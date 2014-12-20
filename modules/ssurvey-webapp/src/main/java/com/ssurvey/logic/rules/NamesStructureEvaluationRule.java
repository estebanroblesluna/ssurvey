package com.ssurvey.logic.rules;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import com.ssurvey.model.Account;
import com.ssurvey.model.LinkedInUserProfile;
import com.ssurvey.service.LinkedInInformationService;

public class NamesStructureEvaluationRule extends ProfileEvaluationRule {
  
  private LinkedInInformationService linkedInInformationService;
  
  public NamesStructureEvaluationRule(float weight, LinkedInInformationService linkedInInformationService) {
    super(weight);
    this.linkedInInformationService = linkedInInformationService;
  }
  
  /*
   * Return 1 if the full name of the account has at least 2 sub strings separated by a white space,
   * or 0 otherwise.
   */
  private static float atLeastTwoStrings (Account act) {
    return (new StringTokenizer(act.getFullName()).countTokens() > 1) ? 1.0f : 0.0f;
  }

  /* 
   * Return true if the string (either name or last name) have good case format.
   * For example: wiLsoN (bad), John (good).
   */
  private static boolean goodCaseWord (String s) {
    if (Character.isLowerCase(s.charAt(0))) {
      return false;
    } else {
      boolean up = false;
      for (int i=1; i<s.length(); i++) {
        if (!Character.isAlphabetic(s.charAt(i))) {
          up = true; // For example, we could have Moore-Towers for last name.
        } else {
          if (up) {
            up = false;
            if (Character.isLowerCase(s.charAt(i))) {
              return false;
            }
          } else {
            if (Character.isUpperCase(s.charAt(i))) {
              return false;
            }
          }
        }
      }
      return true;
    }
  }
  
  /*
   * Return 1 if each of the names of the account has a correct case evaluation,
   * or 0 otherwise.
   */
  private static float correctCase (Account act) {
    if (act.getFirstName() != null && !goodCaseWord(act.getFirstName())) {
      return 0.0f;
    } else if (act.getMiddleName() != null && !goodCaseWord(act.getMiddleName())) {
      return 0.0f;
    } else if (act.getLastName() != null && !goodCaseWord(act.getLastName())) {
      return 0.0f;
    } else {
      return 1.0f;
    }
  }
  
  private static float noInvalidSymbol (Account act) {
    Set<Character> invalid = new HashSet<Character>();
    String invalidCharacters = "!\"@|·#$%&¬/~()=?¿'¡`^[]*+´¨{}_:;<>\\ºª";
    for (int i=0; i<invalidCharacters.length(); i++) {
      invalid.add(invalidCharacters.charAt(i));
    }
    String name = act.getFullName();
    for (int i=0; i<name.length(); i++) {
      if (invalid.contains(name.charAt(i))) {
        return 0.00f;
      }
    }
    return 1.00f;
  }
  
  @Override
  protected float score(LinkedInUserProfile profile) {
    Account act = this.linkedInInformationService.getAccountForLinkedInProfile(profile.getId());
    return (atLeastTwoStrings(act) + correctCase(act) + noInvalidSymbol(act)) / 3.0f;
  }
}

#! /bin/bash

green='\033[0;32m';
yellow='\033[0;33m';
red='\033[0;31m';
reset='\033[0m';

BASEDIR=$(dirname $0)
USER="-u root"
HOST="-h localhost"
PASS=

function apply {
	echo -e "${yellow}Executing $1${reset}";
	mysql $HOST $USER $PASS < $BASEDIR/$1
}

apply mock-data.sql
apply example-data.sql

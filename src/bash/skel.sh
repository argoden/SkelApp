#!/bin/bash
Y='\033[0;33m'
NC='\033[0m' # No Color
function usage() {
printf "${Y}| Skel is a utility to load templates from anywhere ${NC}\n"
printf "${Y}| skel ${NC}-> returns a list of all template \n"
printf "${Y}| skel -l or -long ${NC}-> displays templates names with categories ${NC}\n"
printf "${Y}| skel <templateName> ${NC}-> displays a content of a single template ${NC}\n"
printf "${Y}| skel -c or -category <CATEGORY_NAME> ${NC}-> returns a list of templates in the category ${NC}\n"
printf "${Y}| skel -t or -token <TOKEN> ${NC}-> resets token ${NC}\n"
printf "${Y}| skel -h or -help ${NC}-> displays help ${NC}\n"
}

function exec_cmd(){
  if [ ! -z $CMD ]; then
    curl -s "$CMD?$OPTS&t_=$TOKEN"
  else
    echo "Error: command not found"
  fi
}

#####################################
# main
#####################################

if [ ! -f ~/.skel ]; then
    printf "${Y}Error:${NC} Skel config is missing.\n"
    exit
else
    . /skel_config
    CMD="$URL"
    OPTS=""
fi

if [ -z "$1" ]; then
  exec_cmd
  exit
fi

while getopts ":t:c:hl" optname
  do
    case "$optname" in
      "c")
        OPTS="&category=$2"
        exec_cmd
        exit
        ;;
      "h")
        usage
        exit
        #echo "Option $optname is specified"
        ;;
      "l")
        OPTS="&isVerbose=true"
        exec_cmd
        exit
        ;;
      "?")
        echo "Unknown option $OPTARG, use -h for help"
        exit
        ;;
      ":")
        echo "No argument value for option $OPTARG"
        exit
        ;;
      *)
      # Should not occur
        echo "Unknown error while processing options"
        exit
        ;;
    esac
  done

# loading single template
if [ -z "$2"]; then
  CMD="$URL/$1"
  exec_cmd
  exit
fi

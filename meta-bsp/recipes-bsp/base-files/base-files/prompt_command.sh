rootmode() { printf "[%s]" $(findmnt --noheadings --output FS-OPTIONS /;); }
PROMPT_COMMAND='tput sc; printf "%*s" $COLUMNS "$(rootmode)"; tput rc'

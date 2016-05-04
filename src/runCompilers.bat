@echo off
if not exist jjtreeFiles mkdir jjtreeFiles 
if not exist jjFiles mkdir jjFiles
if not exist javacFiles mkdir javacFiles
jjtree -OUTPUT_DIRECTORY:"jjtreeFiles" parsernav.jjt ^
 && javacc -OUTPUT_DIRECTORY:"jjFiles" jjtreeFiles/parsernav.jj ^
 && echo. && echo Running javac: ^
 && javac -d javacFiles jjtreeFiles/*.java jjFiles/*.java ^
 && echo Done

echo on
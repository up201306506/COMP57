Programa:
java ParserNav [file1]...
Se chamado sem par�metro, para terminar usar EOF (ctrl-z windows, ctrl-d linux)

Notas:
Mudei o "compilador" bat para colocar os ficheiros em direct�rios distintos:
.Os ficheiros gerados a partir do "parsernav.jjt" ficam em "jjtreeFiles" (inclusive "parsernav.jj")
.Os ficheiros gerados a partir do "parsernav.jj" ficam em "jjFiles"
.Os ficheiros bytecode java ficam em "javacFiles"

Por enquanto ParserNav s� faz parse de uma express�o entre /**/ (na mesma linha) 

Acho melhor s� colocarmos no git os ficheiros que alteramos e deixar de fora os
gerados autom�ticamente. Quem precisar corre o runCompilers.bat.
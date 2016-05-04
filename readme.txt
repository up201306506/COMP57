Programa:
java ParserNav [file1]...
Se chamado sem parâmetro, para terminar usar EOF (ctrl-z windows, ctrl-d linux)

Notas:
Mudei o "compilador" bat para colocar os ficheiros em directórios distintos:
.Os ficheiros gerados a partir do "parsernav.jjt" ficam em "jjtreeFiles" (inclusive "parsernav.jj")
.Os ficheiros gerados a partir do "parsernav.jj" ficam em "jjFiles"
.Os ficheiros bytecode java ficam em "javacFiles"

Por enquanto ParserNav só faz parse de uma expressão entre /**/ (na mesma linha) 

Acho melhor só colocarmos no git os ficheiros que alteramos e deixar de fora os
gerados automáticamente. Quem precisar corre o runCompilers.bat.
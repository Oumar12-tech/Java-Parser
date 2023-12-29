# Java-Parser
Java Parse Tree Creator 

This was a fun personal java project I created when I was interested in the concept of how programming languages work and how to create my own programming languages.
I learned about lexemes and parse trees and the java compiler. I decided to create this project to represent how parse trees work in a compiler using java.

The code reads input files with text then traverses and categorizes each letter into the corresponding tokens in a parse tree. 

For example: 

i5.txt contains: f b i a a = 5 b = a + 3.2 p b

Output: 

Next token is: 0, Next lexeme is f
Enter <Prog>
Enter <Dcls>
Enter <Dcl>
Next token is: 3, Next lexeme is b
Next token is: 1, Next lexeme is i
Exit <Dcl>
Enter <Dcls>
Enter <Dcl>
Next token is: 3, Next lexeme is a
Next token is: 3, Next lexeme is a
Exit <Dcl>
Exit <Dcls>
Exit <Dcls>
Enter <Stmts>
Enter <Stmt>
Next token is: 4, Next lexeme is =
Next token is: 7, Next lexeme is 5
Enter <Val>
Next token is: 3, Next lexeme is b
Leave <Val>
Leave <Stmt>
Enter <Stmts>
Enter <Stmt>
Next token is: 4, Next lexeme is =
Next token is: 3, Next lexeme is a
Enter <Val>
Next token is: 5, Next lexeme is +
Leave <Val>
Enter <Expr>
Next token is: 8, Next lexeme is 3.2
Enter <Val>
Next token is: 2, Next lexeme is p
Leave <Val>
Leave <Expr>
Leave <Stmt>
Enter <Stmts>
Enter <Stmt>
Next token is: 3, Next lexeme is b
Leave <Stmt>
Exit <Stmts>
Exit <Stmts>
Exit <Stmts>
Exit <Prog>

       IDENTIFICATION DIVISION.
       PROGRAM-ID. FIZZ-BUZZ.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       77  I                           PIC 9(4).
       77  Q                           PIC 9(4).
       77  R3                          PIC 9(4).
       77  R5                          PIC 9(4).
       
       PROCEDURE DIVISION.
         PERFORM VARYING I FROM 1 BY 1 UNTIL I > 100
           DIVIDE I BY 3 GIVING Q REMAINDER R3;
           DIVIDE I BY 5 GIVING Q REMAINDER R5;
           IF R3 = 0
             THEN
               IF R5 = 0
                 THEN
                   DISPLAY "FizzBuzz";
                 ELSE
                   DISPLAY "Fizz";
               END-IF
             ELSE
               IF R5 = 0
                 THEN
                   DISPLAY "Buzz";
                 ELSE
                   PERFORM SHOW-NUMBER;
               END-IF
           END-IF
         END-PERFORM.
       FIZZ-BUZZ-EXIT.
         STOP RUN.
       
       SHOW-NUMBER SECTION.
         IF I < 10
           THEN
             DISPLAY I(4:1)
           ELSE
             IF I < 100
             THEN
               DISPLAY I(3:2)
             ELSE
               IF I < 1000
                 THEN
                   DISPLAY I(2:3)
                 ELSE
                   DISPLAY I
               END-IF
           END-IF
         END-IF.
       SHOW-NUMBER-EXIT.
         EXIT.
       
       END PROGRAM FIZZ-BUZZ.

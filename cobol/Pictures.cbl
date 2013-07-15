       IDENTIFICATION DIVISION.
       PROGRAM-ID. PICTURES.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  ALL-DATA.
         03  DISPLAY-DATA.
           05  FILLER      PIC X           VALUE "|".
           05  PIC-X       PIC X(5).
           05  FILLER      PIC X           VALUE "|".
           05  PIC-A       PIC A(5).
           05  FILLER      PIC X           VALUE "|".
           05  PIC-MC9V9   PIC --,--9.99.
           05  FILLER      PIC X           VALUE "|".
           05  PIC-S9      PIC S9(5).
           05  FILLER      PIC X           VALUE "|".
           05  PIC-S9V9    PIC S99V999.
           05  FILLER      PIC X           VALUE "|".
           05  PIC-S9P     PIC S99PPP.
           05  FILLER      PIC X           VALUE "|".
           05  PIC-SVP9    PIC SVPP999.
           05  FILLER      PIC X           VALUE "|".
         03  PACKED-DATA.
           05  FILLER      PIC X           VALUE "|".
           05  PIC-S9-PD   PIC S9(5)       USAGE PACKED-DECIMAL.
           05  FILLER      PIC X           VALUE "|".
           05  PIC-S9V9-PD PIC S99V999     USAGE PACKED-DECIMAL.
           05  FILLER      PIC X           VALUE "|".
           05  PIC-S9P-PD  PIC S99PPP      USAGE PACKED-DECIMAL.
           05  FILLER      PIC X           VALUE "|".
           05  PIC-SVP9-PD PIC SVPP999     USAGE PACKED-DECIMAL.
           05  FILLER      PIC X           VALUE "|".
       
       PROCEDURE DIVISION.
         MOVE 123     TO PIC-X.
         MOVE "123"   TO PIC-A.
         PERFORM DISPLAY-TEXTS.
         
         MOVE 1234.5  TO PIC-MC9V9.
         MOVE 123     TO PIC-S9.
         MOVE 1.23    TO PIC-S9V9.
         MOVE 1234    TO PIC-S9P.
         MOVE 0.1234  TO PIC-SVP9.
         MOVE 123     TO PIC-S9-PD.
         MOVE 1.23    TO PIC-S9V9-PD.
         MOVE 1234    TO PIC-S9P-PD.
         MOVE 0.1234  TO PIC-SVP9-PD.
         PERFORM DISPLAY-NUMBERS.
         DISPLAY DISPLAY-DATA.
         
         MOVE -1234.5 TO PIC-MC9V9.
         MOVE -123    TO PIC-S9.
         MOVE -1.23   TO PIC-S9V9.
         MOVE -1234   TO PIC-S9P.
         MOVE -0.1234 TO PIC-SVP9.
         MOVE 123     TO PIC-S9-PD.
         MOVE -1.23   TO PIC-S9V9-PD.
         MOVE -1234   TO PIC-S9P-PD.
         MOVE -0.1234 TO PIC-SVP9-PD.
         PERFORM DISPLAY-NUMBERS.
         DISPLAY DISPLAY-DATA.
         
         STOP RUN.
       
       DISPLAY-TEXTS SECTION.
         DISPLAY PIC-X.
         DISPLAY PIC-A.
         EXIT.
       
       DISPLAY-NUMBERS SECTION.
         DISPLAY PIC-MC9V9.
         DISPLAY PIC-S9.
         DISPLAY PIC-S9V9.
         DISPLAY PIC-S9P.
         DISPLAY PIC-SVP9.
         DISPLAY PIC-S9-PD.
         DISPLAY PIC-S9V9-PD.
         DISPLAY PIC-S9P-PD.
         DISPLAY PIC-SVP9-PD.
         EXIT.
       
       END PROGRAM PICTURES.

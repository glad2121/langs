      ******************************************************************
      * ’n•û‚Ìˆê——‚ð•\Ž¦‚µ‚Ü‚·B
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. SHOW-REGIONS.
       
       DATA DIVISION.
       LINKAGE SECTION.
       01  REGIONS.
         COPY "Regions".
       
       PROCEDURE DIVISION USING REGIONS.
         PERFORM VARYING I FROM 1 BY 1
             UNTIL I > REGIONS-COUNT
           DISPLAY REGION-CODE(I)
               ":" REGION-NAME(I)
               "(" PREFECTURES-COUNT(I) ")";
           PERFORM VARYING J FROM 1 BY 1
               UNTIL J > PREFECTURES-COUNT(I)
             DISPLAY "  " PREFECTURE-CODE(I, J)
                 ":" PREFECTURE-NAME(I, J);
           END-PERFORM
         END-PERFORM.
       SHOW-REGIONS-EXIT.
         EXIT PROGRAM.
       
       END PROGRAM SHOW-REGIONS.

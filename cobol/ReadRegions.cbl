      ******************************************************************
      * 地方の一覧を読み込みます。
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. READ-REGIONS.
       
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
         SELECT REGIONS-FILE
           ASSIGN TO REGIONS-FILENAME
           ORGANIZATION IS LINE SEQUENTIAL
           FILE STATUS IS REGIONS-STATUS.
       
       DATA DIVISION.
       FILE SECTION.
      * 地方ファイル
       FD  REGIONS-FILE.
       01  REGION-RECORD.
         03  REGION-CODE               PIC X(2).
         03  NAME                      PIC N(4).
       
       WORKING-STORAGE SECTION.
       01  REGIONS-STATUS              PIC X(2).
       
       LINKAGE SECTION.
       77  REGIONS-FILENAME            PIC X(80).
       01  REGIONS.
         COPY "Regions".
       
       PROCEDURE DIVISION
           USING REGIONS-FILENAME, REGIONS.
         OPEN INPUT REGIONS-FILE.
         SET I TO 1.
         PERFORM UNTIL REGIONS-STATUS NOT = "00"
           READ REGIONS-FILE
             AT END
               CONTINUE;
             NOT AT END
               ADD 1 TO REGIONS-COUNT;
               MOVE REGION-RECORD TO REGION(I);
               MOVE 0 TO PREFECTURES-COUNT(I);
               SET I UP BY 1;
           END-READ
         END-PERFORM.
       READ-REGIONS-EXIT.
         CLOSE REGIONS-FILE.
         EXIT PROGRAM.
       
       END PROGRAM READ-REGIONS.

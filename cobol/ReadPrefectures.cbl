      ******************************************************************
      * 都道府県の一覧を読み込みます。
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. READ-PREFECTURES.
       
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
         SELECT PREFECTURES-FILE
           ASSIGN TO PREFECTURES-FILENAME
           ORGANIZATION IS LINE SEQUENTIAL
           FILE STATUS IS PREFECTURES-STATUS.
       
       DATA DIVISION.
       FILE SECTION.
      * 都道府県ファイル
       FD  PREFECTURES-FILE.
       01  PREFECTURE-RECORD.
         03  PREFECTURE-CODE           PIC X(2).
         03  NAME                      PIC N(5).
         03  REGION-CODE               PIC X(2).
       
       WORKING-STORAGE SECTION.
       01  PREFECTURES-STATUS          PIC X(2).
       
       LINKAGE SECTION.
       77  PREFECTURES-FILENAME        PIC X(80).
       01  REGIONS.
         COPY "Regions".
       
       PROCEDURE DIVISION
           USING PREFECTURES-FILENAME, REGIONS.
         OPEN INPUT PREFECTURES-FILE.
         PERFORM UNTIL PREFECTURES-STATUS NOT = "00"
           READ PREFECTURES-FILE
             AT END
               CONTINUE;
             NOT AT END
               PERFORM ADD-PREFECTURE;
           END-READ
         END-PERFORM.
       READ-PREFECTURES-EXIT.
         CLOSE PREFECTURES-FILE.
         EXIT PROGRAM.
       
      ******************************************************************
      * 地方に都道府県を追加します。
      ******************************************************************
       ADD-PREFECTURE SECTION.
         SET I TO 1.
         SEARCH REGION VARYING I
           AT END
             DISPLAY "REGION NOT-FOUND";
           WHEN REGION-CODE OF PREFECTURE-RECORD =
               REGION-CODE OF REGION(I)
             ADD 1 TO PREFECTURES-COUNT(I);
             SET J TO PREFECTURES-COUNT(I);
             MOVE PREFECTURE-RECORD TO PREFECTURE(I, J);
         END-SEARCH.
       ADD-PREFECTURE-EXIT.
         EXIT.
       
       END PROGRAM READ-PREFECTURES.

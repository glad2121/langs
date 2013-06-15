      ******************************************************************
      * 地方に属する都道府県の数を表示します。
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. REGION-PREFECTURES.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       77  ARGC                        PIC 9(2).
       77  DATA-DIR                    PIC X(80).
       77  REGIONS-FILENAME            PIC X(80).
       77  PREFECTURES-FILENAME        PIC X(80).
       77  N GLOBAL                    PIC 9(2).
       
      * 地方の一覧
       01  REGIONS.
         COPY "Regions".
       
       PROCEDURE DIVISION.
      D  DISPLAY "ACCEPT FILENAMES".
         PERFORM ACCEPT-FILENAMES.
      D  DISPLAY "INITIALIZE REGIONS".
         INITIALIZE REGIONS.
      D  DISPLAY "CALL READ-REGIONS".
         CALL "READ-REGIONS" USING REGIONS-FILENAME, REGIONS.
      D  DISPLAY "CALL READ-PREFECTURES".
         CALL "READ-PREFECTURES" USING PREFECTURES-FILENAME, REGIONS.
      D  DISPLAY "CALL SHOW-REGIONS".
         CALL "SHOW-REGIONS" USING REGIONS.
       REGION-PREFECTURES-EXIT.
      D  DISPLAY "STOP RUN".
         STOP RUN.
       
      ******************************************************************
      * コマンドライン引数からファイル名を受け取ります。
      ******************************************************************
       ACCEPT-FILENAMES SECTION.
         ACCEPT ARGC FROM ARGUMENT-NUMBER.
         IF ARGC = 0
           THEN
             MOVE "../data" TO DATA-DIR;
           ELSE
             ACCEPT DATA-DIR FROM ARGUMENT-VALUE;
         END-IF.
         CALL "STRLEN" USING DATA-DIR.
         STRING
           DATA-DIR(1:N) DELIMITED BY SIZE
           "/Regions.txt" DELIMITED BY SIZE
           INTO REGIONS-FILENAME
         END-STRING.
         STRING
           DATA-DIR(1:N) DELIMITED BY SIZE
           "/PrefectureRegions.txt" DELIMITED BY SIZE
           INTO PREFECTURES-FILENAME
         END-STRING.
      D  CALL "STRLEN" USING REGIONS-FILENAME.
      D  DISPLAY "REGIONS-FILENAME: " REGIONS-FILENAME(1:N).
      D  CALL "STRLEN" USING PREFECTURES-FILENAME.
      D  DISPLAY "PREFECTURES-FILENAME: " PREFECTURES-FILENAME(1:N).
       ACCEPT-FILENAMES-EXIT.
         EXIT.
       
      ******************************************************************
      * 文字列の長さを求めます。
      ******************************************************************
         IDENTIFICATION DIVISION.
         PROGRAM-ID. STRLEN.
         
         DATA DIVISION.
         LINKAGE SECTION.
         77  S                           PIC X(80).
         
         PROCEDURE DIVISION USING S.
           PERFORM VARYING N FROM 80 BY -1
               UNTIL S(N:1) NOT = " "
           END-PERFORM.
         STRLEN-EXIT.
           EXIT PROGRAM.
         
         END PROGRAM STRLEN.
       
       END PROGRAM REGION-PREFECTURES.

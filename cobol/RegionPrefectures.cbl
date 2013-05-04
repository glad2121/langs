      * 
      * 地方に属する都道府県の数を表示します。
      * 
       IDENTIFICATION DIVISION.
       PROGRAM-ID.   REGION-PREFECTURES.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
      * 地方の一覧
       01  REGIONS GLOBAL.
           05  REGIONS-COUNT            PIC 9(2) VALUE ZERO.
           05  REGION                   OCCURS 10 INDEXED BY I.
               10  REGION-CODE          PIC X(2) VALUE SPACE.
               10  REGION-NAME          PIC N(4) VALUE SPACE.
               10  PREFECTURES-COUNT    PIC 9(2) VALUE ZERO.
               10  PREFECTURE           OCCURS 10 INDEXED BY J.
                   15  PREFECTURE-CODE  PIC X(2) VALUE SPACE.
                   15  PREFECTURE-NAME  PIC N(5) VALUE SPACE.
       
       PROCEDURE DIVISION.
         CALL "READ-REGIONS".
         CALL "READ-PREFECTURES".
         
         PERFORM VARYING I FROM 1 BY 1 UNTIL I > REGIONS-COUNT
           DISPLAY REGION-CODE(I)
               ":" REGION-NAME(I)
               ":" PREFECTURES-COUNT(I);
           PERFORM VARYING J FROM 1 BY 1 UNTIL J > PREFECTURES-COUNT(I)
             DISPLAY "  " PREFECTURE-CODE(I, J)
                 ":" PREFECTURE-NAME(I, J);
           END-PERFORM
         END-PERFORM
         STOP RUN.
      
      *   
      *   地方の一覧を読み込みます。
      *   
         IDENTIFICATION DIVISION.
         PROGRAM-ID. READ-REGIONS.
         
         ENVIRONMENT DIVISION.
         INPUT-OUTPUT SECTION.
         FILE-CONTROL.
           SELECT REGIONS-FILE
             ASSIGN TO "../data/Regions.txt"
             ORGANIZATION IS LINE SEQUENTIAL
             FILE STATUS IS REGIONS-STATUS.
         
         DATA DIVISION.
         FILE SECTION.
      *   地方ファイル
         FD  REGIONS-FILE.
         01  REGION-RECORD.
             05  REGION-CODE            PIC X(2).
             05  NAME                   PIC N(4).
         
         WORKING-STORAGE SECTION.
         01  REGIONS-STATUS             PIC X(2).
         
         PROCEDURE DIVISION.
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
           END-PERFORM
           CLOSE REGIONS-FILE.
           EXIT PROGRAM.
         
         END PROGRAM READ-REGIONS.
       
      *   
      *   都道府県の一覧を読み込みます。
      *   
         IDENTIFICATION DIVISION.
         PROGRAM-ID. READ-PREFECTURES.
         
         ENVIRONMENT DIVISION.
         INPUT-OUTPUT SECTION.
         FILE-CONTROL.
           SELECT PREFECTURES-FILE
             ASSIGN TO "../data/PrefectureRegions.txt"
             ORGANIZATION IS LINE SEQUENTIAL
             FILE STATUS IS PREFECTURES-STATUS.
         
         DATA DIVISION.
         FILE SECTION.
      *   都道府県ファイル
         FD  PREFECTURES-FILE.
         01  PREFECTURE-RECORD.
             05  PREFECTURE-CODE        PIC X(2).
             05  NAME                   PIC N(5).
             05  REGION-CODE            PIC X(2).
         
         WORKING-STORAGE SECTION.
         01  PREFECTURES-STATUS         PIC X(2).
         
         PROCEDURE DIVISION.
           OPEN INPUT PREFECTURES-FILE.
           PERFORM UNTIL PREFECTURES-STATUS NOT = "00"
             READ PREFECTURES-FILE
               AT END
                 CONTINUE;
               NOT AT END
                 SET I TO 1;
                 SEARCH REGION VARYING I
                   AT END
                     DISPLAY "REGION-CODE NOT-FOUND";
                   WHEN REGION-CODE OF PREFECTURE-RECORD =
                       REGION-CODE OF REGION(I)
                     ADD 1 TO PREFECTURES-COUNT(I);
                     SET J TO PREFECTURES-COUNT(I);
                     MOVE PREFECTURE-RECORD TO PREFECTURE(I, J);
                 END-SEARCH
             END-READ
           END-PERFORM
           CLOSE PREFECTURES-FILE.
           EXIT PROGRAM.
         
         END PROGRAM READ-PREFECTURES.
       
       END PROGRAM REGION-PREFECTURES.

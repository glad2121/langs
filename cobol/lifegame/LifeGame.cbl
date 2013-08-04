      ******************************************************************
      * ライフゲーム。
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. LIFE-GAME.
       
      ******************************************************************
      * データ部。
      ******************************************************************
       DATA DIVISION.
       
      ******************************************************************
      * 作業場所節。
      ******************************************************************
       WORKING-STORAGE SECTION.
      * ステージ1。
       01  STAGE-1.
           COPY "Stage".
      * ステージ2。
       01  STAGE-2.
           COPY "Stage".
       
      ******************************************************************
      * 手続き部。
      ******************************************************************
       PROCEDURE DIVISION.
           MOVE 1 TO CELL OF STAGE-1(5, 7).
           MOVE 1 TO CELL OF STAGE-1(5, 8).
           MOVE 1 TO CELL OF STAGE-1(5, 9).
           MOVE 1 TO CELL OF STAGE-1(6, 7).
           MOVE 1 TO CELL OF STAGE-1(7, 8).
           
           CALL "SHOW-STAGE" USING STAGE-1.
           PERFORM 5 TIMES
               CALL "NEXT-STAGE" USING STAGE-1, STAGE-2;
               DISPLAY " ";
               CALL "SHOW-STAGE" USING STAGE-2;
               CALL "NEXT-STAGE" USING STAGE-2, STAGE-1;
               DISPLAY " ";
               CALL "SHOW-STAGE" USING STAGE-1;
           END-PERFORM.
           
       LIFE-GAME-EXIT.
           STOP RUN.
       
       END PROGRAM LIFE-GAME.

      ******************************************************************
      * 次のステージを返します。
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. NEXT-STAGE.
       
      ******************************************************************
      * データ部。
      ******************************************************************
       DATA DIVISION.
       
      ******************************************************************
      * 作業場所節。
      ******************************************************************
       WORKING-STORAGE SECTION.
       77  I                           PIC 9(2).
       77  J                           PIC 9(2).
       77  ROW-IDX                     PIC 9(2).
       77  COL-IDX                     PIC 9(2).
       
      * 生存する近傍の個数。
       77  ALIVE-NEIGHBORS             PIC 9.
       
      ******************************************************************
      * 連絡節。
      ******************************************************************
       LINKAGE SECTION.
      * 現在のステージ。
       01  STAGE-CURR.
           COPY "Stage".
      * 次のステージ。
       01  STAGE-NEXT.
           COPY "Stage".
       
      ******************************************************************
      * 手続き部。
      ******************************************************************
       PROCEDURE DIVISION USING STAGE-CURR, STAGE-NEXT.
           PERFORM VARYING I FROM 1 BY 1 UNTIL I > 8
               PERFORM VARYING J FROM 1 BY 1 UNTIL J > 10
                   PERFORM COUNT-ALIVE-NEIGHBORS;
                   IF CELL OF STAGE-CURR(I, J) = 0
                     THEN
                       IF ALIVE-NEIGHBORS = 3
                         THEN
                           MOVE 1 TO CELL OF STAGE-NEXT(I, J);
                         ELSE
                           MOVE 0 TO CELL OF STAGE-NEXT(I, J);
                       END-IF
                     ELSE
                       IF ALIVE-NEIGHBORS = 2 OR ALIVE-NEIGHBORS = 3
                         THEN
                           MOVE 1 TO CELL OF STAGE-NEXT(I, J);
                         ELSE
                           MOVE 0 TO CELL OF STAGE-NEXT(I, J);
                       END-IF
                   END-IF
               END-PERFORM
           END-PERFORM.
           
       NEXT-STAGE-EXIT.
           EXIT PROGRAM.
       
      ******************************************************************
      * 生存する近傍の個数を数えます。
      ******************************************************************
       COUNT-ALIVE-NEIGHBORS SECTION.
           MOVE 0 TO ALIVE-NEIGHBORS.
           
           COMPUTE ROW-IDX = I - 1.
           COMPUTE COL-IDX = J - 1.
           PERFORM COUNT-ALIVE.
           
           ADD 1 TO COL-IDX.
           PERFORM COUNT-ALIVE.
           
           ADD 1 TO COL-IDX.
           PERFORM COUNT-ALIVE.
           
           ADD 1 TO ROW-IDX.
           PERFORM COUNT-ALIVE.
           
           ADD 1 TO ROW-IDX.
           PERFORM COUNT-ALIVE.
           
           SUBTRACT 1 FROM COL-IDX.
           PERFORM COUNT-ALIVE.
           
           SUBTRACT 1 FROM COL-IDX.
           PERFORM COUNT-ALIVE.
           
           SUBTRACT 1 FROM ROW-IDX.
           PERFORM COUNT-ALIVE.
           
       COUNT-ALIVE-NEIGHBORS-EXIT.
           EXIT.
       
      ******************************************************************
      * 指定したセルの生死を確認します。
      ******************************************************************
       COUNT-ALIVE SECTION.
           IF ROW-IDX >= 1 AND ROW-IDX <= 8
             THEN
               IF COL-IDX >= 1 AND COL-IDX <= 10
                 THEN
                   ADD CELL OF STAGE-CURR(ROW-IDX, COL-IDX)
                       TO ALIVE-NEIGHBORS;
               END-IF
           END-IF.
           
       COUNT-ALIVE-EXIT.
           EXIT.
       
       END PROGRAM NEXT-STAGE.

      ******************************************************************
      * ���C�t�Q�[���B
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. LIFE-GAME.
       
      ******************************************************************
      * �f�[�^���B
      ******************************************************************
       DATA DIVISION.
       
      ******************************************************************
      * ��Əꏊ�߁B
      ******************************************************************
       WORKING-STORAGE SECTION.
      * �X�e�[�W1�B
       01  STAGE-1.
           COPY "Stage".
      * �X�e�[�W2�B
       01  STAGE-2.
           COPY "Stage".
       
      ******************************************************************
      * �葱�����B
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

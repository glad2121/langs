      ******************************************************************
      * �X�e�[�W��\�����܂��B
      ******************************************************************
       IDENTIFICATION DIVISION.
       PROGRAM-ID. SHOW-STAGE.
       
      ******************************************************************
      * �f�[�^���B
      ******************************************************************
       DATA DIVISION.
       
      ******************************************************************
      * ��Əꏊ�߁B
      ******************************************************************
       WORKING-STORAGE SECTION.
       77  I                           PIC 9(2).
       77  J                           PIC 9(2).
       
      * �\���p�̃X�e�[�W�B
       01  STAGE-DISP.
           03  ROW                     OCCURS  8.
               05  CELL                OCCURS 10 PIC N.
       
      ******************************************************************
      * �A���߁B
      ******************************************************************
       LINKAGE SECTION.
      * �X�e�[�W�B
       01  STAGE.
           COPY "Stage".
       
      ******************************************************************
      * �葱�����B
      ******************************************************************
       PROCEDURE DIVISION USING STAGE.
           PERFORM VARYING I FROM 1 BY 1 UNTIL I > 8
               PERFORM VARYING J FROM 1 BY 1 UNTIL J > 10
                   IF CELL OF STAGE(I, J) = 0
                     THEN
                       MOVE "��" TO CELL OF STAGE-DISP(I, J);
                     ELSE
                       MOVE "��" TO CELL OF STAGE-DISP(I, J);
                   END-IF
               END-PERFORM
           END-PERFORM.
           PERFORM VARYING I FROM 1 BY 1 UNTIL I > 8
               DISPLAY ROW OF STAGE-DISP(I);
           END-PERFORM.
           
       SHOW-STAGE-EXIT.
           EXIT PROGRAM.
       
       END PROGRAM SHOW-STAGE.

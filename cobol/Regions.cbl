           05  REGIONS-COUNT            PIC 9(2).
           05  REGION                   OCCURS 10 INDEXED BY I.
               10  REGION-CODE          PIC X(2).
               10  REGION-NAME          PIC N(4).
               10  PREFECTURES-COUNT    PIC 9(2).
               10  PREFECTURE           OCCURS 10 INDEXED BY J.
                   15  PREFECTURE-CODE  PIC X(2).
                   15  PREFECTURE-NAME  PIC N(5).

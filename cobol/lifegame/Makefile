#
# Makefile
#

COBCFLAGS = -std=cobol85

TARGET = LifeGame
OBJS = $(TARGET).o NextStage.o ShowStage.o

all: $(TARGET)

$(TARGET): $(OBJS)
	cobc -x $(COBCFLAGS) $(OBJS)

$(TARGET).o: $(TARGET).cbl
	cobc -c -x $(COBCFLAGS) $<

.SUFFIXES: .cbl

.cbl.o:
	cobc -c $(COBCFLAGS) $<

clean:
	-rm $(TARGET) *.o

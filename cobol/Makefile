#
# Makefile
#

COBCFLAGS = -std=cobol85

TARGET = RegionPrefectures
OBJS = $(TARGET).o ReadRegions.o ReadPrefectures.o ShowRegions.o

all: FizzBuzz $(TARGET)

FizzBuzz: FizzBuzz.cbl
	cobc -x $(COBCFLAGS) $<

$(TARGET): $(OBJS)
	cobc -x $(COBCFLAGS) $(OBJS)

$(TARGET).o: $(TARGET).cbl
	cobc -c -x $(COBCFLAGS) $<

.SUFFIXES: .cbl

.cbl.o:
	cobc -c $(COBCFLAGS) $<

clean:
	-rm FizzBuzz $(TARGET) *.o

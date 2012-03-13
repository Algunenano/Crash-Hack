SRCF		= src/
BUILDF 		= build/
INCLUDEF	= include/
TARGETF		= $(BUILDF)

    #MAIN FILES
TARGETS.cc	= leveltopng.cpp
TARGETS.c	= 
    #NON MAIN IMPLEMENTER FILES
FILES		= 
	
OBJECTS 	= $(FILES:%=$(BUILDF)%.o)
	
OUTPUT_PREFIX	= target_
OUTPUT 		= $(TARGETS.cc:%.cpp=$(TARGETF)$(OUTPUT_PREFIX)%) \
		  $(TARGETS.c:%.c=$(TARGETF)$(OUTPUT_PREFIX)%)
	
CC		= gcc
CCC		= g++
HEADERFLAGS	= -I $(INCLUDEF)
CFLAGS		= -Wall -Wextra -g $(HEADERFLAGS)
LDFLAGS 	=
PACKAGES	= libpng

ifneq ($(strip $(PACKAGES)),)
    CFLAGS	+= $(shell pkg-config --cflags $(PACKAGES))
    LDFLAGS	+= $(shell pkg-config --libs $(PACKAGES))
endif

CCFLAGS		= $(CFLAGS) -std=c++0x

RM		= rm -rf

COMPILE.c	= $(CC) $(CFLAGS)  -c
LINK.c		= $(CC) $(CFLAGS) $(LDFLAGS)
COMPILE.cc	= $(CCC) $(CCFLAGS) -c
LINK.cc		= $(CCC) $(CCFLAGS) $(LDFLAGS)

all:		$(OUTPUT) $(OBJECTS)


$(TARGETF)$(OUTPUT_PREFIX)%: $(SRCF)%.cpp $(OBJECTS)
	$(shell mkdir -p $(TARGETF))
	$(LINK.cc) $< $(OBJECTS) -o $@

$(TARGETF)$(OUTPUT_PREFIX)%: $(SRCF)%.c $(OBJECTS)
	$(shell mkdir -p $(TARGETF))
	$(LINK.c) $< $(OBJECTS) -o $@

$(BUILDF)%.cpp.o:   $(SRCF)%.cpp
	$(shell mkdir -p $(BUILDF))
	$(COMPILE.cc) $< -o $@

$(BUILDF)%.c.o:   $(SRCF)%.c
	$(shell mkdir -p $(BUILDF))
	$(COMPILE.c) $< -o $@

clean:
	$(RM) $(TARGETF) $(BUILDF)

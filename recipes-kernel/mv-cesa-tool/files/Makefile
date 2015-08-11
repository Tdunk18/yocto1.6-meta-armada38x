SRC := $(shell pwd)

all:
	$(MAKE) -C $(KERNEL_SRC)/tools/cesa

modules_install:
	printenv
	install -D $(KERNEL_SRC)/tools/cesa/mv_cesa_tool $(INSTALL_MOD_PATH)/usr/bin/mv_cesa_tool

clean:
	$(MAKE) -C $(KERNEL_SRC)/tools/cesa clean

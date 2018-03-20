# Grapple

_Easy interaction with native processes, all from Java_

[![Build Status](https://travis-ci.org/Jire/grapple.svg?branch=master)](https://travis-ci.org/Jire/grapple)
[![License](https://img.shields.io/github/license/Jire/grapple.svg)](https://github.com/Jire/grapple/blob/master/LICENSE.txt)

---

Example usage:

```java
Process process = ProcessFinder.findProcessesByName("process.exe").get(0);
process.readByte(0x12345); // read a byte at address 0x12345

process.loadModules(); // need to load modules before accessing them!

Module module = process.getModules().get("module.dll");
module.readByte(0x12345); // read a byte at offset 0x12345
```

**Please note:** this project is not yet complete, and all API and feature designs 
are subject to change at any time.
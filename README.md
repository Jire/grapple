# Grapple

_Easy interaction with native processes, all from Java_

[![Build Status](https://travis-ci.org/Jire/grapple.svg?branch=master)](https://travis-ci.org/Jire/grapple)
[![License](https://img.shields.io/github/license/Jire/grapple.svg)](https://github.com/Jire/grapple/blob/master/LICENSE.txt)

---

The easiest way to get started is by using a snapshotter.

```java
Process process = ProcessFinder.findProcessesByName("chrome.exe").get(0);
new JNASnapshotter(process) {
	@Override
	public void afterSnapshot() {
		System.out.println("Read " + readByte(0x12345));
	}
}.autoTakeSnapshots(); // will take snapshots every 16ms, approximately 60 FPS
```

This is the most efficient way to interact with a process, and makes things easy for you.
If you need to support a faster snapshot rate, you can specify it as an argument of `autoTakeSnapshots`.

You can also directly read from a `Process`, and it's plenty of efficient and great for non-real-time applications.

```java
process.readByte(0x12345);
```
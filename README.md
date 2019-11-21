
## ClojureScript React Native Goals:

* Support latest version of react-native (0.61).
* Be able to upgrade react-native version easily.
* Dev mode where app UI reloads instantly upon saving cljs files.
* Dev mode works on simulator and device.
* CLJS nrepl connection to the running app:
   * Emacs users: support CIDER + piggieback + refactor-nrepl.
   * Cursive users: ???
* Use any NPM library, including async-storage, react-native-elements, fontawesome.
* Be able to do a production cljs build for release to app store.

## Survey of Tools in 2019:

I surveyed the ways to do this, and it looks like the main choice we are faced with
is whether to use [figwheel](https://figwheel.org/) or
[shadow-cljs](http://shadow-cljs.org/) for handling the compliation and the REPL.

Both of these setups, in theory, support all the above bullets. They each also have
good templates for getting started.  Here are my recommended starting points:

* Figwheel: https://github.com/bhauman/react-native-figwheel-bridge
* Shadow: https://github.com/thheller/reagent-react-native

I've tried both, and I'm still not sure which I prefer.


---
### Below here is a work in progress:


## Figwheel

* Quirks/Issues:

  * I recommend keeping all the react-native stuff in a separate react-native
directory, the way shadow's project template does it.
  * ES6 3rdparty NPM libraries (such as `@react-native-community/async-storage` and
    `react-native-elements`) requires extra `cljsExports` hacks in `index.js`. I
    wrote a clojure function to generate `index.js` for the figwheel-bridge.

## Shadow

```
$ brew install node
$ brew install watchman
$ sudo gem install cocoapods
$ npx react-native init AwesomeProject
$ mv AwesomeProject react-native
```

* Quirks/Issues:
  * `clj-refactor` `cljr-clean-ns` does not currently support the quoted require
    syntax that shadow uses. This is hopefully an easy fix.

(defproject org.clojars.dyne/fxc "0.2.0"
  :description "FXC simple secret sharing library"
  :url "https://secrets.dyne.org"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [com.tiemens/secretshare "1.4.2"]
                 [jstrutz/hashids "1.0.1"]
                 [me.lemire.integercompression/JavaFastPFOR "0.1.10"]]

  :jvm-opts ["-Djava.security.egd=file:/dev/random" ;use a proper random source
             "-XX:-OmitStackTraceInFastThrow" ; stacktrace JVM exceptions
             ]

  :profiles
  {:dev {:dependencies [[midje "1.8.3"]]
         :plugins [[lein-midje "3.1.3"]]}})

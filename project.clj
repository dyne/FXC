(defproject org.clojars.dyne/fxc "0.5.0-SNAPSHOT"
  :description "FXC simple secret sharing library"
  :url "https://github.com/dyne/fxc"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [com.tiemens/secretshare "1.4.2"]
                 [jstrutz/hashids "1.0.1"]
                 [me.lemire.integercompression/JavaFastPFOR "0.1.10"]]

  :jvm-opts ["-Djava.security.egd=file:/dev/random" ;use a proper random source
             "-XX:-OmitStackTraceInFastThrow" ; stacktrace JVM exceptions
             ]
  :license {:author "Denis Roio"
            :email "jaromil@dyne.org"
            :year 2017
            :key "gpl-3.0"}

  :deploy-repositories [["releases" {:url :clojars
                                     :creds :gpg}]]
  :profiles {:dev {:dependencies [[midje "1.8.3"]]
                   :plugins [[lein-midje "3.1.3"]]}})

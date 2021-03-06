(defproject asciinema "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [clj-time "0.13.0"]
                 [duct "0.8.2"]
                 [yada "1.2.0"]
                 [aleph "0.4.1"]
                 [bidi "2.0.16"]
                 [prismatic/schema "1.1.3"]
                 [environ "1.1.0"]
                 [ring "1.5.0"]
                 [clj-bugsnag "0.2.9"]
                 [clj-aws-s3 "0.3.10" :exclusions [joda-time]]
                 [cheshire "5.7.0"]
                 [pandect "0.6.1"]
                 [com.taoensso/timbre "4.8.0"]
                 [com.taoensso/carmine "2.15.1"]
                 [org.slf4j/slf4j-nop "1.7.21"]
                 [duct/hikaricp-component "0.1.0"]
                 [org.postgresql/postgresql "9.4.1211"]
                 [duct/ragtime-component "0.1.4"]
                 [me.raynes/conch "0.8.0"]]
  :plugins [[lein-environ "1.0.3"]]
  :main ^:skip-aot asciinema.main
  :target-path "target/%s/"
  :aliases {"setup"  ["run" "-m" "duct.util.repl/setup"]}
  :profiles
  {:dev  [:project/dev  :profiles/dev]
   :test [:project/test :profiles/test]
   :uberjar {:aot :all}
   :repl {:repl-options {:host "0.0.0.0"
                         :port 44444}}
   :profiles/dev  {}
   :profiles/test {}
   :project/dev   {:dependencies [[duct/generate "0.8.2"]
                                  [reloaded.repl "0.2.3"]
                                  [org.clojure/tools.namespace "0.2.11"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [eftest "0.1.1"]
                                  [com.gearswithingears/shrubbery "0.4.1"]
                                  [kerodon "0.8.0"]]
                   :source-paths   ["dev/src"]
                   :resource-paths ["dev/resources"]
                   :repl-options {:init-ns user}}
   :project/test  {}})

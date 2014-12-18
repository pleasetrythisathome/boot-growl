(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.7.0-alpha4" :scope "provided"]
                  [adzerk/bootlaces "0.1.5" :scope "test"]
                  [jakemcc/clojure-gntp "0.1.1"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.1.0-SNAPSHOT")
(bootlaces! +version+)

(task-options!
 pom  {:project     'pleasetrythisathome/boot-growl
       :version     +version+
       :description "Boot task for growl noticications"
       :url         "https://github.com/pleasetrythisathome/boot-growl"
       :scm         {:url "https://github.com/pleasetrythisathome/boot-growl"}
       :license     {:name "Eclipse Public License"
                     :url  "http://www.eclipse.org/legal/epl-v10.html"}})

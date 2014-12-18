(ns boot-growl.core
  {:boot/export-tasks true}
  (:require [jakemcc.clojure-gntp.gntp :as gntp]))

(deftask growl
  "Growl notifications during build."
  []
  (let [success false]
    (pod/add-dependencies-worker {:dependencies '[[jakemcc/clojure-gntp "0.1.1"]]})
    (fn [continue]
      (fn [event]
        (try
          (util/with-let [ret (continue event)]
            (pod/call-worker
             (if (zero? @*warnings*)
               `(jakemcc.clojure-gntp.gntp/message "Boot - success" ""
                                                   :appname "boot"
                                                   :app "boot"
                                                   :notification "boot-notify")
               `(jakemcc.clojure-gntp.gntp/message "Boot - warning(s)" ~(deref *warnings*)
                                                   :appname "boot"
                                                   :app "boot"
                                                   :notification "boot-notify"))))
          (catch Throwable t
            (pod/call-worker
             `(jakemcc.clojure-gntp.gntp/message "Boot - error" ~(str (.getMessage t))
                                                 :appname "boot"
                                                 :app "boot"
                                                 :notification "boot-notify"))
            (throw t)))))))

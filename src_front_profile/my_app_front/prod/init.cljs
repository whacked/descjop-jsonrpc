(ns my-app-front.init
    (:require [my-app-front.core :as core]
              [my-app-front.conf :as conf]))

(enable-console-print!)

(defn start-descjop! []
  (core/init! conf/setting))

(start-descjop!)

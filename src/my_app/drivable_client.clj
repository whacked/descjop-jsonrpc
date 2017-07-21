(ns my-app.drivable-client
  (:require [clojure.test :as t]
            [clj-http.client :as client]
            [clj-jsonrpc.jsonrpc :as jsonrpc]
            [clojure.data.json :as json]))

(def rpc-server-spec {:protocol "http"
                      :host "localhost"
                      :port 8002
                      :endpoint "rpc"})
(def rpc-server-address (->> (map rpc-server-spec
                                  [:protocol :host :port :endpoint])
                             (interleave ["" "://" ":" "/"])
                             (apply str)))
(def rpc-counter (atom 0))

(defn drivable-eval [method code]
  (client/post rpc-server-address
               {:form-params {:jsonrpc "2.0"
                              :id (swap! rpc-counter inc)
                              :method method
                              :params [code]}
                :content-type :json}))

(defn drivable-eval-javascript [js]
  (drivable-eval "javascript" js))

(defn drivable-eval-sibilant [sib]
  (drivable-eval "sibilant" sib))

;; (drivable-eval-sibilant "(console.log \"HELLO WORLD\")")

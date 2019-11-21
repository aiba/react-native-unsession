(ns awesome.client.main
  (:require [hx.react :as hx]
            [oops.core :refer [ocall]]
            ["react-native" :as rn]))

;; See https://github.com/binaryage/cljs-devtools/issues/25
(when (exists? js/Symbol)
  (extend-protocol IPrintWithWriter
    js/Symbol
    (-pr-writer [sym writer _]
      (-write writer (str "\"" (.toString sym) "\"")))))

(hx/defnc Root [_]
  [rn/View {:style {:flex 1, :alignItems "center", :justifyContent "center"}}
   [rn/Text {:style {:font-size 36}}
    "Hello world!"]])

(defonce *root-wrapper (atom nil))

(hx/defcomponent RootWrapper
  (constructor [this]
    this)
  (componentDidMount [this]
    (reset! *root-wrapper this))
  (componentWillUnmount [this]
    (reset! *root-wrapper nil))
  (render [this]
    [Root]))

(defn start {:dev/after-load true} []
  (if-let [rw @*root-wrapper]
    (ocall rw :forceUpdate)
    (rn/AppRegistry.registerComponent "AwesomeProject" (fn [] RootWrapper))))

(defn init []
  (start))

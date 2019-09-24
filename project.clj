(defproject emptyone/uberjar-deploy "1.0.0"

  :description "A plugin that deploys an uberjar instead of a jar.  Intended to be used as an alias with the lein-release plugin. Based on https://github.com/BrightNorth/uberjar-deploy"

  :url "https://github.com/evilsneer/uberjar-deploy"

  :plugins [[org.apache.maven.wagon/wagon-ssh-external "2.6"]]
  :deploy-repositories ^:replace [["releases" {:url "scp://root@10.20.30.111/root/rel/"
                                               :sign-releases false}]
                                  ["snapshots" "scp://root@10.20.30.111/root/snap/"]]

  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :eval-in-leiningen true

  :dependencies [[org.clojure/clojure "1.6.0"]])


(cemerick.pomegranate.aether/register-wagon-factory!
  "scp" #(let [c (resolve 'org.apache.maven.wagon.providers.ssh.external.ScpExternalWagon)]
           (clojure.lang.Reflector/invokeConstructor c (into-array []))))
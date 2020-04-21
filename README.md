# uberjar-deploy

A Leiningen plugin to deploy uberjars to any of your repositories, intended to be used in a `lein release` workflow.  
Delegates to `lein deploy` under the hood. Fix forked project for working with custom repos. See `uberjar-deploy` command.

## Rationale

A plugin that just handles deployment of an uberjar by passing all the relevant arguments to the `lein deploy` task, which it pulls out
of the `project.clj`.

## Usage

Add 
    
    :repositories [["emptyone" "https://clojars.emptyone.site/rel"]]
    
to your project.

Put `[emptyone/uberjar-deploy "1.0.0"]` into the `:plugins` vector of your
`:user` profile.

You'll need something like this in your `project.clj`:

    :aliases {"jar"    "uberjar"
              "deploy" "uberjar-deploy"}  ; only maybe

    :release-tasks ^:replace [["vcs" "assert-committed"]
                              ["change" "version" "leiningen.release/bump-version" "release"]
                              ["vcs" "commit"]
                              ["vcs" "tag" "--no-sign"]
                              ["clean"]
                              ["uberjar"]
                              ["uberjar-deploy" "<repo name>"]
                              ["change" "version" "leiningen.release/bump-version"]
                              ["vcs" "commit"]
                              ["vcs" "push"]]

Then you can just run a release as normal:

    $ lein release

Also could be used as separated commands:

    $ lein uberjar && lein uberjar-deploy <repo name>

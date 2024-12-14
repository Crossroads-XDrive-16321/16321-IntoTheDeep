{
  inputs = {
    flake-utils.url = "github:numtide/flake-utils";
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
  };

  outputs =
    inputs:
    inputs.flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = (import (inputs.nixpkgs) { inherit system; });
      in
      rec {
        devShell = pkgs.mkShell {
          buildInputs = with pkgs; [
            jdk17
            (callPackage gradle-packages.gradle_7 {
              java = jdk17;
            })
          ];

          JAVA_HOME = "${pkgs.jdk17.home}";
          GRADLE_HOME = "${pkgs.gradle_7}/lib/gradle";
        };
      }
    );
}

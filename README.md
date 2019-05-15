# 最初のプロジェクト設定
## 雛型をコピーして、みなさんのPC用の環境設定が必要なファイルを用意  
**※IntelliJを起動する前に実施してください。**  
gitから初めてcloneしたときには、下記のファイルのコピーを作って名前変更をしてください。
- .idea/workspace.xml.org → .idea/workspace.xml
- .idea/misc.xml.org → .idea/misc.xml
- pom.properties.org → pom.properties

## このプロジェクトでビルドするJarファイルを、Spigotサーバーのpluginsフォルダに直接出力する方法
pom.properties を開き、jar.output.dirを設定してください。

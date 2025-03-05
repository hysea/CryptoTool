### CryptoTool
CryptoTool 是一个基于 IntelliJ 平台的加密解密工具，主要使用 AES 算法对文本进行加密和解密操作。
#### 功能特性
**1. 加密功能**
用户可以选择需要加密的文本，程序会使用 AES 算法对其进行加密，并将加密后的文本替换原文本。
**2. 解密功能**
用户可以选择需要解密的文本，程序会使用 AES 算法对其进行解密，并将解密后的文本替换原文本。
**3. 密钥配置**
用户可以在 Settings/Preferences -> Tools -> AES Encryption 中配置 AES 加密所需的密钥。
#### 使用方法
加密操作步骤
打开需要加密的文件，选中要加密的文本。
触发加密操作（具体触发方式取决于工具的集成方式，比如通过菜单栏或快捷键等）。
如果密钥未配置，会弹出提示框，引导用户到 Settings/Preferences -> Tools -> AES Encryption 中配置密钥。
配置好密钥后再次触发加密操作，加密后的文本会替换原选中的文本。
解密操作步骤
打开需要解密的文件，选中要解密的文本。
触发解密操作（具体触发方式取决于工具的集成方式，比如通过菜单栏或快捷键等）。
如果密钥未配置，会弹出提示框，引导用户到 Settings/Preferences -> Tools -> AES Encryption 中配置密钥。
配置好密钥后再次触发解密操作，解密后的文本会替换原选中的文本。
密钥配置步骤
打开 Settings/Preferences。
找到 Tools -> AES Encryption 选项。
在输入框中输入 AES 密钥。

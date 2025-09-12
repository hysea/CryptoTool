### CryptoTool
CryptoTool 是一个基于 IntelliJ 平台的加密解密工具，主要使用 AES 算法对文本进行加密和解密操作。同时，还支持文本的混淆和反混淆功能。

#### 功能特性
**1. 加密功能**
用户可以选择需要加密的文本，程序会使用 AES 算法对其进行加密，并将加密后的文本替换原文本。

**2. 解密功能**
用户可以选择需要解密的文本，程序会使用 AES 算法对其进行解密，并将解密后的文本替换原文本。

**3. 混淆功能**
用户可以选择需要混淆的文本，程序会根据配置的 seed 对文本进行混淆，并将混淆后的文本替换原文本。

**4. 反混淆功能**
用户可以选择需要反混淆的文本，程序会根据配置的 seed 对文本进行反混淆，并将反混淆后的文本替换原文本。

**5. 密钥配置**
用户可以在 Settings/Preferences -> Tools -> AES Encryption 中配置 AES 加密所需的密钥和 seed。

#### 使用方法
##### 密钥配置步骤
1. 打开 Settings/Preferences。
2. 找到 Tools -> AES Encryption 选项。
3. 在输入框中输入 AES 密钥和 seed。

##### 加密操作步骤
1. 打开需要加密的文件，选中要加密的文本。
2. 触发加密操作（可通过菜单栏 `EncryptAction` 或快捷键 `ctrl shift E`）。
3. 如果密钥未配置，会弹出提示框，引导用户到 Settings/Preferences -> Tools -> AES Encryption 中配置密钥。
4. 配置好密钥后再次触发加密操作，加密后的文本会替换原选中的文本。

##### 解密操作步骤
1. 打开需要解密的文件，选中要解密的文本。
2. 触发解密操作（可通过菜单栏 `DecryptAction` 或快捷键 `ctrl shift D`）。
3. 如果密钥未配置，会弹出提示框，引导用户到 Settings/Preferences -> Tools -> AES Encryption 中配置密钥。
4. 配置好密钥后再次触发解密操作，解密后的文本会替换原选中的文本。

##### 混淆操作步骤
1. 打开需要混淆的文件，选中要混淆的文本。
2. 触发混淆操作（可通过菜单栏 `ConfuseAction` 或快捷键 `ctrl shift C`）。
3. 如果 seed 未配置，会弹出提示框，引导用户到 Settings/Preferences -> Tools -> AES Encryption 中配置 seed。
4. 配置好 seed 后再次触发混淆操作，混淆后的文本会替换原选中的文本。

##### 反混淆操作步骤
1. 打开需要反混淆的文件，选中要反混淆的文本。
2. 触发反混淆操作（可通过菜单栏 `ClarifyAction` 或快捷键 `ctrl shift C`）。
3. 如果 seed 未配置，会弹出提示框，引导用户到 Settings/Preferences -> Tools -> AES Encryption 中配置 seed。
4. 配置好 seed 后再次触发反混淆操作，反混淆后的文本会替换原选中的文本。

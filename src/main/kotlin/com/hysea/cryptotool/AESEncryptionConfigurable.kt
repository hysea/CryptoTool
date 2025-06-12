package com.hysea.cryptotool

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBLabel
import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField


class AESEncryptionConfigurable(private val project: Project) : Configurable {
    private val keyTextField = JTextField()

    // 添加用于 seed 输入的文本框
    private val seedTextField = JTextField()

    override fun getDisplayName(): String {
        return "AES Encryption"
    }

    override fun createComponent(): JComponent {
        keyTextField.preferredSize = Dimension(300, 50)
        seedTextField.preferredSize = Dimension(300, 50)
        val panel = JPanel()
        // 设置垂直布局
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        val keyPanel = JPanel()
        val label = JBLabel("AES Key:")
        keyPanel.add(label)
        keyPanel.add(keyTextField)
        panel.add(keyPanel)
        // 添加 seed 标签和输入框
        val seedPanel = JPanel()
        val seedLabel = JBLabel("Seed:")
        seedPanel.add(seedLabel)
        seedPanel.add(seedTextField)
        panel.add(seedPanel)
        return panel
    }

    override fun isModified(): Boolean {
        val settings = AESEncryptionSettings.getInstance(project)
        return keyTextField.text != settings.state.aesKey || seedTextField.text != settings.state.seed
    }

    override fun apply() {
        val settings = AESEncryptionSettings.getInstance(project)
        settings.state.aesKey = keyTextField.text
        // 保存 seed 到设置中
        settings.state.seed = seedTextField.text
    }

    override fun reset() {
        val settings = AESEncryptionSettings.getInstance(project)
        keyTextField.text = settings.state.aesKey
        // 从设置中恢复 seed
        seedTextField.text = settings.state.seed
    }

    private fun loadSettings() {
        val settings = project.getService(AESEncryptionSettings::class.java)
        keyTextField.text = settings.state.aesKey
        // 加载 seed
        seedTextField.text = settings.state.seed
    }
}
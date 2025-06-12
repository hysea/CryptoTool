package com.hysea.cryptotool

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

@Service(Service.Level.PROJECT)
@State(name = "AESEncryptionSettings", storages = [Storage("aes_encryption_settings.xml")])
class AESEncryptionSettings : PersistentStateComponent<AESEncryptionSettings.State> {
    @get:JvmName("myState")
    var state = State()

    class State {
        var aesKey: String? = null
        var seed:String? = null
    }

    override fun getState(): State {
        return state
    }

    override fun loadState(state: State) {
        XmlSerializerUtil.copyBean(state, this.state)
    }

    companion object {
        fun getInstance(project: Project): AESEncryptionSettings {
            return project.getService(AESEncryptionSettings::class.java)
        }
    }
}
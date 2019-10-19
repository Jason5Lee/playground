package helloworld

import javafx.scene.Parent
import tornadofx.View
import tornadofx.hbox
import tornadofx.label

class HelloWorldView : View() {
    override val root: Parent = hbox {
        label("Hello world")
    }
}
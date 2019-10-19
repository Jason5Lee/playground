package table

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.paint.Color
import tornadofx.*
import java.time.LocalDate
import java.time.Period

class Person(id: Int, name: String, birthday: LocalDate) {
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

    val birthdayProperty = SimpleObjectProperty(birthday)
    var birthday by birthdayProperty

    // Make age an observable value as well
    val ageProperty = birthdayProperty.objectBinding { Period.between(it, LocalDate.now()).years }
}

class TableView : View() {
    private val persons = listOf(
        Person(1, "Samantha Stuart", LocalDate.of(1991, 12, 4)),
        Person(2, "Tom Marks", LocalDate.of(2011, 1, 23)),
        Person(3, "Stuart Gills", LocalDate.of(1999, 5, 23)),
        Person(3, "Nicole Williams", LocalDate.of(2008, 8, 11))
    ).observable()

    override val root = tableview(persons) {
        column("ID", Person::idProperty)
        column("Name", Person::nameProperty)
        column("Birthday", Person::birthdayProperty)
        column("Age", Person::ageProperty).cellFormat {
            text = it.toString()
            style {
                if (it != null && it < 18) {
                    backgroundColor += c("#8b0000")
                    textFill = Color.WHITE
                } else {
                    backgroundColor += Color.WHITE
                    textFill = Color.BLACK
                }
            }
        }
    }
}
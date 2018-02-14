package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.util.TestUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortCommandTest {

    private AddressBook addressBook;
    private List<ReadOnlyPerson> rawList;
    private List<ReadOnlyPerson> sortedlist;
    private SortCommand sort;

    @Before
    public void init() throws Exception {
        Person anhHuynh = new Person(new Name("Anh Huynh"), new Phone("12345678", false),
                new Email("anhhuynh@nus.com", false),
                new Address("13 Computing Drive Singapore 117417", false), new UniqueTagList());
        Person hieuLe = new Person(new Name("Hieu Le"), new Phone("12345678", false),
                new Email("hieule@nus.com", false),
                new Address("13 Computing Drive Singapore 117417", false), new UniqueTagList());
        Person hoangNguyen = new Person(new Name("Hoang Nguyen"), new Phone("12345678", false),
                new Email("hoangnguyen@nus.com", false),
                new Address("13 Computing Drive Singapore 117417", false), new UniqueTagList());

        this.addressBook = TestUtil.createAddressBook(hieuLe, anhHuynh, hoangNguyen);
        this.rawList = TestUtil.createList(hieuLe, anhHuynh, hoangNguyen);
        this.sortedlist = TestUtil.createList(anhHuynh, hieuLe, hoangNguyen);

    }

    @Test
    public void execute() throws IllegalValueException {
        assertSortCommandBehaviour(sortedlist);
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertSortCommandBehaviour(List<ReadOnlyPerson> expectedList) {
        SortCommand command = createSortCommand(addressBook, rawList);
        CommandResult result = command.execute();

        assertEquals(result.getRelevantPersons().orElse(null), expectedList);
    }

    /**
    * Creates a new sort command.*/
    private SortCommand createSortCommand(AddressBook addressBook, List<ReadOnlyPerson> rawListList) {
        SortCommand command = new SortCommand();
        command.setData(addressBook, rawList);

        return command;
    }
}
# Delegation in Kotlin
## Task: Building a Simple Messaging System
### Class Delegation: Message Sender

Create a messaging system where different
types of messages (e.g., SMS, Email) are
sent using a MessageSender interface.
You need to implement two classes,
SmsSender and EmailSender,
that send messages through different
channels.

### Property Delegation: Message Logger

Enhance your messaging system by adding
a logging feature that tracks how many
messages have been sent. Use property
delegation to keep track of the count.

### Expected Outcome:
Class Delegation: You should be able to
create a MessageService instance that
can send messages via SMS or Email by
delegating the task to the respective
sender class.

Property Delegation: The MessageService
should accurately track and display
the number of messages sent using property
delegation.
package com.smart.tradingbot.common.disposed
//
//import android.content.Intent
//import android.database.Cursor
//import android.net.Uri
//import android.provider.ContactsContract
//
//var uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode("+1 ".plus(myFanTextMobileNumber.value)))
//val cursor: Cursor? = context?.contentResolver
//    .query(ContactsContract.Contacts.CONTENT_URI, arrayOf(ContactsContract.Contacts.LOOKUP_KEY), null, null, null)
//
//        // The Cursor that contains the Contact row
//        var mCursor: Cursor? = null
//        // The index of the lookup key column in the cursor
//        var lookupKeyIndex: Int = 0
//        // The index of the contact's _ID value
//        var idIndex: Int = 0
//        // The lookup key from the Cursor
//        var currentLookupKey: String? = null
//        // The _ID value from the Cursor
//        var currentId: Long = 0
//        // A content URI pointing to the contact
//        var selectedContactUri: Uri? = null
//        /*
//         * Once the user has selected a contact to edit,
//         * this gets the contact's lookup key and _ID values from the
//         * cursor and creates the necessary URI.
//         */
//        mCursor?.apply {
//            // Gets the lookup key column index
//            lookupKeyIndex = getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)
//            // Gets the lookup key value
//            currentLookupKey = getString(lookupKeyIndex)
//            Log.d("RestApi",currentLookupKey.toString())
//            // Gets the _ID column index
//            idIndex = getColumnIndex(ContactsContract.Contacts._ID)
//            currentId = getLong(idIndex)
//            selectedContactUri = ContactsContract.Contacts.getLookupUri(currentId, currentLookupKey)
//        }
//
// Creates a new Intent to edit a contact
//        val intent = Intent(Intent.ACTION_EDIT).apply {
//            /*
//             * Sets the contact URI to edit, and the data type that the
//             * Intent must match
//             */
//            setDataAndType(selectedContactUri, ContactsContract.Contacts.CONTENT_ITEM_TYPE)
//        }
//        resolver.query(uri, String[]{ ContactsContract.PhoneLookup.DISPLAY_NAME})
//val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
//    // Sets the MIME type to match the Contacts Provider
//    type = ContactsContract.RawContacts.CONTENT_TYPE
//
//}
//        val intent = Intent(Intent.ACTION_INSERT_OR_EDIT).apply {
//            // Sets the MIME type
//            type = ContactsContract.Contacts.CONTENT_ITEM_TYPE
//        }
//
//intent.apply {
//    putExtra(ContactsContract.Intents.Insert.NAME, userName.value)
//    putExtra(ContactsContract.Intents.Insert.EMAIL, emailName.value)
//    putExtra(ContactsContract.Intents.Insert.PHONE, "+1 ".plus(myFanTextMobileNumber.value))
//    putExtra(ContactsContract.Intents.Insert.COMPANY, companyName.value)
//    putExtra(ContactsContract.Intents.Insert.ACTION, urlName.value)
//    putExtra(ContactsContract.Intents.Insert.DATA, "FanTextUser")
//    putExtra(ContactsContract.Intents.Insert.FULL_MODE, apartmentName.value)
//    putExtra(ContactsContract.Intents.Insert.POSTAL, zipName.value)
//    putExtra(
//        ContactsContract.Intents.Insert.EMAIL_TYPE,
//        ContactsContract.CommonDataKinds.Email.TYPE_WORK
//    )
//}
//context.startActivity(intent)
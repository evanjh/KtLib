@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Finishes user registration
 * Works only when the current authorization state is authorizationStateWaitRegistration
 *
 * @firstName - The first name of the user
 * @lastName - The last name of the user
 */
suspend fun TdHandler.registerUser(
    firstName: String? = null,
    lastName: String? = null
) = sync<Ok>(RegisterUser(firstName, lastName))

suspend fun TdHandler.registerUserOrNull(
    firstName: String? = null,
    lastName: String? = null
) = syncOrNull<Ok>(RegisterUser(firstName, lastName))

fun TdHandler.registerUserWith(
    firstName: String? = null,
    lastName: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RegisterUser(firstName, lastName), stackIgnore + 1, submit)

/**
 * Returns the current user
 */
suspend fun TdHandler.getMe() = sync<User>(GetMe())

suspend fun TdHandler.getMeOrNull() = syncOrNull<User>(GetMe())

fun TdHandler.getMeWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<User>.() -> Unit)? = null
) = send(GetMe(), stackIgnore + 1, submit)

/**
 * Returns information about a user by their identifier
 * This is an offline request if the current user is not a bot
 *
 * @userId - User identifier
 */
suspend fun TdHandler.getUser(
    userId: Int
) = sync<User>(GetUser(userId))

suspend fun TdHandler.getUserOrNull(
    userId: Int
) = syncOrNull<User>(GetUser(userId))

fun TdHandler.getUserWith(
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<User>.() -> Unit)? = null
) = send(GetUser(userId), stackIgnore + 1, submit)

/**
 * Returns full information about a user by their identifier
 *
 * @userId - User identifier
 */
suspend fun TdHandler.getUserFullInfo(
    userId: Int
) = sync<UserFullInfo>(GetUserFullInfo(userId))

suspend fun TdHandler.getUserFullInfoOrNull(
    userId: Int
) = syncOrNull<UserFullInfo>(GetUserFullInfo(userId))

fun TdHandler.getUserFullInfoWith(
    userId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<UserFullInfo>.() -> Unit)? = null
) = send(GetUserFullInfo(userId), stackIgnore + 1, submit)

/**
 * Returns users voted for the specified option in a non-anonymous polls
 * For the optimal performance the number of returned users is chosen by the library
 *
 * @chatId - Identifier of the chat to which the poll belongs
 * @messageId - Identifier of the message containing the poll
 * @optionId - 0-based identifier of the answer option
 * @offset - Number of users to skip in the result
 * @limit - The maximum number of users to be returned
 *          Must be positive and can't be greater than 50
 *          Fewer users may be returned than specified by the limit, even if the end of the voter list has not been reached
 */
suspend fun TdHandler.getPollVoters(
    chatId: Long,
    messageId: Long,
    optionId: Int,
    offset: Int,
    limit: Int
) = sync<Users>(GetPollVoters(chatId, messageId, optionId, offset, limit))

suspend fun TdHandler.getPollVotersOrNull(
    chatId: Long,
    messageId: Long,
    optionId: Int,
    offset: Int,
    limit: Int
) = syncOrNull<Users>(GetPollVoters(chatId, messageId, optionId, offset, limit))

fun TdHandler.getPollVotersWith(
    chatId: Long,
    messageId: Long,
    optionId: Int,
    offset: Int,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Users>.() -> Unit)? = null
) = send(GetPollVoters(chatId, messageId, optionId, offset, limit), stackIgnore + 1, submit)

/**
 * Returns all user contacts
 */
suspend fun TdHandler.getContacts() = sync<Users>(GetContacts())

suspend fun TdHandler.getContactsOrNull() = syncOrNull<Users>(GetContacts())

fun TdHandler.getContactsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Users>.() -> Unit)? = null
) = send(GetContacts(), stackIgnore + 1, submit)

/**
 * Searches for the specified query in the first names, last names and usernames of the known user contacts
 *
 * @query - Query to search for
 *          May be empty to return all contacts
 * @limit - The maximum number of users to be returned
 */
suspend fun TdHandler.searchContacts(
    query: String? = null,
    limit: Int
) = sync<Users>(SearchContacts(query, limit))

suspend fun TdHandler.searchContactsOrNull(
    query: String? = null,
    limit: Int
) = syncOrNull<Users>(SearchContacts(query, limit))

fun TdHandler.searchContactsWith(
    query: String? = null,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Users>.() -> Unit)? = null
) = send(SearchContacts(query, limit), stackIgnore + 1, submit)

/**
 * Returns up to 20 recently used inline bots in the order of their last usage
 */
suspend fun TdHandler.getRecentInlineBots() = sync<Users>(GetRecentInlineBots())

suspend fun TdHandler.getRecentInlineBotsOrNull() = syncOrNull<Users>(GetRecentInlineBots())

fun TdHandler.getRecentInlineBotsWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<Users>.() -> Unit)? = null
) = send(GetRecentInlineBots(), stackIgnore + 1, submit)

/**
 * Changes the first and last name of the current user
 *
 * @firstName - The new value of the first name for the user
 * @lastName - The new value of the optional last name for the user
 */
suspend fun TdHandler.setName(
    firstName: String? = null,
    lastName: String? = null
) = sync<Ok>(SetName(firstName, lastName))

suspend fun TdHandler.setNameOrNull(
    firstName: String? = null,
    lastName: String? = null
) = syncOrNull<Ok>(SetName(firstName, lastName))

fun TdHandler.setNameWith(
    firstName: String? = null,
    lastName: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetName(firstName, lastName), stackIgnore + 1, submit)

/**
 * Changes the bio of the current user
 *
 * @bio - The new value of the user bio
 */
suspend fun TdHandler.setBio(
    bio: String? = null
) = sync<Ok>(SetBio(bio))

suspend fun TdHandler.setBioOrNull(
    bio: String? = null
) = syncOrNull<Ok>(SetBio(bio))

fun TdHandler.setBioWith(
    bio: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetBio(bio), stackIgnore + 1, submit)

/**
 * Changes the username of the current user
 *
 * @username - The new value of the username
 *             Use an empty string to remove the username
 */
suspend fun TdHandler.setUsername(
    username: String? = null
) = sync<Ok>(SetUsername(username))

suspend fun TdHandler.setUsernameOrNull(
    username: String? = null
) = syncOrNull<Ok>(SetUsername(username))

fun TdHandler.setUsernameWith(
    username: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetUsername(username), stackIgnore + 1, submit)

/**
 * Returns a user that can be contacted to get support
 */
suspend fun TdHandler.getSupportUser() = sync<User>(GetSupportUser())

suspend fun TdHandler.getSupportUserOrNull() = syncOrNull<User>(GetSupportUser())

fun TdHandler.getSupportUserWith(
    stackIgnore: Int = 0,
    submit: (TdCallback<User>.() -> Unit)? = null
) = send(GetSupportUser(), stackIgnore + 1, submit)

/**
 * Changes user privacy settings
 *
 * @setting - The privacy setting
 * @rules - The new privacy rules
 */
suspend fun TdHandler.setUserPrivacySettingRules(
    setting: UserPrivacySetting? = null,
    rules: UserPrivacySettingRules? = null
) = sync<Ok>(SetUserPrivacySettingRules(setting, rules))

suspend fun TdHandler.setUserPrivacySettingRulesOrNull(
    setting: UserPrivacySetting? = null,
    rules: UserPrivacySettingRules? = null
) = syncOrNull<Ok>(SetUserPrivacySettingRules(setting, rules))

fun TdHandler.setUserPrivacySettingRulesWith(
    setting: UserPrivacySetting? = null,
    rules: UserPrivacySettingRules? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetUserPrivacySettingRules(setting, rules), stackIgnore + 1, submit)

/**
 * Returns the current privacy settings
 *
 * @setting - The privacy setting
 */
suspend fun TdHandler.getUserPrivacySettingRules(
    setting: UserPrivacySetting? = null
) = sync<UserPrivacySettingRules>(GetUserPrivacySettingRules(setting))

suspend fun TdHandler.getUserPrivacySettingRulesOrNull(
    setting: UserPrivacySetting? = null
) = syncOrNull<UserPrivacySettingRules>(GetUserPrivacySettingRules(setting))

fun TdHandler.getUserPrivacySettingRulesWith(
    setting: UserPrivacySetting? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<UserPrivacySettingRules>.() -> Unit)? = null
) = send(GetUserPrivacySettingRules(setting), stackIgnore + 1, submit)

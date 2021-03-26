@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a basic group by its identifier
 * This is an offline request if the current user is not a bot
 *
 * @basicGroupId - Basic group identifier
 */
suspend fun TdHandler.getBasicGroup(
    basicGroupId: Int
) = sync(GetBasicGroup(basicGroupId))

suspend fun TdHandler.getBasicGroupOrNull(
    basicGroupId: Int
) = syncOrNull(GetBasicGroup(basicGroupId))

fun TdHandler.getBasicGroupWith(
    basicGroupId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<BasicGroup>.() -> Unit)? = null
) = send(GetBasicGroup(basicGroupId), stackIgnore + 1, submit)

/**
 * Returns full information about a basic group by its identifier
 *
 * @basicGroupId - Basic group identifier
 */
suspend fun TdHandler.getBasicGroupFullInfo(
    basicGroupId: Int
) = sync(GetBasicGroupFullInfo(basicGroupId))

suspend fun TdHandler.getBasicGroupFullInfoOrNull(
    basicGroupId: Int
) = syncOrNull(GetBasicGroupFullInfo(basicGroupId))

fun TdHandler.getBasicGroupFullInfoWith(
    basicGroupId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<BasicGroupFullInfo>.() -> Unit)? = null
) = send(GetBasicGroupFullInfo(basicGroupId), stackIgnore + 1, submit)

/**
 * Creates a voice chat (a group call bound to a chat)
 * Available only for basic groups, supergroups and channels
 * Requires can_manage_voice_chats rights
 *
 * @chatId - Chat identifier
 */
suspend fun TdHandler.createVoiceChat(
    chatId: Long
) = sync(CreateVoiceChat(chatId))

suspend fun TdHandler.createVoiceChatOrNull(
    chatId: Long
) = syncOrNull(CreateVoiceChat(chatId))

fun TdHandler.createVoiceChatWith(
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<GroupCallId>.() -> Unit)? = null
) = send(CreateVoiceChat(chatId), stackIgnore + 1, submit)

/**
 * Returns information about a group call
 *
 * @groupCallId - Group call identifier
 */
suspend fun TdHandler.getGroupCall(
    groupCallId: Int
) = sync(GetGroupCall(groupCallId))

suspend fun TdHandler.getGroupCallOrNull(
    groupCallId: Int
) = syncOrNull(GetGroupCall(groupCallId))

fun TdHandler.getGroupCallWith(
    groupCallId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<GroupCall>.() -> Unit)? = null
) = send(GetGroupCall(groupCallId), stackIgnore + 1, submit)

/**
 * Joins a group call
 *
 * @groupCallId - Group call identifier
 * @participantAlias - Identifier of the group call participant, which will be used to join the call
 *                     Voice chats only
 * @payload - Group join payload
 *            Received from tgcalls
 * @source - Caller synchronization source identifier
 *           Received from tgcalls
 * @isMuted - True, if the user's microphone is muted
 * @inviteHash - If non-empty, invite hash to be used to join the group call without being muted by administrators
 */
suspend fun TdHandler.joinGroupCall(
    groupCallId: Int,
    participantAlias: MessageSender? = null,
    payload: GroupCallPayload? = null,
    source: Int,
    isMuted: Boolean,
    inviteHash: String? = null
) = sync(JoinGroupCall(groupCallId, participantAlias, payload, source, isMuted, inviteHash))

suspend fun TdHandler.joinGroupCallOrNull(
    groupCallId: Int,
    participantAlias: MessageSender? = null,
    payload: GroupCallPayload? = null,
    source: Int,
    isMuted: Boolean,
    inviteHash: String? = null
) = syncOrNull(JoinGroupCall(groupCallId, participantAlias, payload, source, isMuted, inviteHash))

fun TdHandler.joinGroupCallWith(
    groupCallId: Int,
    participantAlias: MessageSender? = null,
    payload: GroupCallPayload? = null,
    source: Int,
    isMuted: Boolean,
    inviteHash: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<GroupCallJoinResponse>.() -> Unit)? = null
) = send(JoinGroupCall(groupCallId, participantAlias, payload, source, isMuted, inviteHash), stackIgnore + 1, submit)

/**
 * Sets group call title
 * Requires groupCall.can_be_managed group call flag
 *
 * @groupCallId - Group call identifier
 * @title - New group call title
 */
suspend fun TdHandler.setGroupCallTitle(
    groupCallId: Int,
    title: String? = null
){
    sync(SetGroupCallTitle(groupCallId, title))
}


suspend fun TdHandler.setGroupCallTitleIgnoreException(
    groupCallId: Int,
    title: String? = null
){
    syncOrNull(SetGroupCallTitle(groupCallId, title))
}


fun TdHandler.setGroupCallTitleWith(
    groupCallId: Int,
    title: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetGroupCallTitle(groupCallId, title), stackIgnore + 1, submit)

/**
 * Toggles whether new participants of a group call can be unmuted only by administrators of the group call
 * Requires groupCall.can_change_mute_new_participants group call flag
 *
 * @groupCallId - Group call identifier
 * @muteNewParticipants - New value of the mute_new_participants setting
 */
suspend fun TdHandler.toggleGroupCallMuteNewParticipants(
    groupCallId: Int,
    muteNewParticipants: Boolean
){
    sync(ToggleGroupCallMuteNewParticipants(groupCallId, muteNewParticipants))
}


suspend fun TdHandler.toggleGroupCallMuteNewParticipantsIgnoreException(
    groupCallId: Int,
    muteNewParticipants: Boolean
){
    syncOrNull(ToggleGroupCallMuteNewParticipants(groupCallId, muteNewParticipants))
}


fun TdHandler.toggleGroupCallMuteNewParticipantsWith(
    groupCallId: Int,
    muteNewParticipants: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleGroupCallMuteNewParticipants(groupCallId, muteNewParticipants), stackIgnore + 1, submit)

/**
 * Revokes invite link for a group call
 * Requires groupCall.can_be_managed group call flag
 *
 * @groupCallId - Group call identifier
 */
suspend fun TdHandler.revokeGroupCallInviteLink(
    groupCallId: Int
){
    sync(RevokeGroupCallInviteLink(groupCallId))
}


suspend fun TdHandler.revokeGroupCallInviteLinkIgnoreException(
    groupCallId: Int
){
    syncOrNull(RevokeGroupCallInviteLink(groupCallId))
}


fun TdHandler.revokeGroupCallInviteLinkWith(
    groupCallId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(RevokeGroupCallInviteLink(groupCallId), stackIgnore + 1, submit)

/**
 * Invites users to a group call
 * Sends a service message of type messageInviteToGroupCall for voice chats
 *
 * @groupCallId - Group call identifier
 * @userIds - User identifiers
 *            At most 10 users can be invited simultaneously
 */
suspend fun TdHandler.inviteGroupCallParticipants(
    groupCallId: Int,
    userIds: IntArray
){
    sync(InviteGroupCallParticipants(groupCallId, userIds))
}


suspend fun TdHandler.inviteGroupCallParticipantsIgnoreException(
    groupCallId: Int,
    userIds: IntArray
){
    syncOrNull(InviteGroupCallParticipants(groupCallId, userIds))
}


fun TdHandler.inviteGroupCallParticipantsWith(
    groupCallId: Int,
    userIds: IntArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(InviteGroupCallParticipants(groupCallId, userIds), stackIgnore + 1, submit)

/**
 * Returns invite link to a voice chat in a public chat
 *
 * @groupCallId - Group call identifier
 * @canSelfUnmute - Pass true if the invite_link should contain an invite hash, passing which to joinGroupCall would allow the invited user to unmute themself
 *                  Requires groupCall.can_be_managed group call flag
 */
suspend fun TdHandler.getGroupCallInviteLink(
    groupCallId: Int,
    canSelfUnmute: Boolean
) = sync(GetGroupCallInviteLink(groupCallId, canSelfUnmute))

suspend fun TdHandler.getGroupCallInviteLinkOrNull(
    groupCallId: Int,
    canSelfUnmute: Boolean
) = syncOrNull(GetGroupCallInviteLink(groupCallId, canSelfUnmute))

fun TdHandler.getGroupCallInviteLinkWith(
    groupCallId: Int,
    canSelfUnmute: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<HttpUrl>.() -> Unit)? = null
) = send(GetGroupCallInviteLink(groupCallId, canSelfUnmute), stackIgnore + 1, submit)

/**
 * Starts recording of a group call
 * Requires groupCall.can_be_managed group call flag
 *
 * @groupCallId - Group call identifier
 * @title - Group call recording title
 */
suspend fun TdHandler.startGroupCallRecording(
    groupCallId: Int,
    title: String? = null
){
    sync(StartGroupCallRecording(groupCallId, title))
}


suspend fun TdHandler.startGroupCallRecordingIgnoreException(
    groupCallId: Int,
    title: String? = null
){
    syncOrNull(StartGroupCallRecording(groupCallId, title))
}


fun TdHandler.startGroupCallRecordingWith(
    groupCallId: Int,
    title: String? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(StartGroupCallRecording(groupCallId, title), stackIgnore + 1, submit)

/**
 * Ends recording of a group call
 * Requires groupCall.can_be_managed group call flag
 *
 * @groupCallId - Group call identifier
 */
suspend fun TdHandler.endGroupCallRecording(
    groupCallId: Int
){
    sync(EndGroupCallRecording(groupCallId))
}


suspend fun TdHandler.endGroupCallRecordingIgnoreException(
    groupCallId: Int
){
    syncOrNull(EndGroupCallRecording(groupCallId))
}


fun TdHandler.endGroupCallRecordingWith(
    groupCallId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(EndGroupCallRecording(groupCallId), stackIgnore + 1, submit)

/**
 * Informs TDLib that a group call participant speaking state has changed
 *
 * @groupCallId - Group call identifier
 * @source - Group call participant's synchronization source identifier, or 0 for the current user
 * @isSpeaking - True, if the user is speaking
 */
suspend fun TdHandler.setGroupCallParticipantIsSpeaking(
    groupCallId: Int,
    source: Int,
    isSpeaking: Boolean
){
    sync(SetGroupCallParticipantIsSpeaking(groupCallId, source, isSpeaking))
}


suspend fun TdHandler.setGroupCallParticipantIsSpeakingIgnoreException(
    groupCallId: Int,
    source: Int,
    isSpeaking: Boolean
){
    syncOrNull(SetGroupCallParticipantIsSpeaking(groupCallId, source, isSpeaking))
}


fun TdHandler.setGroupCallParticipantIsSpeakingWith(
    groupCallId: Int,
    source: Int,
    isSpeaking: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetGroupCallParticipantIsSpeaking(groupCallId, source, isSpeaking), stackIgnore + 1, submit)

/**
 * Toggles whether a group call participant is muted, unmuted, or allowed to unmute themself
 *
 * @groupCallId - Group call identifier
 * @participant - Participant identifier
 * @isMuted - Pass true if the user must be muted and false otherwise
 */
suspend fun TdHandler.toggleGroupCallParticipantIsMuted(
    groupCallId: Int,
    participant: MessageSender? = null,
    isMuted: Boolean
){
    sync(ToggleGroupCallParticipantIsMuted(groupCallId, participant, isMuted))
}


suspend fun TdHandler.toggleGroupCallParticipantIsMutedIgnoreException(
    groupCallId: Int,
    participant: MessageSender? = null,
    isMuted: Boolean
){
    syncOrNull(ToggleGroupCallParticipantIsMuted(groupCallId, participant, isMuted))
}


fun TdHandler.toggleGroupCallParticipantIsMutedWith(
    groupCallId: Int,
    participant: MessageSender? = null,
    isMuted: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleGroupCallParticipantIsMuted(groupCallId, participant, isMuted), stackIgnore + 1, submit)

/**
 * Changes a group call participant's volume level
 * If the current user can manage the group call, then the participant's volume level will be changed for all users with default volume level
 *
 * @groupCallId - Group call identifier
 * @participant - Participant identifier
 * @volumeLevel - New participant's volume level
 */
suspend fun TdHandler.setGroupCallParticipantVolumeLevel(
    groupCallId: Int,
    participant: MessageSender? = null,
    volumeLevel: Int
){
    sync(SetGroupCallParticipantVolumeLevel(groupCallId, participant, volumeLevel))
}


suspend fun TdHandler.setGroupCallParticipantVolumeLevelIgnoreException(
    groupCallId: Int,
    participant: MessageSender? = null,
    volumeLevel: Int
){
    syncOrNull(SetGroupCallParticipantVolumeLevel(groupCallId, participant, volumeLevel))
}


fun TdHandler.setGroupCallParticipantVolumeLevelWith(
    groupCallId: Int,
    participant: MessageSender? = null,
    volumeLevel: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetGroupCallParticipantVolumeLevel(groupCallId, participant, volumeLevel), stackIgnore + 1, submit)

/**
 * Toggles whether a group call participant hand is rased
 *
 * @groupCallId - Group call identifier
 * @participant - Participant identifier
 * @isHandRaised - Pass true if the user's hand should be raised
 *                 Only self hand can be raised
 *                 Requires groupCall.can_be_managed group call flag to lower other's hand
 */
suspend fun TdHandler.toggleGroupCallParticipantIsHandRaised(
    groupCallId: Int,
    participant: MessageSender? = null,
    isHandRaised: Boolean
){
    sync(ToggleGroupCallParticipantIsHandRaised(groupCallId, participant, isHandRaised))
}


suspend fun TdHandler.toggleGroupCallParticipantIsHandRaisedIgnoreException(
    groupCallId: Int,
    participant: MessageSender? = null,
    isHandRaised: Boolean
){
    syncOrNull(ToggleGroupCallParticipantIsHandRaised(groupCallId, participant, isHandRaised))
}


fun TdHandler.toggleGroupCallParticipantIsHandRaisedWith(
    groupCallId: Int,
    participant: MessageSender? = null,
    isHandRaised: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(ToggleGroupCallParticipantIsHandRaised(groupCallId, participant, isHandRaised), stackIgnore + 1, submit)

/**
 * Loads more group call participants
 * The loaded participants will be received through updates
 * Use the field groupCall.loaded_all_participants to check whether all participants has already been loaded
 *
 * @groupCallId - Group call identifier
 *                The group call must be previously received through getGroupCall and must be joined or being joined
 * @limit - Maximum number of participants to load
 */
suspend fun TdHandler.loadGroupCallParticipants(
    groupCallId: Int,
    limit: Int
){
    sync(LoadGroupCallParticipants(groupCallId, limit))
}


suspend fun TdHandler.loadGroupCallParticipantsIgnoreException(
    groupCallId: Int,
    limit: Int
){
    syncOrNull(LoadGroupCallParticipants(groupCallId, limit))
}


fun TdHandler.loadGroupCallParticipantsWith(
    groupCallId: Int,
    limit: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(LoadGroupCallParticipants(groupCallId, limit), stackIgnore + 1, submit)

/**
 * Leaves a group call
 *
 * @groupCallId - Group call identifier
 */
suspend fun TdHandler.leaveGroupCall(
    groupCallId: Int
){
    sync(LeaveGroupCall(groupCallId))
}


suspend fun TdHandler.leaveGroupCallIgnoreException(
    groupCallId: Int
){
    syncOrNull(LeaveGroupCall(groupCallId))
}


fun TdHandler.leaveGroupCallWith(
    groupCallId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(LeaveGroupCall(groupCallId), stackIgnore + 1, submit)

/**
 * Discards a group call
 * Requires groupCall.can_be_managed
 *
 * @groupCallId - Group call identifier
 */
suspend fun TdHandler.discardGroupCall(
    groupCallId: Int
){
    sync(DiscardGroupCall(groupCallId))
}


suspend fun TdHandler.discardGroupCallIgnoreException(
    groupCallId: Int
){
    syncOrNull(DiscardGroupCall(groupCallId))
}


fun TdHandler.discardGroupCallWith(
    groupCallId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DiscardGroupCall(groupCallId), stackIgnore + 1, submit)

package td;

import org.jetbrains.annotations.*;

@SuppressWarnings("ALL")
public class TdApi {

    public static abstract class Object {

        public native String toString();

        public abstract int getConstructor();

    }

    public static abstract class Function extends Object{

        public native String toString();

    }

    /**
     * An object of this type can be returned on every function call, in case of an error
     *
     * @code - Error code
     *         Subject to future changes
     *         If the error code is 406, the error message must not be processed in any way and must not be displayed to the user
     * @message - Error message
     *            Subject to future changes
     */
    public static class Error extends Object {

        public int code;
        public String message;

        public Error() {}

        public Error(int code, String message) {

            this.code = code;
            this.message = message;

        }

        @Override
        public int getConstructor() { return -1679978726; }

    }


    /**
     * An object of this type is returned on a successful function call for certain functions
     */
    public static class Ok extends Object {

        @Override
        public int getConstructor() { return -722616727; }

    }


    /**
     * Contains parameters for TDLib initialization
     *
     * @useTestDc - If set to true, the Telegram test environment will be used instead of the production environment
     * @databaseDirectory - The path to the directory for the persistent database
     *                      If empty, the current working directory will be used
     * @filesDirectory - The path to the directory for storing files
     *                   If empty, database_directory will be used
     * @useFileDatabase - If set to true, information about downloaded and uploaded files will be saved between application restarts
     * @useChatInfoDatabase - If set to true, the library will maintain a cache of users, basic groups, supergroups, channels and secret chats
     *                        Implies use_file_database
     * @useMessageDatabase - If set to true, the library will maintain a cache of chats and messages
     *                       Implies use_chat_info_database
     * @useSecretChats - If set to true, support for secret chats will be enabled
     * @apiId - Application identifier for Telegram API access, which can be obtained at https://my.telegram.org
     * @apiHash - Application identifier hash for Telegram API access, which can be obtained at https://my.telegram.org
     * @systemLanguageCode - IETF language tag of the user's operating system language
     * @deviceModel - Model of the device the application is being run on
     * @systemVersion - Version of the operating system the application is being run on
     *                  If empty, the version is automatically detected by TDLib
     * @applicationVersion - Application version
     * @enableStorageOptimizer - If set to true, old files will automatically be deleted
     * @ignoreFileNames - If set to true, original file names will be ignored
     *                    Otherwise, downloaded files will be saved under names as close as possible to the original name
     */
    public static class TdlibParameters extends Object {

        public boolean useTestDc;
        public String databaseDirectory;
        public String filesDirectory;
        public boolean useFileDatabase;
        public boolean useChatInfoDatabase;
        public boolean useMessageDatabase;
        public boolean useSecretChats;
        public int apiId;
        public String apiHash;
        public String systemLanguageCode;
        public String deviceModel;
        public String systemVersion;
        public String applicationVersion;
        public boolean enableStorageOptimizer;
        public boolean ignoreFileNames;

        public TdlibParameters() {}

        public TdlibParameters(boolean useTestDc, String databaseDirectory, String filesDirectory, boolean useFileDatabase, boolean useChatInfoDatabase, boolean useMessageDatabase, boolean useSecretChats, int apiId, String apiHash, String systemLanguageCode, String deviceModel, String systemVersion, String applicationVersion, boolean enableStorageOptimizer, boolean ignoreFileNames) {

            this.useTestDc = useTestDc;
            this.databaseDirectory = databaseDirectory;
            this.filesDirectory = filesDirectory;
            this.useFileDatabase = useFileDatabase;
            this.useChatInfoDatabase = useChatInfoDatabase;
            this.useMessageDatabase = useMessageDatabase;
            this.useSecretChats = useSecretChats;
            this.apiId = apiId;
            this.apiHash = apiHash;
            this.systemLanguageCode = systemLanguageCode;
            this.deviceModel = deviceModel;
            this.systemVersion = systemVersion;
            this.applicationVersion = applicationVersion;
            this.enableStorageOptimizer = enableStorageOptimizer;
            this.ignoreFileNames = ignoreFileNames;

        }

        @Override
        public int getConstructor() { return -761520773; }

    }


    /**
     * Provides information about the method by which an authentication code is delivered to the user
     */
    public static abstract class AuthenticationCodeType extends Object {}

    /**
     * An authentication code is delivered via a private Telegram message, which can be viewed from another active session
     *
     * @length - Length of the code
     */
    public static class AuthenticationCodeTypeTelegramMessage extends AuthenticationCodeType {

        public int length;

        public AuthenticationCodeTypeTelegramMessage() {}

        public AuthenticationCodeTypeTelegramMessage(int length) {

            this.length = length;

        }

        @Override
        public int getConstructor() { return 2079628074; }

    }


    /**
     * An authentication code is delivered via an SMS message to the specified phone number
     *
     * @length - Length of the code
     */
    public static class AuthenticationCodeTypeSms extends AuthenticationCodeType {

        public int length;

        public AuthenticationCodeTypeSms() {}

        public AuthenticationCodeTypeSms(int length) {

            this.length = length;

        }

        @Override
        public int getConstructor() { return 962650760; }

    }


    /**
     * An authentication code is delivered via a phone call to the specified phone number
     *
     * @length - Length of the code
     */
    public static class AuthenticationCodeTypeCall extends AuthenticationCodeType {

        public int length;

        public AuthenticationCodeTypeCall() {}

        public AuthenticationCodeTypeCall(int length) {

            this.length = length;

        }

        @Override
        public int getConstructor() { return 1636265063; }

    }


    /**
     * An authentication code is delivered by an immediately cancelled call to the specified phone number
     * The number from which the call was made is the code
     *
     * @pattern - Pattern of the phone number from which the call will be made
     */
    public static class AuthenticationCodeTypeFlashCall extends AuthenticationCodeType {

        public String pattern;

        public AuthenticationCodeTypeFlashCall() {}

        public AuthenticationCodeTypeFlashCall(String pattern) {

            this.pattern = pattern;

        }

        @Override
        public int getConstructor() { return 1395882402; }

    }


    /**
     * Information about the authentication code that was sent
     *
     * @phoneNumber - A phone number that is being authenticated
     * @type - Describes the way the code was sent to the user
     * @nextType - Describes the way the next code will be sent to the user
     * @timeout - Timeout before the code should be re-sent, in seconds
     */
    public static class AuthenticationCodeInfo extends Object {

        public String phoneNumber;
        public AuthenticationCodeType type;
        @Nullable public AuthenticationCodeType nextType;
        public int timeout;

        public AuthenticationCodeInfo() {}

        public AuthenticationCodeInfo(String phoneNumber, AuthenticationCodeType type, @Nullable AuthenticationCodeType nextType, int timeout) {

            this.phoneNumber = phoneNumber;
            this.type = type;
            this.nextType = nextType;
            this.timeout = timeout;

        }

        @Override
        public int getConstructor() { return -860345416; }

    }


    /**
     * Information about the email address authentication code that was sent
     *
     * @emailAddressPattern - Pattern of the email address to which an authentication code was sent
     * @length - Length of the code
     *           0 if unknown
     */
    public static class EmailAddressAuthenticationCodeInfo extends Object {

        public String emailAddressPattern;
        public int length;

        public EmailAddressAuthenticationCodeInfo() {}

        public EmailAddressAuthenticationCodeInfo(String emailAddressPattern, int length) {

            this.emailAddressPattern = emailAddressPattern;
            this.length = length;

        }

        @Override
        public int getConstructor() { return 1151066659; }

    }


    /**
     * Represents a part of the text that needs to be formatted in some unusual way
     *
     * @offset - Offset of the entity, in UTF-16 code units
     * @length - Length of the entity, in UTF-16 code units
     * @type - Type of the entity
     */
    public static class TextEntity extends Object {

        public int offset;
        public int length;
        public TextEntityType type;

        public TextEntity() {}

        public TextEntity(int offset, int length, TextEntityType type) {

            this.offset = offset;
            this.length = length;
            this.type = type;

        }

        @Override
        public int getConstructor() { return -1951688280; }

    }


    /**
     * Contains a list of text entities
     *
     * @entities - List of text entities
     */
    public static class TextEntities extends Object {

        public TextEntity[] entities;

        public TextEntities() {}

        public TextEntities(TextEntity[] entities) {

            this.entities = entities;

        }

        @Override
        public int getConstructor() { return -933199172; }

    }


    /**
     * A text with some entities
     *
     * @text - The text
     * @entities - Entities contained in the text
     *             Entities can be nested, but must not mutually intersect with each other
     *             Pre, Code and PreCode entities can't contain other entities
     *             Bold, Italic, Underline and Strikethrough entities can contain and to be contained in all other entities
     *             All other entities can't contain each other
     */
    public static class FormattedText extends Object {

        public String text;
        public TextEntity[] entities;

        public FormattedText() {}

        public FormattedText(String text, TextEntity[] entities) {

            this.text = text;
            this.entities = entities;

        }

        @Override
        public int getConstructor() { return -252624564; }

    }


    /**
     * Contains Telegram terms of service
     *
     * @text - Text of the terms of service
     * @minUserAge - The minimum age of a user to be able to accept the terms
     *               0 if any
     * @showPopup - True, if a blocking popup with terms of service must be shown to the user
     */
    public static class TermsOfService extends Object {

        public FormattedText text;
        public int minUserAge;
        public boolean showPopup;

        public TermsOfService() {}

        public TermsOfService(FormattedText text, int minUserAge, boolean showPopup) {

            this.text = text;
            this.minUserAge = minUserAge;
            this.showPopup = showPopup;

        }

        @Override
        public int getConstructor() { return 739422597; }

    }


    /**
     * Represents the current authorization state of the TDLib client
     */
    public static abstract class AuthorizationState extends Object {}

    /**
     * TDLib needs TdlibParameters for initialization
     */
    public static class AuthorizationStateWaitTdlibParameters extends AuthorizationState {

        @Override
        public int getConstructor() { return 904720988; }

    }


    /**
     * TDLib needs an encryption key to decrypt the local database
     *
     * @isEncrypted - True, if the database is currently encrypted
     */
    public static class AuthorizationStateWaitEncryptionKey extends AuthorizationState {

        public boolean isEncrypted;

        public AuthorizationStateWaitEncryptionKey() {}

        public AuthorizationStateWaitEncryptionKey(boolean isEncrypted) {

            this.isEncrypted = isEncrypted;

        }

        @Override
        public int getConstructor() { return 612103496; }

    }


    /**
     * TDLib needs the user's phone number to authorize
     * Call `setAuthenticationPhoneNumber` to provide the phone number, or use `requestQrCodeAuthentication`, or `checkAuthenticationBotToken` for other authentication options
     */
    public static class AuthorizationStateWaitPhoneNumber extends AuthorizationState {

        @Override
        public int getConstructor() { return 306402531; }

    }


    /**
     * TDLib needs the user's authentication code to authorize
     *
     * @codeInfo - Information about the authorization code that was sent
     */
    public static class AuthorizationStateWaitCode extends AuthorizationState {

        public AuthenticationCodeInfo codeInfo;

        public AuthorizationStateWaitCode() {}

        public AuthorizationStateWaitCode(AuthenticationCodeInfo codeInfo) {

            this.codeInfo = codeInfo;

        }

        @Override
        public int getConstructor() { return 52643073; }

    }


    /**
     * The user needs to confirm authorization on another logged in device by scanning a QR code with the provided link
     *
     * @link - A tg:// URL for the QR code
     *         The link will be updated frequently
     */
    public static class AuthorizationStateWaitOtherDeviceConfirmation extends AuthorizationState {

        public String link;

        public AuthorizationStateWaitOtherDeviceConfirmation() {}

        public AuthorizationStateWaitOtherDeviceConfirmation(String link) {

            this.link = link;

        }

        @Override
        public int getConstructor() { return 860166378; }

    }


    /**
     * The user is unregistered and need to accept terms of service and enter their first name and last name to finish registration
     *
     * @termsOfService - Telegram terms of service
     */
    public static class AuthorizationStateWaitRegistration extends AuthorizationState {

        public TermsOfService termsOfService;

        public AuthorizationStateWaitRegistration() {}

        public AuthorizationStateWaitRegistration(TermsOfService termsOfService) {

            this.termsOfService = termsOfService;

        }

        @Override
        public int getConstructor() { return 550350511; }

    }


    /**
     * The user has been authorized, but needs to enter a password to start using the application
     *
     * @passwordHint - Hint for the password
     * @hasRecoveryEmailAddress - True, if a recovery email address has been set up
     * @recoveryEmailAddressPattern - Pattern of the email address to which the recovery email was sent
     *                                Empty until a recovery email has been sent
     */
    public static class AuthorizationStateWaitPassword extends AuthorizationState {

        @Nullable public String passwordHint;
        public boolean hasRecoveryEmailAddress;
        public String recoveryEmailAddressPattern;

        public AuthorizationStateWaitPassword() {}

        public AuthorizationStateWaitPassword(@Nullable String passwordHint, boolean hasRecoveryEmailAddress, String recoveryEmailAddressPattern) {

            this.passwordHint = passwordHint;
            this.hasRecoveryEmailAddress = hasRecoveryEmailAddress;
            this.recoveryEmailAddressPattern = recoveryEmailAddressPattern;

        }

        @Override
        public int getConstructor() { return 187548796; }

    }


    /**
     * The user has been successfully authorized
     * TDLib is now ready to answer queries
     */
    public static class AuthorizationStateReady extends AuthorizationState {

        @Override
        public int getConstructor() { return -1834871737; }

    }


    /**
     * The user is currently logging out
     */
    public static class AuthorizationStateLoggingOut extends AuthorizationState {

        @Override
        public int getConstructor() { return 154449270; }

    }


    /**
     * TDLib is closing, all subsequent queries will be answered with the error 500
     * Note that closing TDLib can take a while
     * All resources will be freed only after authorizationStateClosed has been received
     */
    public static class AuthorizationStateClosing extends AuthorizationState {

        @Override
        public int getConstructor() { return 445855311; }

    }


    /**
     * TDLib client is in its final state
     * All databases are closed and all resources are released
     * No other updates will be received after this
     * All queries will be responded to with error code 500
     * To continue working, one should create a new instance of the TDLib client
     */
    public static class AuthorizationStateClosed extends AuthorizationState {

        @Override
        public int getConstructor() { return 1526047584; }

    }


    /**
     * Represents the current state of 2-step verification
     *
     * @hasPassword - True, if a 2-step verification password is set
     * @passwordHint - Hint for the password
     * @hasRecoveryEmailAddress - True, if a recovery email is set
     * @hasPassportData - True, if some Telegram Passport elements were saved
     * @recoveryEmailAddressCodeInfo - Information about the recovery email address to which the confirmation email was sent
     */
    public static class PasswordState extends Object {

        public boolean hasPassword;
        @Nullable public String passwordHint;
        public boolean hasRecoveryEmailAddress;
        public boolean hasPassportData;
        @Nullable public EmailAddressAuthenticationCodeInfo recoveryEmailAddressCodeInfo;

        public PasswordState() {}

        public PasswordState(boolean hasPassword, @Nullable String passwordHint, boolean hasRecoveryEmailAddress, boolean hasPassportData, @Nullable EmailAddressAuthenticationCodeInfo recoveryEmailAddressCodeInfo) {

            this.hasPassword = hasPassword;
            this.passwordHint = passwordHint;
            this.hasRecoveryEmailAddress = hasRecoveryEmailAddress;
            this.hasPassportData = hasPassportData;
            this.recoveryEmailAddressCodeInfo = recoveryEmailAddressCodeInfo;

        }

        @Override
        public int getConstructor() { return -1154797731; }

    }


    /**
     * Contains information about the current recovery email address
     *
     * @recoveryEmailAddress - Recovery email address
     */
    public static class RecoveryEmailAddress extends Object {

        public String recoveryEmailAddress;

        public RecoveryEmailAddress() {}

        public RecoveryEmailAddress(String recoveryEmailAddress) {

            this.recoveryEmailAddress = recoveryEmailAddress;

        }

        @Override
        public int getConstructor() { return 1290526187; }

    }


    /**
     * Returns information about the availability of a temporary password, which can be used for payments
     *
     * @hasPassword - True, if a temporary password is available
     * @validFor - Time left before the temporary password expires, in seconds
     */
    public static class TemporaryPasswordState extends Object {

        public boolean hasPassword;
        public int validFor;

        public TemporaryPasswordState() {}

        public TemporaryPasswordState(boolean hasPassword, int validFor) {

            this.hasPassword = hasPassword;
            this.validFor = validFor;

        }

        @Override
        public int getConstructor() { return 939837410; }

    }


    /**
     * Represents a local file
     *
     * @path - Local path to the locally available file part
     * @canBeDownloaded - True, if it is possible to try to download or generate the file
     * @canBeDeleted - True, if the file can be deleted
     * @isDownloadingActive - True, if the file is currently being downloaded (or a local copy is being generated by some other means)
     * @isDownloadingCompleted - True, if the local copy is fully available
     * @downloadOffset - Download will be started from this offset
     *                   Downloaded_prefix_size is calculated from this offset
     * @downloadedPrefixSize - If is_downloading_completed is false, then only some prefix of the file starting from download_offset is ready to be read
     *                         Downloaded_prefix_size is the size of that prefix
     * @downloadedSize - Total downloaded file bytes
     *                   Should be used only for calculating download progress
     *                   The actual file size may be bigger, and some parts of it may contain garbage
     */
    public static class LocalFile extends Object {

        @Nullable public String path;
        public boolean canBeDownloaded;
        public boolean canBeDeleted;
        public boolean isDownloadingActive;
        public boolean isDownloadingCompleted;
        public int downloadOffset;
        public int downloadedPrefixSize;
        public int downloadedSize;

        public LocalFile() {}

        public LocalFile(@Nullable String path, boolean canBeDownloaded, boolean canBeDeleted, boolean isDownloadingActive, boolean isDownloadingCompleted, int downloadOffset, int downloadedPrefixSize, int downloadedSize) {

            this.path = path;
            this.canBeDownloaded = canBeDownloaded;
            this.canBeDeleted = canBeDeleted;
            this.isDownloadingActive = isDownloadingActive;
            this.isDownloadingCompleted = isDownloadingCompleted;
            this.downloadOffset = downloadOffset;
            this.downloadedPrefixSize = downloadedPrefixSize;
            this.downloadedSize = downloadedSize;

        }

        @Override
        public int getConstructor() { return -1166400317; }

    }


    /**
     * Represents a remote file
     *
     * @id - Remote file identifier
     *       Can be used by the current user across application restarts or even from other devices
     *       Uniquely identifies a file, but a file can have a lot of different valid identifiers
     *       If the ID starts with "http://" or "https://", it represents the HTTP URL of the file
     *       TDLib is currently unable to download files if only their URL is known
     *       If downloadFile is called on such a file or if it is sent to a secret chat, TDLib starts a file generation process by sending updateFileGenerationStart to the application with the HTTP URL in the original_path and "#url#" as the conversion string
     *       Application should generate the file by downloading it to the specified location
     * @uniqueId - Unique file identifier
     *             May be empty if unknown
     *             The unique file identifier which is the same for the same file even for different users and is persistent over time
     * @isUploadingActive - True, if the file is currently being uploaded (or a remote copy is being generated by some other means)
     * @isUploadingCompleted - True, if a remote copy is fully available
     * @uploadedSize - Size of the remote available part of the file
     *                 0 if unknown
     */
    public static class RemoteFile extends Object {

        @Nullable public String id;
        public String uniqueId;
        public boolean isUploadingActive;
        public boolean isUploadingCompleted;
        public int uploadedSize;

        public RemoteFile() {}

        public RemoteFile(@Nullable String id, String uniqueId, boolean isUploadingActive, boolean isUploadingCompleted, int uploadedSize) {

            this.id = id;
            this.uniqueId = uniqueId;
            this.isUploadingActive = isUploadingActive;
            this.isUploadingCompleted = isUploadingCompleted;
            this.uploadedSize = uploadedSize;

        }

        @Override
        public int getConstructor() { return -1822143022; }

    }


    /**
     * Represents a file
     *
     * @id - Unique file identifier
     * @size - File size
     *         0 if unknown
     * @expectedSize - Expected file size in case the exact file size is unknown, but an approximate size is known
     *                 Can be used to show download/upload progress
     * @local - Information about the local copy of the file
     * @remote - Information about the remote copy of the file
     */
    public static class File extends Object {

        public int id;
        public int size;
        public int expectedSize;
        public LocalFile local;
        public RemoteFile remote;

        public File() {}

        public File(int id, int size, int expectedSize, LocalFile local, RemoteFile remote) {

            this.id = id;
            this.size = size;
            this.expectedSize = expectedSize;
            this.local = local;
            this.remote = remote;

        }

        @Override
        public int getConstructor() { return 766337656; }

    }


    /**
     * Points to a file
     */
    public static abstract class InputFile extends Object {}

    /**
     * A file defined by its unique ID
     *
     * @id - Unique file identifier
     */
    public static class InputFileId extends InputFile {

        public int id;

        public InputFileId() {}

        public InputFileId(int id) {

            this.id = id;

        }

        @Override
        public int getConstructor() { return 1788906253; }

    }


    /**
     * A file defined by its remote ID
     * The remote ID is guaranteed to be usable only if the corresponding file is still accessible to the user and known to TDLib
     * For example, if the file is from a message, then the message must be not deleted and accessible to the user
     * If the file database is disabled, then the corresponding object with the file must be preloaded by the application
     *
     * @id - Remote file identifier
     */
    public static class InputFileRemote extends InputFile {

        public String id;

        public InputFileRemote() {}

        public InputFileRemote(String id) {

            this.id = id;

        }

        @Override
        public int getConstructor() { return -107574466; }

    }


    /**
     * A file defined by a local path
     *
     * @path - Local path to the file
     */
    public static class InputFileLocal extends InputFile {

        public String path;

        public InputFileLocal() {}

        public InputFileLocal(String path) {

            this.path = path;

        }

        @Override
        public int getConstructor() { return 2056030919; }

    }


    /**
     * A file generated by the application
     *
     * @originalPath - Local path to a file from which the file is generated
     *                 May be empty if there is no such file
     * @conversion - String specifying the conversion applied to the original file
     *               Should be persistent across application restarts
     *               Conversions beginning with '#' are reserved for internal TDLib usage
     * @expectedSize - Expected size of the generated file
     *                 0 if unknown
     */
    public static class InputFileGenerated extends InputFile {

        public String originalPath;
        public String conversion;
        public int expectedSize;

        public InputFileGenerated() {}

        public InputFileGenerated(String originalPath, String conversion, int expectedSize) {

            this.originalPath = originalPath;
            this.conversion = conversion;
            this.expectedSize = expectedSize;

        }

        @Override
        public int getConstructor() { return -1781351885; }

    }


    /**
     * Describes an image in JPEG format
     *
     * @type - Image type (see https://core.telegram.org/constructor/photoSize)
     * @photo - Information about the image file
     * @width - Image width
     * @height - Image height
     * @progressiveSizes - Sizes of progressive JPEG file prefixes, which can be used to preliminarily show the image
     */
    public static class PhotoSize extends Object {

        public String type;
        public File photo;
        public int width;
        public int height;
        public int[] progressiveSizes;

        public PhotoSize() {}

        public PhotoSize(String type, File photo, int width, int height, int[] progressiveSizes) {

            this.type = type;
            this.photo = photo;
            this.width = width;
            this.height = height;
            this.progressiveSizes = progressiveSizes;

        }

        @Override
        public int getConstructor() { return 1609182352; }

    }


    /**
     * Thumbnail image of a very poor quality and low resolution
     *
     * @width - Thumbnail width, usually doesn't exceed 40
     * @height - Thumbnail height, usually doesn't exceed 40
     * @data - The thumbnail in JPEG format
     */
    public static class Minithumbnail extends Object {

        public int width;
        public int height;
        public byte[] data;

        public Minithumbnail() {}

        public Minithumbnail(int width, int height, byte[] data) {

            this.width = width;
            this.height = height;
            this.data = data;

        }

        @Override
        public int getConstructor() { return -328540758; }

    }


    /**
     * Describes format of the thumbnail
     */
    public static abstract class ThumbnailFormat extends Object {}

    /**
     * The thumbnail is in JPEG format
     */
    public static class ThumbnailFormatJpeg extends ThumbnailFormat {

        @Override
        public int getConstructor() { return -653503352; }

    }


    /**
     * The thumbnail is in PNG format
     * It will be used only for background patterns
     */
    public static class ThumbnailFormatPng extends ThumbnailFormat {

        @Override
        public int getConstructor() { return 1577490421; }

    }


    /**
     * The thumbnail is in WEBP format
     * It will be used only for some stickers
     */
    public static class ThumbnailFormatWebp extends ThumbnailFormat {

        @Override
        public int getConstructor() { return -53588974; }

    }


    /**
     * The thumbnail is in static GIF format
     * It will be used only for some bot inline results
     */
    public static class ThumbnailFormatGif extends ThumbnailFormat {

        @Override
        public int getConstructor() { return 1252205962; }

    }


    /**
     * The thumbnail is in TGS format
     * It will be used only for animated sticker sets
     */
    public static class ThumbnailFormatTgs extends ThumbnailFormat {

        @Override
        public int getConstructor() { return 1315522642; }

    }


    /**
     * The thumbnail is in MPEG4 format
     * It will be used only for some animations and videos
     */
    public static class ThumbnailFormatMpeg4 extends ThumbnailFormat {

        @Override
        public int getConstructor() { return 278616062; }

    }


    /**
     * Represents a thumbnail
     *
     * @format - Thumbnail format
     * @width - Thumbnail width
     * @height - Thumbnail height
     * @file - The thumbnail
     */
    public static class Thumbnail extends Object {

        public ThumbnailFormat format;
        public int width;
        public int height;
        public File file;

        public Thumbnail() {}

        public Thumbnail(ThumbnailFormat format, int width, int height, File file) {

            this.format = format;
            this.width = width;
            this.height = height;
            this.file = file;

        }

        @Override
        public int getConstructor() { return 1243275371; }

    }


    /**
     * Part of the face, relative to which a mask should be placed
     */
    public static abstract class MaskPoint extends Object {}

    /**
     * A mask should be placed relatively to the forehead
     */
    public static class MaskPointForehead extends MaskPoint {

        @Override
        public int getConstructor() { return 1027512005; }

    }


    /**
     * A mask should be placed relatively to the eyes
     */
    public static class MaskPointEyes extends MaskPoint {

        @Override
        public int getConstructor() { return 1748310861; }

    }


    /**
     * A mask should be placed relatively to the mouth
     */
    public static class MaskPointMouth extends MaskPoint {

        @Override
        public int getConstructor() { return 411773406; }

    }


    /**
     * A mask should be placed relatively to the chin
     */
    public static class MaskPointChin extends MaskPoint {

        @Override
        public int getConstructor() { return 534995335; }

    }


    /**
     * Position on a photo where a mask should be placed
     *
     * @point - Part of the face, relative to which the mask should be placed
     * @xShift - Shift by X-axis measured in widths of the mask scaled to the face size, from left to right
     *           (For example, -1.0 will place the mask just to the left of the default mask position)
     * @yShift - Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom
     *           (For example, 1.0 will place the mask just below the default mask position)
     * @scale - Mask scaling coefficient
     *          (For example, 2.0 means a doubled size)
     */
    public static class MaskPosition extends Object {

        public MaskPoint point;
        public double xShift;
        public double yShift;
        public double scale;

        public MaskPosition() {}

        public MaskPosition(MaskPoint point, double xShift, double yShift, double scale) {

            this.point = point;
            this.xShift = xShift;
            this.yShift = yShift;
            this.scale = scale;

        }

        @Override
        public int getConstructor() { return -2097433026; }

    }


    /**
     * Describes one answer option of a poll
     *
     * @text - Option text, 1-100 characters
     * @voterCount - Number of voters for this option, available only for closed or voted polls
     * @votePercentage - The percentage of votes for this option, 0-100
     * @isChosen - True, if the option was chosen by the user
     * @isBeingChosen - True, if the option is being chosen by a pending setPollAnswer request
     */
    public static class PollOption extends Object {

        public String text;
        public int voterCount;
        public int votePercentage;
        public boolean isChosen;
        public boolean isBeingChosen;

        public PollOption() {}

        public PollOption(String text, int voterCount, int votePercentage, boolean isChosen, boolean isBeingChosen) {

            this.text = text;
            this.voterCount = voterCount;
            this.votePercentage = votePercentage;
            this.isChosen = isChosen;
            this.isBeingChosen = isBeingChosen;

        }

        @Override
        public int getConstructor() { return 1473893797; }

    }


    /**
     * Describes the type of a poll
     */
    public static abstract class PollType extends Object {}

    /**
     * A regular poll
     *
     * @allowMultipleAnswers - True, if multiple answer options can be chosen simultaneously
     */
    public static class PollTypeRegular extends PollType {

        public boolean allowMultipleAnswers;

        public PollTypeRegular() {}

        public PollTypeRegular(boolean allowMultipleAnswers) {

            this.allowMultipleAnswers = allowMultipleAnswers;

        }

        @Override
        public int getConstructor() { return 641265698; }

    }


    /**
     * A poll in quiz mode, which has exactly one correct answer option and can be answered only once
     *
     * @correctOptionId - 0-based identifier of the correct answer option
     *                    -1 for a yet unanswered poll
     * @explanation - Text that is shown when the user chooses an incorrect answer or taps on the lamp icon, 0-200 characters with at most 2 line feeds
     *                Empty for a yet unanswered poll
     */
    public static class PollTypeQuiz extends PollType {

        public int correctOptionId;
        public FormattedText explanation;

        public PollTypeQuiz() {}

        public PollTypeQuiz(int correctOptionId, FormattedText explanation) {

            this.correctOptionId = correctOptionId;
            this.explanation = explanation;

        }

        @Override
        public int getConstructor() { return 657013913; }

    }


    /**
     * Describes an animation file
     * The animation must be encoded in GIF or MPEG4 format
     *
     * @duration - Duration of the animation, in seconds
     *             As defined by the sender
     * @width - Width of the animation
     * @height - Height of the animation
     * @fileName - Original name of the file
     *             As defined by the sender
     * @mimeType - MIME type of the file, usually "image/gif" or "video/mp4"
     * @hasStickers - True, if stickers were added to the animation
     *                The list of corresponding sticker set can be received using getAttachedStickerSets
     * @minithumbnail - Animation minithumbnail
     * @thumbnail - Animation thumbnail in JPEG or MPEG4 format
     * @animation - File containing the animation
     */
    public static class Animation extends Object {

        public int duration;
        public int width;
        public int height;
        public String fileName;
        public String mimeType;
        public boolean hasStickers;
        @Nullable public Minithumbnail minithumbnail;
        @Nullable public Thumbnail thumbnail;
        public File animation;

        public Animation() {}

        public Animation(int duration, int width, int height, String fileName, String mimeType, boolean hasStickers, @Nullable Minithumbnail minithumbnail, @Nullable Thumbnail thumbnail, File animation) {

            this.duration = duration;
            this.width = width;
            this.height = height;
            this.fileName = fileName;
            this.mimeType = mimeType;
            this.hasStickers = hasStickers;
            this.minithumbnail = minithumbnail;
            this.thumbnail = thumbnail;
            this.animation = animation;

        }

        @Override
        public int getConstructor() { return -872359106; }

    }


    /**
     * Describes an audio file
     * Audio is usually in MP3 or M4A format
     *
     * @duration - Duration of the audio, in seconds
     *             As defined by the sender
     * @title - Title of the audio
     *          As defined by the sender
     * @performer - Performer of the audio
     *              As defined by the sender
     * @fileName - Original name of the file
     *             As defined by the sender
     * @mimeType - The MIME type of the file
     *             As defined by the sender
     * @albumCoverMinithumbnail - The minithumbnail of the album cover
     * @albumCoverThumbnail - The thumbnail of the album cover in JPEG format
     *                        As defined by the sender
     *                        The full size thumbnail should be extracted from the downloaded file
     * @audio - File containing the audio
     */
    public static class Audio extends Object {

        public int duration;
        public String title;
        public String performer;
        public String fileName;
        public String mimeType;
        @Nullable public Minithumbnail albumCoverMinithumbnail;
        @Nullable public Thumbnail albumCoverThumbnail;
        public File audio;

        public Audio() {}

        public Audio(int duration, String title, String performer, String fileName, String mimeType, @Nullable Minithumbnail albumCoverMinithumbnail, @Nullable Thumbnail albumCoverThumbnail, File audio) {

            this.duration = duration;
            this.title = title;
            this.performer = performer;
            this.fileName = fileName;
            this.mimeType = mimeType;
            this.albumCoverMinithumbnail = albumCoverMinithumbnail;
            this.albumCoverThumbnail = albumCoverThumbnail;
            this.audio = audio;

        }

        @Override
        public int getConstructor() { return -1179334690; }

    }


    /**
     * Describes a document of any type
     *
     * @fileName - Original name of the file
     *             As defined by the sender
     * @mimeType - MIME type of the file
     *             As defined by the sender
     * @minithumbnail - Document minithumbnail
     * @thumbnail - Document thumbnail in JPEG or PNG format (PNG will be used only for background patterns)
     *              As defined by the sender
     * @document - File containing the document
     */
    public static class Document extends Object {

        public String fileName;
        public String mimeType;
        @Nullable public Minithumbnail minithumbnail;
        @Nullable public Thumbnail thumbnail;
        public File document;

        public Document() {}

        public Document(String fileName, String mimeType, @Nullable Minithumbnail minithumbnail, @Nullable Thumbnail thumbnail, File document) {

            this.fileName = fileName;
            this.mimeType = mimeType;
            this.minithumbnail = minithumbnail;
            this.thumbnail = thumbnail;
            this.document = document;

        }

        @Override
        public int getConstructor() { return -1357271080; }

    }


    /**
     * Describes a photo
     *
     * @hasStickers - True, if stickers were added to the photo
     *                The list of corresponding sticker sets can be received using getAttachedStickerSets
     * @minithumbnail - Photo minithumbnail
     * @sizes - Available variants of the photo, in different sizes
     */
    public static class Photo extends Object {

        public boolean hasStickers;
        @Nullable public Minithumbnail minithumbnail;
        public PhotoSize[] sizes;

        public Photo() {}

        public Photo(boolean hasStickers, @Nullable Minithumbnail minithumbnail, PhotoSize[] sizes) {

            this.hasStickers = hasStickers;
            this.minithumbnail = minithumbnail;
            this.sizes = sizes;

        }

        @Override
        public int getConstructor() { return -2022871583; }

    }


    /**
     * Describes a sticker
     *
     * @setId - The identifier of the sticker set to which the sticker belongs
     *          0 if none
     * @width - Sticker width
     *          As defined by the sender
     * @height - Sticker height
     *           As defined by the sender
     * @emoji - Emoji corresponding to the sticker
     * @isAnimated - True, if the sticker is an animated sticker in TGS format
     * @isMask - True, if the sticker is a mask
     * @maskPosition - Position where the mask should be placed
     * @thumbnail - Sticker thumbnail in WEBP or JPEG format
     * @sticker - File containing the sticker
     */
    public static class Sticker extends Object {

        public long setId;
        public int width;
        public int height;
        public String emoji;
        public boolean isAnimated;
        public boolean isMask;
        @Nullable public MaskPosition maskPosition;
        @Nullable public Thumbnail thumbnail;
        public File sticker;

        public Sticker() {}

        public Sticker(long setId, int width, int height, String emoji, boolean isAnimated, boolean isMask, @Nullable MaskPosition maskPosition, @Nullable Thumbnail thumbnail, File sticker) {

            this.setId = setId;
            this.width = width;
            this.height = height;
            this.emoji = emoji;
            this.isAnimated = isAnimated;
            this.isMask = isMask;
            this.maskPosition = maskPosition;
            this.thumbnail = thumbnail;
            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return -692141937; }

    }


    /**
     * Describes a video file
     *
     * @duration - Duration of the video, in seconds
     *             As defined by the sender
     * @width - Video width
     *          As defined by the sender
     * @height - Video height
     *           As defined by the sender
     * @fileName - Original name of the file
     *             As defined by the sender
     * @mimeType - MIME type of the file
     *             As defined by the sender
     * @hasStickers - True, if stickers were added to the video
     *                The list of corresponding sticker sets can be received using getAttachedStickerSets
     * @supportsStreaming - True, if the video should be tried to be streamed
     * @minithumbnail - Video minithumbnail
     * @thumbnail - Video thumbnail in JPEG or MPEG4 format
     *              As defined by the sender
     * @video - File containing the video
     */
    public static class Video extends Object {

        public int duration;
        public int width;
        public int height;
        public String fileName;
        public String mimeType;
        public boolean hasStickers;
        public boolean supportsStreaming;
        @Nullable public Minithumbnail minithumbnail;
        @Nullable public Thumbnail thumbnail;
        public File video;

        public Video() {}

        public Video(int duration, int width, int height, String fileName, String mimeType, boolean hasStickers, boolean supportsStreaming, @Nullable Minithumbnail minithumbnail, @Nullable Thumbnail thumbnail, File video) {

            this.duration = duration;
            this.width = width;
            this.height = height;
            this.fileName = fileName;
            this.mimeType = mimeType;
            this.hasStickers = hasStickers;
            this.supportsStreaming = supportsStreaming;
            this.minithumbnail = minithumbnail;
            this.thumbnail = thumbnail;
            this.video = video;

        }

        @Override
        public int getConstructor() { return 832856268; }

    }


    /**
     * Describes a video note
     * The video must be equal in width and height, cropped to a circle, and stored in MPEG4 format
     *
     * @duration - Duration of the video, in seconds
     *             As defined by the sender
     * @length - Video width and height
     *           As defined by the sender
     * @minithumbnail - Video minithumbnail
     * @thumbnail - Video thumbnail in JPEG format
     *              As defined by the sender
     * @video - File containing the video
     */
    public static class VideoNote extends Object {

        public int duration;
        public int length;
        @Nullable public Minithumbnail minithumbnail;
        @Nullable public Thumbnail thumbnail;
        public File video;

        public VideoNote() {}

        public VideoNote(int duration, int length, @Nullable Minithumbnail minithumbnail, @Nullable Thumbnail thumbnail, File video) {

            this.duration = duration;
            this.length = length;
            this.minithumbnail = minithumbnail;
            this.thumbnail = thumbnail;
            this.video = video;

        }

        @Override
        public int getConstructor() { return -71734726; }

    }


    /**
     * Describes a voice note
     * The voice note must be encoded with the Opus codec, and stored inside an OGG container
     * Voice notes can have only a single audio channel
     *
     * @duration - Duration of the voice note, in seconds
     *             As defined by the sender
     * @waveform - A waveform representation of the voice note in 5-bit format
     * @mimeType - MIME type of the file
     *             As defined by the sender
     * @voice - File containing the voice note
     */
    public static class VoiceNote extends Object {

        public int duration;
        public byte[] waveform;
        public String mimeType;
        public File voice;

        public VoiceNote() {}

        public VoiceNote(int duration, byte[] waveform, String mimeType, File voice) {

            this.duration = duration;
            this.waveform = waveform;
            this.mimeType = mimeType;
            this.voice = voice;

        }

        @Override
        public int getConstructor() { return -2066012058; }

    }


    /**
     * Describes a user contact
     *
     * @phoneNumber - Phone number of the user
     * @firstName - First name of the user
     * @lastName - Last name of the user
     * @vcard - Additional data about the user in a form of vCard
     * @userId - Identifier of the user, if known
     *           Otherwise 0
     */
    public static class Contact extends Object {

        public String phoneNumber;
        public String firstName;
        public String lastName;
        public String vcard;
        public int userId;

        public Contact() {}

        public Contact(String phoneNumber, String firstName, String lastName, String vcard, int userId) {

            this.phoneNumber = phoneNumber;
            this.firstName = firstName;
            this.lastName = lastName;
            this.vcard = vcard;
            this.userId = userId;

        }

        @Override
        public int getConstructor() { return -1483002540; }

    }


    /**
     * Describes a location on planet Earth
     *
     * @latitude - Latitude of the location in degrees
     *             As defined by the sender
     * @longitude - Longitude of the location, in degrees
     *              As defined by the sender
     */
    public static class Location extends Object {

        public double latitude;
        public double longitude;

        public Location() {}

        public Location(double latitude, double longitude) {

            this.latitude = latitude;
            this.longitude = longitude;

        }

        @Override
        public int getConstructor() { return 749028016; }

    }


    /**
     * Describes a venue
     *
     * @location - Venue location
     *             As defined by the sender
     * @title - Venue name
     *          As defined by the sender
     * @address - Venue address
     *            As defined by the sender
     * @provider - Provider of the venue database
     *             As defined by the sender
     *             Currently only "foursquare" and "gplaces" (Google Places) need to be supported
     * @id - Identifier of the venue in the provider database
     *       As defined by the sender
     * @type - Type of the venue in the provider database
     *         As defined by the sender
     */
    public static class Venue extends Object {

        public Location location;
        public String title;
        public String address;
        public String provider;
        public String id;
        public String type;

        public Venue() {}

        public Venue(Location location, String title, String address, String provider, String id, String type) {

            this.location = location;
            this.title = title;
            this.address = address;
            this.provider = provider;
            this.id = id;
            this.type = type;

        }

        @Override
        public int getConstructor() { return 1070406393; }

    }


    /**
     * Describes a game
     *
     * @id - Game ID
     * @shortName - Game short name
     *              To share a game use the URL https://t.me/{bot_username}?game={game_short_name}
     * @title - Game title
     * @text - Game text, usually containing scoreboards for a game
     * @description - Game description
     * @photo - Game photo
     * @animation - Game animation
     */
    public static class Game extends Object {

        public long id;
        public String shortName;
        public String title;
        public FormattedText text;
        public String description;
        public Photo photo;
        @Nullable public Animation animation;

        public Game() {}

        public Game(long id, String shortName, String title, FormattedText text, String description, Photo photo, @Nullable Animation animation) {

            this.id = id;
            this.shortName = shortName;
            this.title = title;
            this.text = text;
            this.description = description;
            this.photo = photo;
            this.animation = animation;

        }

        @Override
        public int getConstructor() { return -1565597752; }

    }


    /**
     * Describes a poll
     *
     * @id - Unique poll identifier
     * @question - Poll question, 1-300 characters
     * @options - List of poll answer options
     * @totalVoterCount - Total number of voters, participating in the poll
     * @recentVoterUserIds - User identifiers of recent voters, if the poll is non-anonymous
     * @isAnonymous - True, if the poll is anonymous
     * @type - Type of the poll
     * @openPeriod - Amount of time the poll will be active after creation, in seconds
     * @closeDate - Point in time (Unix timestamp) when the poll will be automatically closed
     * @isClosed - True, if the poll is closed
     */
    public static class Poll extends Object {

        public long id;
        public String question;
        public PollOption[] options;
        public int totalVoterCount;
        public int[] recentVoterUserIds;
        public boolean isAnonymous;
        public PollType type;
        public int openPeriod;
        public int closeDate;
        public boolean isClosed;

        public Poll() {}

        public Poll(long id, String question, PollOption[] options, int totalVoterCount, int[] recentVoterUserIds, boolean isAnonymous, PollType type, int openPeriod, int closeDate, boolean isClosed) {

            this.id = id;
            this.question = question;
            this.options = options;
            this.totalVoterCount = totalVoterCount;
            this.recentVoterUserIds = recentVoterUserIds;
            this.isAnonymous = isAnonymous;
            this.type = type;
            this.openPeriod = openPeriod;
            this.closeDate = closeDate;
            this.isClosed = isClosed;

        }

        @Override
        public int getConstructor() { return 163256789; }

    }


    /**
     * Describes a user profile photo
     *
     * @id - Photo identifier
     *       0 for an empty photo
     *       Can be used to find a photo in a list of user profile photos
     * @small - A small (160x160) user profile photo
     *          The file can be downloaded only before the photo is changed
     * @big - A big (640x640) user profile photo
     *        The file can be downloaded only before the photo is changed
     * @hasAnimation - True, if the photo has animated variant
     */
    public static class ProfilePhoto extends Object {

        public long id;
        public File small;
        public File big;
        public boolean hasAnimation;

        public ProfilePhoto() {}

        public ProfilePhoto(long id, File small, File big, boolean hasAnimation) {

            this.id = id;
            this.small = small;
            this.big = big;
            this.hasAnimation = hasAnimation;

        }

        @Override
        public int getConstructor() { return 1270562457; }

    }


    /**
     * Contains basic information about the photo of a chat
     *
     * @small - A small (160x160) chat photo variant in JPEG format
     *          The file can be downloaded only before the photo is changed
     * @big - A big (640x640) chat photo variant in JPEG format
     *        The file can be downloaded only before the photo is changed
     * @hasAnimation - True, if the photo has animated variant
     */
    public static class ChatPhotoInfo extends Object {

        public File small;
        public File big;
        public boolean hasAnimation;

        public ChatPhotoInfo() {}

        public ChatPhotoInfo(File small, File big, boolean hasAnimation) {

            this.small = small;
            this.big = big;
            this.hasAnimation = hasAnimation;

        }

        @Override
        public int getConstructor() { return 404510091; }

    }


    /**
     * Represents the type of a user
     * The following types are possible: regular users, deleted users and bots
     */
    public static abstract class UserType extends Object {}

    /**
     * A regular user
     */
    public static class UserTypeRegular extends UserType {

        @Override
        public int getConstructor() { return -598644325; }

    }


    /**
     * A deleted user or deleted bot
     * No information on the user besides the user identifier is available
     * It is not possible to perform any active actions on this type of user
     */
    public static class UserTypeDeleted extends UserType {

        @Override
        public int getConstructor() { return -1807729372; }

    }


    /**
     * A bot (see https://core.telegram.org/bots)
     *
     * @canJoinGroups - True, if the bot can be invited to basic group and supergroup chats
     * @canReadAllGroupMessages - True, if the bot can read all messages in basic group or supergroup chats and not just those addressed to the bot
     *                            In private and channel chats a bot can always read all messages
     * @isInline - True, if the bot supports inline queries
     * @inlineQueryPlaceholder - Placeholder for inline queries (displayed on the application input field)
     * @needLocation - True, if the location of the user should be sent with every inline query to this bot
     */
    public static class UserTypeBot extends UserType {

        public boolean canJoinGroups;
        public boolean canReadAllGroupMessages;
        public boolean isInline;
        public String inlineQueryPlaceholder;
        public boolean needLocation;

        public UserTypeBot() {}

        public UserTypeBot(boolean canJoinGroups, boolean canReadAllGroupMessages, boolean isInline, String inlineQueryPlaceholder, boolean needLocation) {

            this.canJoinGroups = canJoinGroups;
            this.canReadAllGroupMessages = canReadAllGroupMessages;
            this.isInline = isInline;
            this.inlineQueryPlaceholder = inlineQueryPlaceholder;
            this.needLocation = needLocation;

        }

        @Override
        public int getConstructor() { return 1262387765; }

    }


    /**
     * No information on the user besides the user identifier is available, yet this user has not been deleted
     * This object is extremely rare and must be handled like a deleted user
     * It is not possible to perform any actions on users of this type
     */
    public static class UserTypeUnknown extends UserType {

        @Override
        public int getConstructor() { return -724541123; }

    }


    /**
     * Represents a command supported by a bot
     *
     * @command - Text of the bot command
     * @description - Description of the bot command
     */
    public static class BotCommand extends Object {

        public String command;
        public String description;

        public BotCommand() {}

        public BotCommand(String command, String description) {

            this.command = command;
            this.description = description;

        }

        @Override
        public int getConstructor() { return -1032140601; }

    }


    /**
     * Provides information about a bot and its supported commands
     *
     * @description - Long description shown on the user info page
     * @commands - A list of commands supported by the bot
     */
    public static class BotInfo extends Object {

        public String description;
        public BotCommand[] commands;

        public BotInfo() {}

        public BotInfo(String description, BotCommand[] commands) {

            this.description = description;
            this.commands = commands;

        }

        @Override
        public int getConstructor() { return 1296528907; }

    }


    /**
     * Represents a location to which a chat is connected
     *
     * @location - The location
     * @address - Location address
     */
    public static class ChatLocation extends Object {

        public Location location;
        public String address;

        public ChatLocation() {}

        public ChatLocation(Location location, String address) {

            this.location = location;
            this.address = address;

        }

        @Override
        public int getConstructor() { return -1566863583; }

    }


    /**
     * Animated variant of a chat photo in MPEG4 format
     *
     * @length - Animation width and height
     * @file - Information about the animation file
     * @mainFrameTimestamp - Timestamp of the frame, used as a static chat photo
     */
    public static class AnimatedChatPhoto extends Object {

        public int length;
        public File file;
        public double mainFrameTimestamp;

        public AnimatedChatPhoto() {}

        public AnimatedChatPhoto(int length, File file, double mainFrameTimestamp) {

            this.length = length;
            this.file = file;
            this.mainFrameTimestamp = mainFrameTimestamp;

        }

        @Override
        public int getConstructor() { return 191994926; }

    }


    /**
     * Describes a chat or user profile photo
     *
     * @id - Unique photo identifier
     * @addedDate - Point in time (Unix timestamp) when the photo has been added
     * @minithumbnail - Photo minithumbnail
     * @sizes - Available variants of the photo in JPEG format, in different size
     * @animation - Animated variant of the photo in MPEG4 format
     */
    public static class ChatPhoto extends Object {

        public long id;
        public int addedDate;
        @Nullable public Minithumbnail minithumbnail;
        public PhotoSize[] sizes;
        @Nullable public AnimatedChatPhoto animation;

        public ChatPhoto() {}

        public ChatPhoto(long id, int addedDate, @Nullable Minithumbnail minithumbnail, PhotoSize[] sizes, @Nullable AnimatedChatPhoto animation) {

            this.id = id;
            this.addedDate = addedDate;
            this.minithumbnail = minithumbnail;
            this.sizes = sizes;
            this.animation = animation;

        }

        @Override
        public int getConstructor() { return -113003577; }

    }


    /**
     * Contains a list of chat or user profile photos
     *
     * @totalCount - Total number of photos
     * @photos - List of photos
     */
    public static class ChatPhotos extends Object {

        public int totalCount;
        public ChatPhoto[] photos;

        public ChatPhotos() {}

        public ChatPhotos(int totalCount, ChatPhoto[] photos) {

            this.totalCount = totalCount;
            this.photos = photos;

        }

        @Override
        public int getConstructor() { return -1510699180; }

    }


    /**
     * Describes a photo to be set as a user profile or chat photo
     */
    public static abstract class InputChatPhoto extends Object {}

    /**
     * A previously used profile photo of the current user
     *
     * @chatPhotoId - Identifier of the profile photo to reuse
     */
    public static class InputChatPhotoPrevious extends InputChatPhoto {

        public long chatPhotoId;

        public InputChatPhotoPrevious() {}

        public InputChatPhotoPrevious(long chatPhotoId) {

            this.chatPhotoId = chatPhotoId;

        }

        @Override
        public int getConstructor() { return 23128529; }

    }


    /**
     * A static photo in JPEG format
     *
     * @photo - Photo to be set as profile photo
     *          Only inputFileLocal and inputFileGenerated are allowed
     */
    public static class InputChatPhotoStatic extends InputChatPhoto {

        public InputFile photo;

        public InputChatPhotoStatic() {}

        public InputChatPhotoStatic(InputFile photo) {

            this.photo = photo;

        }

        @Override
        public int getConstructor() { return 1979179699; }

    }


    /**
     * An animation in MPEG4 format
     * Must be square, at most 10 seconds long, have width between 160 and 800 and be at most 2MB in size
     *
     * @animation - Animation to be set as profile photo
     *              Only inputFileLocal and inputFileGenerated are allowed
     * @mainFrameTimestamp - Timestamp of the frame, which will be used as static chat photo
     */
    public static class InputChatPhotoAnimation extends InputChatPhoto {

        public InputFile animation;
        public double mainFrameTimestamp;

        public InputChatPhotoAnimation() {}

        public InputChatPhotoAnimation(InputFile animation, double mainFrameTimestamp) {

            this.animation = animation;
            this.mainFrameTimestamp = mainFrameTimestamp;

        }

        @Override
        public int getConstructor() { return 90846242; }

    }


    /**
     * Represents a user
     *
     * @id - User identifier
     * @firstName - First name of the user
     * @lastName - Last name of the user
     * @username - Username of the user
     * @phoneNumber - Phone number of the user
     * @status - Current online status of the user
     * @profilePhoto - Profile photo of the user
     * @isContact - The user is a contact of the current user
     * @isMutualContact - The user is a contact of the current user and the current user is a contact of the user
     * @isVerified - True, if the user is verified
     * @isSupport - True, if the user is Telegram support account
     * @restrictionReason - If non-empty, it contains a human-readable description of the reason why access to this user must be restricted
     * @isScam - True, if many users reported this user as a scam
     * @haveAccess - If false, the user is inaccessible, and the only information known about the user is inside this class
     *               It can't be passed to any method except GetUser
     * @type - Type of the user
     * @languageCode - IETF language tag of the user's language
     *                 Only available to bots
     */
    public static class User extends Object {

        public int id;
        public String firstName;
        public String lastName;
        public String username;
        public String phoneNumber;
        public UserStatus status;
        @Nullable public ProfilePhoto profilePhoto;
        public boolean isContact;
        public boolean isMutualContact;
        public boolean isVerified;
        public boolean isSupport;
        public String restrictionReason;
        public boolean isScam;
        public boolean haveAccess;
        public UserType type;
        public String languageCode;

        public User() {}

        public User(int id, String firstName, String lastName, String username, String phoneNumber, UserStatus status, @Nullable ProfilePhoto profilePhoto, boolean isContact, boolean isMutualContact, boolean isVerified, boolean isSupport, String restrictionReason, boolean isScam, boolean haveAccess, UserType type, String languageCode) {

            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.phoneNumber = phoneNumber;
            this.status = status;
            this.profilePhoto = profilePhoto;
            this.isContact = isContact;
            this.isMutualContact = isMutualContact;
            this.isVerified = isVerified;
            this.isSupport = isSupport;
            this.restrictionReason = restrictionReason;
            this.isScam = isScam;
            this.haveAccess = haveAccess;
            this.type = type;
            this.languageCode = languageCode;

        }

        @Override
        public int getConstructor() { return -824771497; }

    }


    /**
     * Contains full information about a user
     *
     * @photo - User profile photo
     * @canBeCalled - True, if the user can be called
     * @supportsVideoCalls - True, if a video call can be created with the user
     * @hasPrivateCalls - True, if the user can't be called due to their privacy settings
     * @needPhoneNumberPrivacyException - True, if the current user needs to explicitly allow to share their phone number with the user when the method addContact is used
     * @bio - A short user bio
     * @shareText - For bots, the text that is included with the link when users share the bot
     * @groupInCommonCount - Number of group chats where both the other user and the current user are a member
     *                       0 for the current user
     * @botInfo - If the user is a bot, information about the bot
     */
    public static class UserFullInfo extends Object {

        @Nullable public ChatPhoto photo;
        public boolean canBeCalled;
        public boolean supportsVideoCalls;
        public boolean hasPrivateCalls;
        public boolean needPhoneNumberPrivacyException;
        public String bio;
        public String shareText;
        public int groupInCommonCount;
        @Nullable public BotInfo botInfo;

        public UserFullInfo() {}

        public UserFullInfo(@Nullable ChatPhoto photo, boolean canBeCalled, boolean supportsVideoCalls, boolean hasPrivateCalls, boolean needPhoneNumberPrivacyException, String bio, String shareText, int groupInCommonCount, @Nullable BotInfo botInfo) {

            this.photo = photo;
            this.canBeCalled = canBeCalled;
            this.supportsVideoCalls = supportsVideoCalls;
            this.hasPrivateCalls = hasPrivateCalls;
            this.needPhoneNumberPrivacyException = needPhoneNumberPrivacyException;
            this.bio = bio;
            this.shareText = shareText;
            this.groupInCommonCount = groupInCommonCount;
            this.botInfo = botInfo;

        }

        @Override
        public int getConstructor() { return -1067749716; }

    }


    /**
     * Represents a list of users
     *
     * @totalCount - Approximate total count of users found
     * @userIds - A list of user identifiers
     */
    public static class Users extends Object {

        public int totalCount;
        public int[] userIds;

        public Users() {}

        public Users(int totalCount, int[] userIds) {

            this.totalCount = totalCount;
            this.userIds = userIds;

        }

        @Override
        public int getConstructor() { return 273760088; }

    }


    /**
     * Contains information about a chat administrator
     *
     * @userId - User identifier of the administrator
     * @customTitle - Custom title of the administrator
     * @isOwner - True, if the user is the owner of the chat
     */
    public static class ChatAdministrator extends Object {

        public int userId;
        public String customTitle;
        public boolean isOwner;

        public ChatAdministrator() {}

        public ChatAdministrator(int userId, String customTitle, boolean isOwner) {

            this.userId = userId;
            this.customTitle = customTitle;
            this.isOwner = isOwner;

        }

        @Override
        public int getConstructor() { return 487220942; }

    }


    /**
     * Represents a list of chat administrators
     *
     * @administrators - A list of chat administrators
     */
    public static class ChatAdministrators extends Object {

        public ChatAdministrator[] administrators;

        public ChatAdministrators() {}

        public ChatAdministrators(ChatAdministrator[] administrators) {

            this.administrators = administrators;

        }

        @Override
        public int getConstructor() { return -2126186435; }

    }


    /**
     * Describes actions that a user is allowed to take in a chat
     *
     * @canSendMessages - True, if the user can send text messages, contacts, locations, and venues
     * @canSendMediaMessages - True, if the user can send audio files, documents, photos, videos, video notes, and voice notes
     *                         Implies can_send_messages permissions
     * @canSendPolls - True, if the user can send polls
     *                 Implies can_send_messages permissions
     * @canSendOtherMessages - True, if the user can send animations, games, stickers, and dice and use inline bots
     *                         Implies can_send_messages permissions
     * @canAddWebPagePreviews - True, if the user may add a web page preview to their messages
     *                          Implies can_send_messages permissions
     * @canChangeInfo - True, if the user can change the chat title, photo, and other settings
     * @canInviteUsers - True, if the user can invite new users to the chat
     * @canPinMessages - True, if the user can pin messages
     */
    public static class ChatPermissions extends Object {

        public boolean canSendMessages;
        public boolean canSendMediaMessages;
        public boolean canSendPolls;
        public boolean canSendOtherMessages;
        public boolean canAddWebPagePreviews;
        public boolean canChangeInfo;
        public boolean canInviteUsers;
        public boolean canPinMessages;

        public ChatPermissions() {}

        public ChatPermissions(boolean canSendMessages, boolean canSendMediaMessages, boolean canSendPolls, boolean canSendOtherMessages, boolean canAddWebPagePreviews, boolean canChangeInfo, boolean canInviteUsers, boolean canPinMessages) {

            this.canSendMessages = canSendMessages;
            this.canSendMediaMessages = canSendMediaMessages;
            this.canSendPolls = canSendPolls;
            this.canSendOtherMessages = canSendOtherMessages;
            this.canAddWebPagePreviews = canAddWebPagePreviews;
            this.canChangeInfo = canChangeInfo;
            this.canInviteUsers = canInviteUsers;
            this.canPinMessages = canPinMessages;

        }

        @Override
        public int getConstructor() { return 1584650463; }

    }


    /**
     * Provides information about the status of a member in a chat
     */
    public static abstract class ChatMemberStatus extends Object {}

    /**
     * The user is the owner of a chat and has all the administrator privileges
     *
     * @customTitle - A custom title of the owner
     *                Applicable to supergroups only
     * @isAnonymous - True, if the creator isn't shown in the chat member list and sends messages anonymously
     * @isMember - True, if the user is a member of the chat
     */
    public static class ChatMemberStatusCreator extends ChatMemberStatus {

        public String customTitle;
        public boolean isAnonymous;
        public boolean isMember;

        public ChatMemberStatusCreator() {}

        public ChatMemberStatusCreator(String customTitle, boolean isAnonymous, boolean isMember) {

            this.customTitle = customTitle;
            this.isAnonymous = isAnonymous;
            this.isMember = isMember;

        }

        @Override
        public int getConstructor() { return -160019714; }

    }


    /**
     * The user is a member of a chat and has some additional privileges
     * In basic groups, administrators can edit and delete messages sent by others, add new members, and ban unprivileged members
     * In supergroups and channels, there are more detailed options for administrator privileges
     *
     * @customTitle - A custom title of the administrator
     *                Applicable to supergroups only
     * @canBeEdited - True, if the current user can edit the administrator privileges for the called user
     * @canChangeInfo - True, if the administrator can change the chat title, photo, and other settings
     * @canPostMessages - True, if the administrator can create channel posts
     *                    Applicable to channels only
     * @canEditMessages - True, if the administrator can edit messages of other users and pin messages
     *                    Applicable to channels only
     * @canDeleteMessages - True, if the administrator can delete messages of other users
     * @canInviteUsers - True, if the administrator can invite new users to the chat
     * @canRestrictMembers - True, if the administrator can restrict, ban, or unban chat members
     * @canPinMessages - True, if the administrator can pin messages
     *                   Applicable to groups only
     * @canPromoteMembers - True, if the administrator can add new administrators with a subset of their own privileges or demote administrators that were directly or indirectly promoted by them
     * @isAnonymous - True, if the administrator isn't shown in the chat member list and sends messages anonymously
     */
    public static class ChatMemberStatusAdministrator extends ChatMemberStatus {

        public String customTitle;
        public boolean canBeEdited;
        public boolean canChangeInfo;
        public boolean canPostMessages;
        public boolean canEditMessages;
        public boolean canDeleteMessages;
        public boolean canInviteUsers;
        public boolean canRestrictMembers;
        public boolean canPinMessages;
        public boolean canPromoteMembers;
        public boolean isAnonymous;

        public ChatMemberStatusAdministrator() {}

        public ChatMemberStatusAdministrator(String customTitle, boolean canBeEdited, boolean canChangeInfo, boolean canPostMessages, boolean canEditMessages, boolean canDeleteMessages, boolean canInviteUsers, boolean canRestrictMembers, boolean canPinMessages, boolean canPromoteMembers, boolean isAnonymous) {

            this.customTitle = customTitle;
            this.canBeEdited = canBeEdited;
            this.canChangeInfo = canChangeInfo;
            this.canPostMessages = canPostMessages;
            this.canEditMessages = canEditMessages;
            this.canDeleteMessages = canDeleteMessages;
            this.canInviteUsers = canInviteUsers;
            this.canRestrictMembers = canRestrictMembers;
            this.canPinMessages = canPinMessages;
            this.canPromoteMembers = canPromoteMembers;
            this.isAnonymous = isAnonymous;

        }

        @Override
        public int getConstructor() { return 222495835; }

    }


    /**
     * The user is a member of a chat, without any additional privileges or restrictions
     */
    public static class ChatMemberStatusMember extends ChatMemberStatus {

        @Override
        public int getConstructor() { return 844723285; }

    }


    /**
     * The user is under certain restrictions in the chat
     * Not supported in basic groups and channels
     *
     * @isMember - True, if the user is a member of the chat
     * @restrictedUntilDate - Point in time (Unix timestamp) when restrictions will be lifted from the user
     *                        0 if never
     *                        If the user is restricted for more than 366 days or for less than 30 seconds from the current time, the user is considered to be restricted forever
     * @permissions - User permissions in the chat
     */
    public static class ChatMemberStatusRestricted extends ChatMemberStatus {

        public boolean isMember;
        public int restrictedUntilDate;
        public ChatPermissions permissions;

        public ChatMemberStatusRestricted() {}

        public ChatMemberStatusRestricted(boolean isMember, int restrictedUntilDate, ChatPermissions permissions) {

            this.isMember = isMember;
            this.restrictedUntilDate = restrictedUntilDate;
            this.permissions = permissions;

        }

        @Override
        public int getConstructor() { return 1661432998; }

    }


    /**
     * The user is not a chat member
     */
    public static class ChatMemberStatusLeft extends ChatMemberStatus {

        @Override
        public int getConstructor() { return -5815259; }

    }


    /**
     * The user was banned (and hence is not a member of the chat)
     * Implies the user can't return to the chat or view messages
     *
     * @bannedUntilDate - Point in time (Unix timestamp) when the user will be unbanned
     *                    0 if never
     *                    If the user is banned for more than 366 days or for less than 30 seconds from the current time, the user is considered to be banned forever
     */
    public static class ChatMemberStatusBanned extends ChatMemberStatus {

        public int bannedUntilDate;

        public ChatMemberStatusBanned() {}

        public ChatMemberStatusBanned(int bannedUntilDate) {

            this.bannedUntilDate = bannedUntilDate;

        }

        @Override
        public int getConstructor() { return -1653518666; }

    }


    /**
     * A user with information about joining/leaving a chat
     *
     * @userId - User identifier of the chat member
     * @inviterUserId - Identifier of a user that invited/promoted/banned this member in the chat
     *                  0 if unknown
     * @joinedChatDate - Point in time (Unix timestamp) when the user joined the chat
     * @status - Status of the member in the chat
     * @botInfo - If the user is a bot, information about the bot
     *            Can be null even for a bot if the bot is not the chat member
     */
    public static class ChatMember extends Object {

        public int userId;
        public int inviterUserId;
        public int joinedChatDate;
        public ChatMemberStatus status;
        @Nullable public BotInfo botInfo;

        public ChatMember() {}

        public ChatMember(int userId, int inviterUserId, int joinedChatDate, ChatMemberStatus status, @Nullable BotInfo botInfo) {

            this.userId = userId;
            this.inviterUserId = inviterUserId;
            this.joinedChatDate = joinedChatDate;
            this.status = status;
            this.botInfo = botInfo;

        }

        @Override
        public int getConstructor() { return -806137076; }

    }


    /**
     * Contains a list of chat members
     *
     * @totalCount - Approximate total count of chat members found
     * @members - A list of chat members
     */
    public static class ChatMembers extends Object {

        public int totalCount;
        public ChatMember[] members;

        public ChatMembers() {}

        public ChatMembers(int totalCount, ChatMember[] members) {

            this.totalCount = totalCount;
            this.members = members;

        }

        @Override
        public int getConstructor() { return -497558622; }

    }


    /**
     * Specifies the kind of chat members to return in searchChatMembers
     */
    public static abstract class ChatMembersFilter extends Object {}

    /**
     * Returns contacts of the user
     */
    public static class ChatMembersFilterContacts extends ChatMembersFilter {

        @Override
        public int getConstructor() { return 1774485671; }

    }


    /**
     * Returns the owner and administrators
     */
    public static class ChatMembersFilterAdministrators extends ChatMembersFilter {

        @Override
        public int getConstructor() { return -1266893796; }

    }


    /**
     * Returns all chat members, including restricted chat members
     */
    public static class ChatMembersFilterMembers extends ChatMembersFilter {

        @Override
        public int getConstructor() { return 670504342; }

    }


    /**
     * Returns users under certain restrictions in the chat
     * Can be used only by administrators in a supergroup
     */
    public static class ChatMembersFilterRestricted extends ChatMembersFilter {

        @Override
        public int getConstructor() { return 1256282813; }

    }


    /**
     * Returns users banned from the chat
     * Can be used only by administrators in a supergroup or in a channel
     */
    public static class ChatMembersFilterBanned extends ChatMembersFilter {

        @Override
        public int getConstructor() { return -1863102648; }

    }


    /**
     * Returns bot members of the chat
     */
    public static class ChatMembersFilterBots extends ChatMembersFilter {

        @Override
        public int getConstructor() { return -1422567288; }

    }


    /**
     * Specifies the kind of chat members to return in getSupergroupMembers
     */
    public static abstract class SupergroupMembersFilter extends Object {}

    /**
     * Returns recently active users in reverse chronological order
     */
    public static class SupergroupMembersFilterRecent extends SupergroupMembersFilter {

        @Override
        public int getConstructor() { return 1178199509; }

    }


    /**
     * Returns contacts of the user, which are members of the supergroup or channel
     *
     * @query - Query to search for
     */
    public static class SupergroupMembersFilterContacts extends SupergroupMembersFilter {

        public String query;

        public SupergroupMembersFilterContacts() {}

        public SupergroupMembersFilterContacts(String query) {

            this.query = query;

        }

        @Override
        public int getConstructor() { return -1282910856; }

    }


    /**
     * Returns the owner and administrators
     */
    public static class SupergroupMembersFilterAdministrators extends SupergroupMembersFilter {

        @Override
        public int getConstructor() { return -2097380265; }

    }


    /**
     * Used to search for supergroup or channel members via a (string) query
     *
     * @query - Query to search for
     */
    public static class SupergroupMembersFilterSearch extends SupergroupMembersFilter {

        public String query;

        public SupergroupMembersFilterSearch() {}

        public SupergroupMembersFilterSearch(String query) {

            this.query = query;

        }

        @Override
        public int getConstructor() { return -1696358469; }

    }


    /**
     * Returns restricted supergroup members
     * Can be used only by administrators
     *
     * @query - Query to search for
     */
    public static class SupergroupMembersFilterRestricted extends SupergroupMembersFilter {

        public String query;

        public SupergroupMembersFilterRestricted() {}

        public SupergroupMembersFilterRestricted(String query) {

            this.query = query;

        }

        @Override
        public int getConstructor() { return -1107800034; }

    }


    /**
     * Returns users banned from the supergroup or channel
     * Can be used only by administrators
     *
     * @query - Query to search for
     */
    public static class SupergroupMembersFilterBanned extends SupergroupMembersFilter {

        public String query;

        public SupergroupMembersFilterBanned() {}

        public SupergroupMembersFilterBanned(String query) {

            this.query = query;

        }

        @Override
        public int getConstructor() { return -1210621683; }

    }


    /**
     * Returns bot members of the supergroup or channel
     */
    public static class SupergroupMembersFilterBots extends SupergroupMembersFilter {

        @Override
        public int getConstructor() { return 492138918; }

    }


    /**
     * Represents a basic group of 0-200 users (must be upgraded to a supergroup to accommodate more than 200 users)
     *
     * @id - Group identifier
     * @memberCount - Number of members in the group
     * @status - Status of the current user in the group
     * @isActive - True, if the group is active
     * @upgradedToSupergroupId - Identifier of the supergroup to which this group was upgraded
     *                           0 if none
     */
    public static class BasicGroup extends Object {

        public int id;
        public int memberCount;
        public ChatMemberStatus status;
        public boolean isActive;
        public int upgradedToSupergroupId;

        public BasicGroup() {}

        public BasicGroup(int id, int memberCount, ChatMemberStatus status, boolean isActive, int upgradedToSupergroupId) {

            this.id = id;
            this.memberCount = memberCount;
            this.status = status;
            this.isActive = isActive;
            this.upgradedToSupergroupId = upgradedToSupergroupId;

        }

        @Override
        public int getConstructor() { return -317839045; }

    }


    /**
     * Contains full information about a basic group
     *
     * @photo - Chat photo
     * @description - Group description
     * @creatorUserId - User identifier of the creator of the group
     *                  0 if unknown
     * @members - Group members
     * @inviteLink - Invite link for this group
     *               Available only after it has been generated at least once and only for the group creator
     */
    public static class BasicGroupFullInfo extends Object {

        @Nullable public ChatPhoto photo;
        public String description;
        public int creatorUserId;
        public ChatMember[] members;
        public String inviteLink;

        public BasicGroupFullInfo() {}

        public BasicGroupFullInfo(@Nullable ChatPhoto photo, String description, int creatorUserId, ChatMember[] members, String inviteLink) {

            this.photo = photo;
            this.description = description;
            this.creatorUserId = creatorUserId;
            this.members = members;
            this.inviteLink = inviteLink;

        }

        @Override
        public int getConstructor() { return -127204719; }

    }


    /**
     * Represents a supergroup or channel with zero or more members (subscribers in the case of channels)
     * From the point of view of the system, a channel is a special kind of a supergroup: only administrators can post and see the list of members, and posts from all administrators use the name and photo of the channel instead of individual names and profile photos
     * Unlike supergroups, channels can have an unlimited number of subscribers
     *
     * @id - Supergroup or channel identifier
     * @username - Username of the supergroup or channel
     *             Empty for private supergroups or channels
     * @date - Point in time (Unix timestamp) when the current user joined, or the point in time when the supergroup or channel was created, in case the user is not a member
     * @status - Status of the current user in the supergroup or channel
     *           Custom title will be always empty
     * @memberCount - Number of members in the supergroup or channel
     *                0 if unknown
     *                Currently it is guaranteed to be known only if the supergroup or channel was received through searchPublicChats, searchChatsNearby, getInactiveSupergroupChats, getSuitableDiscussionChats, getGroupsInCommon, or getUserPrivacySettingRules
     * @hasLinkedChat - True, if the channel has a discussion group, or the supergroup is the designated discussion group for a channel
     * @hasLocation - True, if the supergroup is connected to a location, i.e
     *                The supergroup is a location-based supergroup
     * @signMessages - True, if messages sent to the channel should contain information about the sender
     *                 This field is only applicable to channels
     * @isSlowModeEnabled - True, if the slow mode is enabled in the supergroup
     * @isChannel - True, if the supergroup is a channel
     * @isVerified - True, if the supergroup or channel is verified
     * @restrictionReason - If non-empty, contains a human-readable description of the reason why access to this supergroup or channel must be restricted
     * @isScam - True, if many users reported this supergroup as a scam
     */
    public static class Supergroup extends Object {

        public int id;
        public String username;
        public int date;
        public ChatMemberStatus status;
        public int memberCount;
        public boolean hasLinkedChat;
        public boolean hasLocation;
        public boolean signMessages;
        public boolean isSlowModeEnabled;
        public boolean isChannel;
        public boolean isVerified;
        public String restrictionReason;
        public boolean isScam;

        public Supergroup() {}

        public Supergroup(int id, String username, int date, ChatMemberStatus status, int memberCount, boolean hasLinkedChat, boolean hasLocation, boolean signMessages, boolean isSlowModeEnabled, boolean isChannel, boolean isVerified, String restrictionReason, boolean isScam) {

            this.id = id;
            this.username = username;
            this.date = date;
            this.status = status;
            this.memberCount = memberCount;
            this.hasLinkedChat = hasLinkedChat;
            this.hasLocation = hasLocation;
            this.signMessages = signMessages;
            this.isSlowModeEnabled = isSlowModeEnabled;
            this.isChannel = isChannel;
            this.isVerified = isVerified;
            this.restrictionReason = restrictionReason;
            this.isScam = isScam;

        }

        @Override
        public int getConstructor() { return -103091; }

    }


    /**
     * Contains full information about a supergroup or channel
     *
     * @photo - Chat photo
     * @description - Supergroup or channel description
     * @memberCount - Number of members in the supergroup or channel
     *                0 if unknown
     * @administratorCount - Number of privileged users in the supergroup or channel
     *                       0 if unknown
     * @restrictedCount - Number of restricted users in the supergroup
     *                    0 if unknown
     * @bannedCount - Number of users banned from chat
     *                0 if unknown
     * @linkedChatId - Chat identifier of a discussion group for the channel, or a channel, for which the supergroup is the designated discussion group
     *                 0 if none or unknown
     * @slowModeDelay - Delay between consecutive sent messages for non-administrator supergroup members, in seconds
     * @slowModeDelayExpiresIn - Time left before next message can be sent in the supergroup, in seconds
     *                           An updateSupergroupFullInfo update is not triggered when value of this field changes, but both new and old values are non-zero
     * @canGetMembers - True, if members of the chat can be retrieved
     * @canSetUsername - True, if the chat username can be changed
     * @canSetStickerSet - True, if the supergroup sticker set can be changed
     * @canSetLocation - True, if the supergroup location can be changed
     * @canGetStatistics - True, if the supergroup or channel statistics are available
     * @isAllHistoryAvailable - True, if new chat members will have access to old messages
     *                          In public or discussion groups and both public and private channels, old messages are always available, so this option affects only private supergroups without a linked chat
     *                          The value of this field is only available for chat administrators
     * @stickerSetId - Identifier of the supergroup sticker set
     *                 0 if none
     * @location - Location to which the supergroup is connected
     * @inviteLink - Invite link for this chat
     * @upgradedFromBasicGroupId - Identifier of the basic group from which supergroup was upgraded
     *                             0 if none
     * @upgradedFromMaxMessageId - Identifier of the last message in the basic group from which supergroup was upgraded
     *                             0 if none
     */
    public static class SupergroupFullInfo extends Object {

        @Nullable public ChatPhoto photo;
        public String description;
        public int memberCount;
        public int administratorCount;
        public int restrictedCount;
        public int bannedCount;
        public long linkedChatId;
        public int slowModeDelay;
        public double slowModeDelayExpiresIn;
        public boolean canGetMembers;
        public boolean canSetUsername;
        public boolean canSetStickerSet;
        public boolean canSetLocation;
        public boolean canGetStatistics;
        public boolean isAllHistoryAvailable;
        public long stickerSetId;
        @Nullable public ChatLocation location;
        public String inviteLink;
        public int upgradedFromBasicGroupId;
        public long upgradedFromMaxMessageId;

        public SupergroupFullInfo() {}

        public SupergroupFullInfo(@Nullable ChatPhoto photo, String description, int memberCount, int administratorCount, int restrictedCount, int bannedCount, long linkedChatId, int slowModeDelay, double slowModeDelayExpiresIn, boolean canGetMembers, boolean canSetUsername, boolean canSetStickerSet, boolean canSetLocation, boolean canGetStatistics, boolean isAllHistoryAvailable, long stickerSetId, @Nullable ChatLocation location, String inviteLink, int upgradedFromBasicGroupId, long upgradedFromMaxMessageId) {

            this.photo = photo;
            this.description = description;
            this.memberCount = memberCount;
            this.administratorCount = administratorCount;
            this.restrictedCount = restrictedCount;
            this.bannedCount = bannedCount;
            this.linkedChatId = linkedChatId;
            this.slowModeDelay = slowModeDelay;
            this.slowModeDelayExpiresIn = slowModeDelayExpiresIn;
            this.canGetMembers = canGetMembers;
            this.canSetUsername = canSetUsername;
            this.canSetStickerSet = canSetStickerSet;
            this.canSetLocation = canSetLocation;
            this.canGetStatistics = canGetStatistics;
            this.isAllHistoryAvailable = isAllHistoryAvailable;
            this.stickerSetId = stickerSetId;
            this.location = location;
            this.inviteLink = inviteLink;
            this.upgradedFromBasicGroupId = upgradedFromBasicGroupId;
            this.upgradedFromMaxMessageId = upgradedFromMaxMessageId;

        }

        @Override
        public int getConstructor() { return -1112328416; }

    }


    /**
     * Describes the current secret chat state
     */
    public static abstract class SecretChatState extends Object {}

    /**
     * The secret chat is not yet created
     * Waiting for the other user to get online
     */
    public static class SecretChatStatePending extends SecretChatState {

        @Override
        public int getConstructor() { return -1637050756; }

    }


    /**
     * The secret chat is ready to use
     */
    public static class SecretChatStateReady extends SecretChatState {

        @Override
        public int getConstructor() { return -1611352087; }

    }


    /**
     * The secret chat is closed
     */
    public static class SecretChatStateClosed extends SecretChatState {

        @Override
        public int getConstructor() { return -1945106707; }

    }


    /**
     * Represents a secret chat
     *
     * @id - Secret chat identifier
     * @userId - Identifier of the chat partner
     * @state - State of the secret chat
     * @isOutbound - True, if the chat was created by the current user
     *               Otherwise false
     * @ttl - Current message Time To Live setting (self-destruct timer) for the chat, in seconds
     * @keyHash - Hash of the currently used key for comparison with the hash of the chat partner's key
     *            This is a string of 36 little-endian bytes, which must be split into groups of 2 bits, each denoting a pixel of one of 4 colors FFFFFF, D5E6F3, 2D5775, and 2F99C9
     *            The pixels must be used to make a 12x12 square image filled from left to right, top to bottom
     *            Alternatively, the first 32 bytes of the hash can be converted to the hexadecimal format and printed as 32 2-digit hex numbers
     * @layer - Secret chat layer
     *          Determines features supported by the chat partner's application
     *          Video notes are supported if the layer >= 66
     *          Nested text entities and underline and strikethrough entities are supported if the layer >= 101
     */
    public static class SecretChat extends Object {

        public int id;
        public int userId;
        public SecretChatState state;
        public boolean isOutbound;
        public int ttl;
        public byte[] keyHash;
        public int layer;

        public SecretChat() {}

        public SecretChat(int id, int userId, SecretChatState state, boolean isOutbound, int ttl, byte[] keyHash, int layer) {

            this.id = id;
            this.userId = userId;
            this.state = state;
            this.isOutbound = isOutbound;
            this.ttl = ttl;
            this.keyHash = keyHash;
            this.layer = layer;

        }

        @Override
        public int getConstructor() { return 1279231629; }

    }


    /**
     * Contains information about the origin of a forwarded message
     */
    public static abstract class MessageForwardOrigin extends Object {}

    /**
     * The message was originally written by a known user
     *
     * @senderUserId - Identifier of the user that originally sent the message
     */
    public static class MessageForwardOriginUser extends MessageForwardOrigin {

        public int senderUserId;

        public MessageForwardOriginUser() {}

        public MessageForwardOriginUser(int senderUserId) {

            this.senderUserId = senderUserId;

        }

        @Override
        public int getConstructor() { return 2781520; }

    }


    /**
     * The message was originally written by an anonymous chat administrator on behalf of the chat
     *
     * @senderChatId - Identifier of the chat that originally sent the message
     */
    public static class MessageForwardOriginChat extends MessageForwardOrigin {

        public long senderChatId;

        public MessageForwardOriginChat() {}

        public MessageForwardOriginChat(long senderChatId) {

            this.senderChatId = senderChatId;

        }

        @Override
        public int getConstructor() { return 872598889; }

    }


    /**
     * The message was originally written by a user, which is hidden by their privacy settings
     *
     * @senderName - Name of the sender
     */
    public static class MessageForwardOriginHiddenUser extends MessageForwardOrigin {

        public String senderName;

        public MessageForwardOriginHiddenUser() {}

        public MessageForwardOriginHiddenUser(String senderName) {

            this.senderName = senderName;

        }

        @Override
        public int getConstructor() { return -271257885; }

    }


    /**
     * The message was originally a post in a channel
     *
     * @chatId - Identifier of the chat from which the message was originally forwarded
     * @messageId - Message identifier of the original message
     *              0 if unknown
     * @authorSignature - Original post author signature
     */
    public static class MessageForwardOriginChannel extends MessageForwardOrigin {

        public long chatId;
        public long messageId;
        public String authorSignature;

        public MessageForwardOriginChannel() {}

        public MessageForwardOriginChannel(long chatId, long messageId, String authorSignature) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.authorSignature = authorSignature;

        }

        @Override
        public int getConstructor() { return 1490730723; }

    }


    /**
     * Contains information about a forwarded message
     *
     * @origin - Origin of a forwarded message
     * @date - Point in time (Unix timestamp) when the message was originally sent
     * @publicServiceAnnouncementType - The type of a public service announcement for the forwarded message
     * @fromChatId - For messages forwarded to the chat with the current user (Saved Messages), to the Replies bot chat, or to the channel's discussion group, the identifier of the chat from which the message was forwarded last time
     *               0 if unknown
     * @fromMessageId - For messages forwarded to the chat with the current user (Saved Messages), to the Replies bot chat, or to the channel's discussion group, the identifier of the original message from which the new message was forwarded last time
     *                  0 if unknown
     */
    public static class MessageForwardInfo extends Object {

        public MessageForwardOrigin origin;
        public int date;
        public String publicServiceAnnouncementType;
        public long fromChatId;
        public long fromMessageId;

        public MessageForwardInfo() {}

        public MessageForwardInfo(MessageForwardOrigin origin, int date, String publicServiceAnnouncementType, long fromChatId, long fromMessageId) {

            this.origin = origin;
            this.date = date;
            this.publicServiceAnnouncementType = publicServiceAnnouncementType;
            this.fromChatId = fromChatId;
            this.fromMessageId = fromMessageId;

        }

        @Override
        public int getConstructor() { return -327300408; }

    }


    /**
     * Contains information about message replies
     *
     * @replyCount - Number of times the message was directly or indirectly replied
     * @recentReplierUserIds - User identifiers of the recent repliers to the message
     *                         Available in channels with a discussion supergroup
     * @lastReadInboxMessageId - Identifier of the last read incoming reply to the message
     * @lastReadOutboxMessageId - Identifier of the last read outgoing reply to the message
     * @lastMessageId - Identifier of the last reply to the message
     */
    public static class MessageReplyInfo extends Object {

        public int replyCount;
        public int[] recentReplierUserIds;
        public long lastReadInboxMessageId;
        public long lastReadOutboxMessageId;
        public long lastMessageId;

        public MessageReplyInfo() {}

        public MessageReplyInfo(int replyCount, int[] recentReplierUserIds, long lastReadInboxMessageId, long lastReadOutboxMessageId, long lastMessageId) {

            this.replyCount = replyCount;
            this.recentReplierUserIds = recentReplierUserIds;
            this.lastReadInboxMessageId = lastReadInboxMessageId;
            this.lastReadOutboxMessageId = lastReadOutboxMessageId;
            this.lastMessageId = lastMessageId;

        }

        @Override
        public int getConstructor() { return -67189409; }

    }


    /**
     * Contains information about interactions with a message
     *
     * @viewCount - Number of times the message was viewed
     * @forwardCount - Number of times the message was forwarded
     * @replyInfo - Contains information about direct or indirect replies to the message
     *              Currently, available only in channels with a discussion supergroup and discussion supergroups for messages, which are not replies itself
     */
    public static class MessageInteractionInfo extends Object {

        public int viewCount;
        public int forwardCount;
        @Nullable public MessageReplyInfo replyInfo;

        public MessageInteractionInfo() {}

        public MessageInteractionInfo(int viewCount, int forwardCount, @Nullable MessageReplyInfo replyInfo) {

            this.viewCount = viewCount;
            this.forwardCount = forwardCount;
            this.replyInfo = replyInfo;

        }

        @Override
        public int getConstructor() { return -620714966; }

    }


    /**
     * Contains information about the sending state of the message
     */
    public static abstract class MessageSendingState extends Object {}

    /**
     * The message is being sent now, but has not yet been delivered to the server
     */
    public static class MessageSendingStatePending extends MessageSendingState {

        @Override
        public int getConstructor() { return -1381803582; }

    }


    /**
     * The message failed to be sent
     *
     * @errorCode - An error code
     *              0 if unknown
     * @errorMessage - Error message
     * @canRetry - True, if the message can be re-sent
     * @retryAfter - Time left before the message can be re-sent, in seconds
     *               No update is sent when this field changes
     */
    public static class MessageSendingStateFailed extends MessageSendingState {

        public int errorCode;
        public String errorMessage;
        public boolean canRetry;
        public double retryAfter;

        public MessageSendingStateFailed() {}

        public MessageSendingStateFailed(int errorCode, String errorMessage, boolean canRetry, double retryAfter) {

            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
            this.canRetry = canRetry;
            this.retryAfter = retryAfter;

        }

        @Override
        public int getConstructor() { return 2054476087; }

    }


    /**
     * Describes a message
     *
     * @id - Message identifier
     *       Unique for the chat to which the message belongs
     * @senderUserId - Identifier of the user who sent the message
     *                 0 if unknown
     *                 Currently, it is unknown for channel posts, for channel posts automatically forwarded to discussion group and for anonymously sent supergroup messages
     * @senderChatId - Identifier of the chat on behalf of which the message was sent
     *                 0 if none
     * @chatId - Chat identifier
     * @sendingState - Information about the sending state of the message
     * @schedulingState - Information about the scheduling state of the message
     * @isOutgoing - True, if the message is outgoing
     * @canBeEdited - True, if the message can be edited
     *                For live location and poll messages this fields shows whether editMessageLiveLocation or stopPoll can be used with this message by the application
     * @canBeForwarded - True, if the message can be forwarded
     * @canBeDeletedOnlyForSelf - True, if the message can be deleted only for the current user while other users will continue to see it
     * @canBeDeletedForAllUsers - True, if the message can be deleted for all users
     * @canGetStatistics - True, if the message statistics are available
     * @canGetMessageThread - True, if the message thread info is available
     * @isChannelPost - True, if the message is a channel post
     *                  All messages to channels are channel posts, all other messages are not channel posts
     * @containsUnreadMention - True, if the message contains an unread mention for the current user
     * @date - Point in time (Unix timestamp) when the message was sent
     * @editDate - Point in time (Unix timestamp) when the message was last edited
     * @forwardInfo - Information about the initial message sender
     * @interactionInfo - Information about interactions with the message
     * @replyInChatId - If non-zero, the identifier of the chat to which the replied message belongs
     *                  Currently, only messages in the Replies chat can have different reply_in_chat_id and chat_id
     * @replyToMessageId - If non-zero, the identifier of the message this message is replying to
     *                     Can be the identifier of a deleted message
     * @messageThreadId - If non-zero, the identifier of the message thread the message belongs to
     *                    Unique within the chat to which the message belongs
     * @ttl - For self-destructing messages, the message's TTL (Time To Live), in seconds
     *        0 if none
     *        TDLib will send updateDeleteMessages or updateMessageContent once the TTL expires
     * @ttlExpiresIn - Time left before the message expires, in seconds
     * @viaBotUserId - If non-zero, the user identifier of the bot through which this message was sent
     * @authorSignature - For channel posts, optional author signature
     * @mediaAlbumId - Unique identifier of an album this message belongs to
     *                 Only photos and videos can be grouped together in albums
     * @restrictionReason - If non-empty, contains a human-readable description of the reason why access to this message must be restricted
     * @content - Content of the message
     * @replyMarkup - Reply markup for the message
     */
    public static class Message extends Object {

        public long id;
        public int senderUserId;
        public long senderChatId;
        public long chatId;
        @Nullable public MessageSendingState sendingState;
        @Nullable public MessageSchedulingState schedulingState;
        public boolean isOutgoing;
        public boolean canBeEdited;
        public boolean canBeForwarded;
        public boolean canBeDeletedOnlyForSelf;
        public boolean canBeDeletedForAllUsers;
        public boolean canGetStatistics;
        public boolean canGetMessageThread;
        public boolean isChannelPost;
        public boolean containsUnreadMention;
        public int date;
        public int editDate;
        @Nullable public MessageForwardInfo forwardInfo;
        @Nullable public MessageInteractionInfo interactionInfo;
        public long replyInChatId;
        public long replyToMessageId;
        public long messageThreadId;
        public int ttl;
        public double ttlExpiresIn;
        public int viaBotUserId;
        public String authorSignature;
        public long mediaAlbumId;
        public String restrictionReason;
        public MessageContent content;
        @Nullable public ReplyMarkup replyMarkup;

        public Message() {}

        public Message(long id, int senderUserId, long senderChatId, long chatId, @Nullable MessageSendingState sendingState, @Nullable MessageSchedulingState schedulingState, boolean isOutgoing, boolean canBeEdited, boolean canBeForwarded, boolean canBeDeletedOnlyForSelf, boolean canBeDeletedForAllUsers, boolean canGetStatistics, boolean canGetMessageThread, boolean isChannelPost, boolean containsUnreadMention, int date, int editDate, @Nullable MessageForwardInfo forwardInfo, @Nullable MessageInteractionInfo interactionInfo, long replyInChatId, long replyToMessageId, long messageThreadId, int ttl, double ttlExpiresIn, int viaBotUserId, String authorSignature, long mediaAlbumId, String restrictionReason, MessageContent content, @Nullable ReplyMarkup replyMarkup) {

            this.id = id;
            this.senderUserId = senderUserId;
            this.senderChatId = senderChatId;
            this.chatId = chatId;
            this.sendingState = sendingState;
            this.schedulingState = schedulingState;
            this.isOutgoing = isOutgoing;
            this.canBeEdited = canBeEdited;
            this.canBeForwarded = canBeForwarded;
            this.canBeDeletedOnlyForSelf = canBeDeletedOnlyForSelf;
            this.canBeDeletedForAllUsers = canBeDeletedForAllUsers;
            this.canGetStatistics = canGetStatistics;
            this.canGetMessageThread = canGetMessageThread;
            this.isChannelPost = isChannelPost;
            this.containsUnreadMention = containsUnreadMention;
            this.date = date;
            this.editDate = editDate;
            this.forwardInfo = forwardInfo;
            this.interactionInfo = interactionInfo;
            this.replyInChatId = replyInChatId;
            this.replyToMessageId = replyToMessageId;
            this.messageThreadId = messageThreadId;
            this.ttl = ttl;
            this.ttlExpiresIn = ttlExpiresIn;
            this.viaBotUserId = viaBotUserId;
            this.authorSignature = authorSignature;
            this.mediaAlbumId = mediaAlbumId;
            this.restrictionReason = restrictionReason;
            this.content = content;
            this.replyMarkup = replyMarkup;

        }

        @Override
        public int getConstructor() { return 81067037; }

    }


    /**
     * Contains a list of messages
     *
     * @totalCount - Approximate total count of messages found
     * @messages - List of messages
     *             Messages may be null
     */
    public static class Messages extends Object {

        public int totalCount;
        public Message[] messages;

        public Messages() {}

        public Messages(int totalCount, Message[] messages) {

            this.totalCount = totalCount;
            this.messages = messages;

        }

        @Override
        public int getConstructor() { return -16498159; }

    }


    /**
     * Contains a list of messages found by a search
     *
     * @totalCount - Approximate total count of messages found
     *               -1 if unknown
     * @messages - List of messages
     * @nextOffset - The offset for the next request
     *               If empty, there are no more results
     */
    public static class FoundMessages extends Object {

        public int totalCount;
        public Message[] messages;
        public String nextOffset;

        public FoundMessages() {}

        public FoundMessages(int totalCount, Message[] messages, String nextOffset) {

            this.totalCount = totalCount;
            this.messages = messages;
            this.nextOffset = nextOffset;

        }

        @Override
        public int getConstructor() { return -529809608; }

    }


    /**
     * Describes the types of chats to which notification settings are applied
     */
    public static abstract class NotificationSettingsScope extends Object {}

    /**
     * Notification settings applied to all private and secret chats when the corresponding chat setting has a default value
     */
    public static class NotificationSettingsScopePrivateChats extends NotificationSettingsScope {

        @Override
        public int getConstructor() { return 937446759; }

    }


    /**
     * Notification settings applied to all basic groups and supergroups when the corresponding chat setting has a default value
     */
    public static class NotificationSettingsScopeGroupChats extends NotificationSettingsScope {

        @Override
        public int getConstructor() { return 1212142067; }

    }


    /**
     * Notification settings applied to all channels when the corresponding chat setting has a default value
     */
    public static class NotificationSettingsScopeChannelChats extends NotificationSettingsScope {

        @Override
        public int getConstructor() { return 548013448; }

    }


    /**
     * Contains information about notification settings for a chat
     *
     * @useDefaultMuteFor - If true, mute_for is ignored and the value for the relevant type of chat is used instead
     * @muteFor - Time left before notifications will be unmuted, in seconds
     * @useDefaultSound - If true, sound is ignored and the value for the relevant type of chat is used instead
     * @sound - The name of an audio file to be used for notification sounds
     *          Only applies to iOS applications
     * @useDefaultShowPreview - If true, show_preview is ignored and the value for the relevant type of chat is used instead
     * @showPreview - True, if message content should be displayed in notifications
     * @useDefaultDisablePinnedMessageNotifications - If true, disable_pinned_message_notifications is ignored and the value for the relevant type of chat is used instead
     * @disablePinnedMessageNotifications - If true, notifications for incoming pinned messages will be created as for an ordinary unread message
     * @useDefaultDisableMentionNotifications - If true, disable_mention_notifications is ignored and the value for the relevant type of chat is used instead
     * @disableMentionNotifications - If true, notifications for messages with mentions will be created as for an ordinary unread message
     */
    public static class ChatNotificationSettings extends Object {

        public boolean useDefaultMuteFor;
        public int muteFor;
        public boolean useDefaultSound;
        public String sound;
        public boolean useDefaultShowPreview;
        public boolean showPreview;
        public boolean useDefaultDisablePinnedMessageNotifications;
        public boolean disablePinnedMessageNotifications;
        public boolean useDefaultDisableMentionNotifications;
        public boolean disableMentionNotifications;

        public ChatNotificationSettings() {}

        public ChatNotificationSettings(boolean useDefaultMuteFor, int muteFor, boolean useDefaultSound, String sound, boolean useDefaultShowPreview, boolean showPreview, boolean useDefaultDisablePinnedMessageNotifications, boolean disablePinnedMessageNotifications, boolean useDefaultDisableMentionNotifications, boolean disableMentionNotifications) {

            this.useDefaultMuteFor = useDefaultMuteFor;
            this.muteFor = muteFor;
            this.useDefaultSound = useDefaultSound;
            this.sound = sound;
            this.useDefaultShowPreview = useDefaultShowPreview;
            this.showPreview = showPreview;
            this.useDefaultDisablePinnedMessageNotifications = useDefaultDisablePinnedMessageNotifications;
            this.disablePinnedMessageNotifications = disablePinnedMessageNotifications;
            this.useDefaultDisableMentionNotifications = useDefaultDisableMentionNotifications;
            this.disableMentionNotifications = disableMentionNotifications;

        }

        @Override
        public int getConstructor() { return 1503183218; }

    }


    /**
     * Contains information about notification settings for several chats
     *
     * @muteFor - Time left before notifications will be unmuted, in seconds
     * @sound - The name of an audio file to be used for notification sounds
     *          Only applies to iOS applications
     * @showPreview - True, if message content should be displayed in notifications
     * @disablePinnedMessageNotifications - True, if notifications for incoming pinned messages will be created as for an ordinary unread message
     * @disableMentionNotifications - True, if notifications for messages with mentions will be created as for an ordinary unread message
     */
    public static class ScopeNotificationSettings extends Object {

        public int muteFor;
        public String sound;
        public boolean showPreview;
        public boolean disablePinnedMessageNotifications;
        public boolean disableMentionNotifications;

        public ScopeNotificationSettings() {}

        public ScopeNotificationSettings(int muteFor, String sound, boolean showPreview, boolean disablePinnedMessageNotifications, boolean disableMentionNotifications) {

            this.muteFor = muteFor;
            this.sound = sound;
            this.showPreview = showPreview;
            this.disablePinnedMessageNotifications = disablePinnedMessageNotifications;
            this.disableMentionNotifications = disableMentionNotifications;

        }

        @Override
        public int getConstructor() { return -426103745; }

    }


    /**
     * Contains information about a message draft
     *
     * @replyToMessageId - Identifier of the message to reply to
     *                     0 if none
     * @date - Point in time (Unix timestamp) when the draft was created
     * @inputMessageText - Content of the message draft
     *                     This should always be of type inputMessageText
     */
    public static class DraftMessage extends Object {

        public long replyToMessageId;
        public int date;
        public InputMessageContent inputMessageText;

        public DraftMessage() {}

        public DraftMessage(long replyToMessageId, int date, InputMessageContent inputMessageText) {

            this.replyToMessageId = replyToMessageId;
            this.date = date;
            this.inputMessageText = inputMessageText;

        }

        @Override
        public int getConstructor() { return 1373050112; }

    }


    /**
     * Describes the type of a chat
     */
    public static abstract class ChatType extends Object {}

    /**
     * An ordinary chat with a user
     *
     * @userId - User identifier
     */
    public static class ChatTypePrivate extends ChatType {

        public int userId;

        public ChatTypePrivate() {}

        public ChatTypePrivate(int userId) {

            this.userId = userId;

        }

        @Override
        public int getConstructor() { return 1700720838; }

    }


    /**
     * A basic group (i.e., a chat with 0-200 other users)
     *
     * @basicGroupId - Basic group identifier
     */
    public static class ChatTypeBasicGroup extends ChatType {

        public int basicGroupId;

        public ChatTypeBasicGroup() {}

        public ChatTypeBasicGroup(int basicGroupId) {

            this.basicGroupId = basicGroupId;

        }

        @Override
        public int getConstructor() { return 21815278; }

    }


    /**
     * A supergroup (i.e
     * A chat with up to GetOption("supergroup_max_size") other users), or channel (with unlimited members)
     *
     * @supergroupId - Supergroup or channel identifier
     * @isChannel - True, if the supergroup is a channel
     */
    public static class ChatTypeSupergroup extends ChatType {

        public int supergroupId;
        public boolean isChannel;

        public ChatTypeSupergroup() {}

        public ChatTypeSupergroup(int supergroupId, boolean isChannel) {

            this.supergroupId = supergroupId;
            this.isChannel = isChannel;

        }

        @Override
        public int getConstructor() { return 955152366; }

    }


    /**
     * A secret chat with a user
     *
     * @secretChatId - Secret chat identifier
     * @userId - User identifier of the secret chat peer
     */
    public static class ChatTypeSecret extends ChatType {

        public int secretChatId;
        public int userId;

        public ChatTypeSecret() {}

        public ChatTypeSecret(int secretChatId, int userId) {

            this.secretChatId = secretChatId;
            this.userId = userId;

        }

        @Override
        public int getConstructor() { return 136722563; }

    }


    /**
     * Represents a filter of user chats
     *
     * @title - The title of the filter
     * @iconName - The icon name for short filter representation
     *             If non-empty, must be one of "All", "Unread", "Unmuted", "Bots", "Channels", "Groups", "Private", "Custom", "Setup", "Cat", "Crown", "Favorite", "Flower", "Game", "Home", "Love", "Mask", "Party", "Sport", "Study", "Trade", "Travel", "Work"
     *             If empty, use getChatFilterDefaultIconName to get default icon name for the filter
     * @pinnedChatIds - The chat identifiers of pinned chats in the filtered chat list
     * @includedChatIds - The chat identifiers of always included chats in the filtered chat list
     * @excludedChatIds - The chat identifiers of always excluded chats in the filtered chat list
     * @excludeMuted - True, if the muted chats need to be excluded
     * @excludeRead - True, if read chats need to be excluded
     * @excludeArchived - True, if archived chats need to be excluded
     * @includeContacts - True, if contacts need to be included
     * @includeNonContacts - True, if non-contact users need to be included
     * @includeBots - True, if bots need to be included
     * @includeGroups - True, if basic groups and supergroups need to be included
     * @includeChannels - True, if channels need to be included
     */
    public static class ChatFilter extends Object {

        public String title;
        public String iconName;
        public long[] pinnedChatIds;
        public long[] includedChatIds;
        public long[] excludedChatIds;
        public boolean excludeMuted;
        public boolean excludeRead;
        public boolean excludeArchived;
        public boolean includeContacts;
        public boolean includeNonContacts;
        public boolean includeBots;
        public boolean includeGroups;
        public boolean includeChannels;

        public ChatFilter() {}

        public ChatFilter(String title, String iconName, long[] pinnedChatIds, long[] includedChatIds, long[] excludedChatIds, boolean excludeMuted, boolean excludeRead, boolean excludeArchived, boolean includeContacts, boolean includeNonContacts, boolean includeBots, boolean includeGroups, boolean includeChannels) {

            this.title = title;
            this.iconName = iconName;
            this.pinnedChatIds = pinnedChatIds;
            this.includedChatIds = includedChatIds;
            this.excludedChatIds = excludedChatIds;
            this.excludeMuted = excludeMuted;
            this.excludeRead = excludeRead;
            this.excludeArchived = excludeArchived;
            this.includeContacts = includeContacts;
            this.includeNonContacts = includeNonContacts;
            this.includeBots = includeBots;
            this.includeGroups = includeGroups;
            this.includeChannels = includeChannels;

        }

        @Override
        public int getConstructor() { return -664815123; }

    }


    /**
     * Contains basic information about a chat filter
     *
     * @id - Unique chat filter identifier
     * @title - The title of the filter
     * @iconName - The icon name for short filter representation
     *             One of "All", "Unread", "Unmuted", "Bots", "Channels", "Groups", "Private", "Custom", "Setup", "Cat", "Crown", "Favorite", "Flower", "Game", "Home", "Love", "Mask", "Party", "Sport", "Study", "Trade", "Travel", "Work"
     */
    public static class ChatFilterInfo extends Object {

        public int id;
        public String title;
        public String iconName;

        public ChatFilterInfo() {}

        public ChatFilterInfo(int id, String title, String iconName) {

            this.id = id;
            this.title = title;
            this.iconName = iconName;

        }

        @Override
        public int getConstructor() { return -943721165; }

    }


    /**
     * Describes a recommended chat filter
     *
     * @filter - The chat filter
     * @description - Chat filter description
     */
    public static class RecommendedChatFilter extends Object {

        public ChatFilter filter;
        public String description;

        public RecommendedChatFilter() {}

        public RecommendedChatFilter(ChatFilter filter, String description) {

            this.filter = filter;
            this.description = description;

        }

        @Override
        public int getConstructor() { return 36048610; }

    }


    /**
     * Contains a list of recommended chat filters
     *
     * @chatFilters - List of recommended chat filters
     */
    public static class RecommendedChatFilters extends Object {

        public RecommendedChatFilter[] chatFilters;

        public RecommendedChatFilters() {}

        public RecommendedChatFilters(RecommendedChatFilter[] chatFilters) {

            this.chatFilters = chatFilters;

        }

        @Override
        public int getConstructor() { return -263416880; }

    }


    /**
     * Describes a list of chats
     */
    public static abstract class ChatList extends Object {}

    /**
     * A main list of chats
     */
    public static class ChatListMain extends ChatList {

        @Override
        public int getConstructor() { return -400991316; }

    }


    /**
     * A list of chats usually located at the top of the main chat list
     * Unmuted chats are automatically moved from the Archive to the Main chat list when a new message arrives
     */
    public static class ChatListArchive extends ChatList {

        @Override
        public int getConstructor() { return 362770115; }

    }


    /**
     * A list of chats belonging to a chat filter
     *
     * @chatFilterId - Chat filter identifier
     */
    public static class ChatListFilter extends ChatList {

        public int chatFilterId;

        public ChatListFilter() {}

        public ChatListFilter(int chatFilterId) {

            this.chatFilterId = chatFilterId;

        }

        @Override
        public int getConstructor() { return -2022707655; }

    }


    /**
     * Contains a list of chat lists
     *
     * @chatLists - List of chat lists
     */
    public static class ChatLists extends Object {

        public ChatList[] chatLists;

        public ChatLists() {}

        public ChatLists(ChatList[] chatLists) {

            this.chatLists = chatLists;

        }

        @Override
        public int getConstructor() { return -258292771; }

    }


    /**
     * Describes a reason why an external chat is shown in a chat list
     */
    public static abstract class ChatSource extends Object {}

    /**
     * The chat is sponsored by the user's MTProxy server
     */
    public static class ChatSourceMtprotoProxy extends ChatSource {

        @Override
        public int getConstructor() { return 394074115; }

    }


    /**
     * The chat contains a public service announcement
     *
     * @type - The type of the announcement
     * @text - The text of the announcement
     */
    public static class ChatSourcePublicServiceAnnouncement extends ChatSource {

        public String type;
        public String text;

        public ChatSourcePublicServiceAnnouncement() {}

        public ChatSourcePublicServiceAnnouncement(String type, String text) {

            this.type = type;
            this.text = text;

        }

        @Override
        public int getConstructor() { return -328571244; }

    }


    /**
     * Describes a position of a chat in a chat list
     *
     * @list - The chat list
     * @order - A parameter used to determine order of the chat in the chat list
     *          Chats must be sorted by the pair (order, chat.id) in descending order
     * @isPinned - True, if the chat is pinned in the chat list
     * @source - Source of the chat in the chat list
     */
    public static class ChatPosition extends Object {

        public ChatList list;
        public long order;
        public boolean isPinned;
        @Nullable public ChatSource source;

        public ChatPosition() {}

        public ChatPosition(ChatList list, long order, boolean isPinned, @Nullable ChatSource source) {

            this.list = list;
            this.order = order;
            this.isPinned = isPinned;
            this.source = source;

        }

        @Override
        public int getConstructor() { return -622557355; }

    }


    /**
     * A chat
     * (Can be a private chat, basic group, supergroup, or secret chat)
     *
     * @id - Chat unique identifier
     * @type - Type of the chat
     * @title - Chat title
     * @photo - Chat photo
     * @permissions - Actions that non-administrator chat members are allowed to take in the chat
     * @lastMessage - Last message in the chat
     * @positions - Positions of the chat in chat lists
     * @isMarkedAsUnread - True, if the chat is marked as unread
     * @isBlocked - True, if the chat is blocked by the current user and private messages from the chat can't be received
     * @hasScheduledMessages - True, if the chat has scheduled messages
     * @canBeDeletedOnlyForSelf - True, if the chat messages can be deleted only for the current user while other users will continue to see the messages
     * @canBeDeletedForAllUsers - True, if the chat messages can be deleted for all users
     * @canBeReported - True, if the chat can be reported to Telegram moderators through reportChat
     * @defaultDisableNotification - Default value of the disable_notification parameter, used when a message is sent to the chat
     * @unreadCount - Number of unread messages in the chat
     * @lastReadInboxMessageId - Identifier of the last read incoming message
     * @lastReadOutboxMessageId - Identifier of the last read outgoing message
     * @unreadMentionCount - Number of unread messages with a mention/reply in the chat
     * @notificationSettings - Notification settings for this chat
     * @actionBar - Describes actions which should be possible to do through a chat action bar
     * @pinnedMessageId - Identifier of the pinned message in the chat
     *                    0 if none
     * @replyMarkupMessageId - Identifier of the message from which reply markup needs to be used
     *                         0 if there is no default custom reply markup in the chat
     * @draftMessage - A draft of a message in the chat
     * @clientData - Contains application-specific data associated with the chat
     *               (For example, the chat scroll position or local chat notification settings can be stored here.) Persistent if the message database is used
     */
    public static class Chat extends Object {

        public long id;
        public ChatType type;
        public String title;
        @Nullable public ChatPhotoInfo photo;
        public ChatPermissions permissions;
        @Nullable public Message lastMessage;
        public ChatPosition[] positions;
        public boolean isMarkedAsUnread;
        public boolean isBlocked;
        public boolean hasScheduledMessages;
        public boolean canBeDeletedOnlyForSelf;
        public boolean canBeDeletedForAllUsers;
        public boolean canBeReported;
        public boolean defaultDisableNotification;
        public int unreadCount;
        public long lastReadInboxMessageId;
        public long lastReadOutboxMessageId;
        public int unreadMentionCount;
        public ChatNotificationSettings notificationSettings;
        @Nullable public ChatActionBar actionBar;
        public long pinnedMessageId;
        public long replyMarkupMessageId;
        @Nullable public DraftMessage draftMessage;
        public String clientData;

        public Chat() {}

        public Chat(long id, ChatType type, String title, @Nullable ChatPhotoInfo photo, ChatPermissions permissions, @Nullable Message lastMessage, ChatPosition[] positions, boolean isMarkedAsUnread, boolean isBlocked, boolean hasScheduledMessages, boolean canBeDeletedOnlyForSelf, boolean canBeDeletedForAllUsers, boolean canBeReported, boolean defaultDisableNotification, int unreadCount, long lastReadInboxMessageId, long lastReadOutboxMessageId, int unreadMentionCount, ChatNotificationSettings notificationSettings, @Nullable ChatActionBar actionBar, long pinnedMessageId, long replyMarkupMessageId, @Nullable DraftMessage draftMessage, String clientData) {

            this.id = id;
            this.type = type;
            this.title = title;
            this.photo = photo;
            this.permissions = permissions;
            this.lastMessage = lastMessage;
            this.positions = positions;
            this.isMarkedAsUnread = isMarkedAsUnread;
            this.isBlocked = isBlocked;
            this.hasScheduledMessages = hasScheduledMessages;
            this.canBeDeletedOnlyForSelf = canBeDeletedOnlyForSelf;
            this.canBeDeletedForAllUsers = canBeDeletedForAllUsers;
            this.canBeReported = canBeReported;
            this.defaultDisableNotification = defaultDisableNotification;
            this.unreadCount = unreadCount;
            this.lastReadInboxMessageId = lastReadInboxMessageId;
            this.lastReadOutboxMessageId = lastReadOutboxMessageId;
            this.unreadMentionCount = unreadMentionCount;
            this.notificationSettings = notificationSettings;
            this.actionBar = actionBar;
            this.pinnedMessageId = pinnedMessageId;
            this.replyMarkupMessageId = replyMarkupMessageId;
            this.draftMessage = draftMessage;
            this.clientData = clientData;

        }

        @Override
        public int getConstructor() { return 1881489172; }

    }


    /**
     * Represents a list of chats
     *
     * @totalCount - Approximate total count of chats found
     * @chatIds - List of chat identifiers
     */
    public static class Chats extends Object {

        public int totalCount;
        public long[] chatIds;

        public Chats() {}

        public Chats(int totalCount, long[] chatIds) {

            this.totalCount = totalCount;
            this.chatIds = chatIds;

        }

        @Override
        public int getConstructor() { return 1809654812; }

    }


    /**
     * Describes a chat located nearby
     *
     * @chatId - Chat identifier
     * @distance - Distance to the chat location in meters
     */
    public static class ChatNearby extends Object {

        public long chatId;
        public int distance;

        public ChatNearby() {}

        public ChatNearby(long chatId, int distance) {

            this.chatId = chatId;
            this.distance = distance;

        }

        @Override
        public int getConstructor() { return 48120405; }

    }


    /**
     * Represents a list of chats located nearby
     *
     * @usersNearby - List of users nearby
     * @supergroupsNearby - List of location-based supergroups nearby
     */
    public static class ChatsNearby extends Object {

        public ChatNearby[] usersNearby;
        public ChatNearby[] supergroupsNearby;

        public ChatsNearby() {}

        public ChatsNearby(ChatNearby[] usersNearby, ChatNearby[] supergroupsNearby) {

            this.usersNearby = usersNearby;
            this.supergroupsNearby = supergroupsNearby;

        }

        @Override
        public int getConstructor() { return 187746081; }

    }


    /**
     * Contains a chat invite link
     *
     * @inviteLink - Chat invite link
     */
    public static class ChatInviteLink extends Object {

        public String inviteLink;

        public ChatInviteLink() {}

        public ChatInviteLink(String inviteLink) {

            this.inviteLink = inviteLink;

        }

        @Override
        public int getConstructor() { return -882072492; }

    }


    /**
     * Contains information about a chat invite link
     *
     * @chatId - Chat identifier of the invite link
     *           0 if the user has no access to the chat before joining
     * @accessibleFor - If non-zero, the remaining time for which read access is granted to the chat, in seconds
     * @type - Contains information about the type of the chat
     * @title - Title of the chat
     * @photo - Chat photo
     * @memberCount - Number of members in the chat
     * @memberUserIds - User identifiers of some chat members that may be known to the current user
     * @isPublic - True, if the chat is a public supergroup or channel, i.e
     *             It has a username or it is a location-based supergroup
     */
    public static class ChatInviteLinkInfo extends Object {

        public long chatId;
        public int accessibleFor;
        public ChatType type;
        public String title;
        @Nullable public ChatPhotoInfo photo;
        public int memberCount;
        public int[] memberUserIds;
        public boolean isPublic;

        public ChatInviteLinkInfo() {}

        public ChatInviteLinkInfo(long chatId, int accessibleFor, ChatType type, String title, @Nullable ChatPhotoInfo photo, int memberCount, int[] memberUserIds, boolean isPublic) {

            this.chatId = chatId;
            this.accessibleFor = accessibleFor;
            this.type = type;
            this.title = title;
            this.photo = photo;
            this.memberCount = memberCount;
            this.memberUserIds = memberUserIds;
            this.isPublic = isPublic;

        }

        @Override
        public int getConstructor() { return 910695551; }

    }


    /**
     * Describes a type of public chats
     */
    public static abstract class PublicChatType extends Object {}

    /**
     * The chat is public, because it has username
     */
    public static class PublicChatTypeHasUsername extends PublicChatType {

        @Override
        public int getConstructor() { return 350789758; }

    }


    /**
     * The chat is public, because it is a location-based supergroup
     */
    public static class PublicChatTypeIsLocationBased extends PublicChatType {

        @Override
        public int getConstructor() { return 1183735952; }

    }


    /**
     * Describes actions which should be possible to do through a chat action bar
     */
    public static abstract class ChatActionBar extends Object {}

    /**
     * The chat can be reported as spam using the method reportChat with the reason chatReportReasonSpam
     *
     * @canUnarchive - If true, the chat was automatically archived and can be moved back to the main chat list using addChatToList simultaneously with setting chat notification settings to default using setChatNotificationSettings
     */
    public static class ChatActionBarReportSpam extends ChatActionBar {

        public boolean canUnarchive;

        public ChatActionBarReportSpam() {}

        public ChatActionBarReportSpam(boolean canUnarchive) {

            this.canUnarchive = canUnarchive;

        }

        @Override
        public int getConstructor() { return -1312758246; }

    }


    /**
     * The chat is a location-based supergroup, which can be reported as having unrelated location using the method reportChat with the reason chatReportReasonUnrelatedLocation
     */
    public static class ChatActionBarReportUnrelatedLocation extends ChatActionBar {

        @Override
        public int getConstructor() { return 758175489; }

    }


    /**
     * The chat is a private or secret chat, which can be reported using the method reportChat, or the other user can be blocked using the method blockUser, or the other user can be added to the contact list using the method addContact
     *
     * @canUnarchive - If true, the chat was automatically archived and can be moved back to the main chat list using addChatToList simultaneously with setting chat notification settings to default using setChatNotificationSettings
     * @distance - If non-negative, the current user was found by the peer through searchChatsNearby and this is the distance between the users
     */
    public static class ChatActionBarReportAddBlock extends ChatActionBar {

        public boolean canUnarchive;
        public int distance;

        public ChatActionBarReportAddBlock() {}

        public ChatActionBarReportAddBlock(boolean canUnarchive, int distance) {

            this.canUnarchive = canUnarchive;
            this.distance = distance;

        }

        @Override
        public int getConstructor() { return -914150419; }

    }


    /**
     * The chat is a private or secret chat and the other user can be added to the contact list using the method addContact
     */
    public static class ChatActionBarAddContact extends ChatActionBar {

        @Override
        public int getConstructor() { return -733325295; }

    }


    /**
     * The chat is a private or secret chat with a mutual contact and the user's phone number can be shared with the other user using the method sharePhoneNumber
     */
    public static class ChatActionBarSharePhoneNumber extends ChatActionBar {

        @Override
        public int getConstructor() { return 35188697; }

    }


    /**
     * Describes a keyboard button type
     */
    public static abstract class KeyboardButtonType extends Object {}

    /**
     * A simple button, with text that should be sent when the button is pressed
     */
    public static class KeyboardButtonTypeText extends KeyboardButtonType {

        @Override
        public int getConstructor() { return -1773037256; }

    }


    /**
     * A button that sends the user's phone number when pressed
     * Available only in private chats
     */
    public static class KeyboardButtonTypeRequestPhoneNumber extends KeyboardButtonType {

        @Override
        public int getConstructor() { return -1529235527; }

    }


    /**
     * A button that sends the user's location when pressed
     * Available only in private chats
     */
    public static class KeyboardButtonTypeRequestLocation extends KeyboardButtonType {

        @Override
        public int getConstructor() { return -125661955; }

    }


    /**
     * A button that allows the user to create and send a poll when pressed
     * Available only in private chats
     *
     * @forceRegular - If true, only regular polls must be allowed to create
     * @forceQuiz - If true, only polls in quiz mode must be allowed to create
     */
    public static class KeyboardButtonTypeRequestPoll extends KeyboardButtonType {

        public boolean forceRegular;
        public boolean forceQuiz;

        public KeyboardButtonTypeRequestPoll() {}

        public KeyboardButtonTypeRequestPoll(boolean forceRegular, boolean forceQuiz) {

            this.forceRegular = forceRegular;
            this.forceQuiz = forceQuiz;

        }

        @Override
        public int getConstructor() { return 1902435512; }

    }


    /**
     * Represents a single button in a bot keyboard
     *
     * @text - Text of the button
     * @type - Type of the button
     */
    public static class KeyboardButton extends Object {

        public String text;
        public KeyboardButtonType type;

        public KeyboardButton() {}

        public KeyboardButton(String text, KeyboardButtonType type) {

            this.text = text;
            this.type = type;

        }

        @Override
        public int getConstructor() { return -2069836172; }

    }


    /**
     * Describes the type of an inline keyboard button
     */
    public static abstract class InlineKeyboardButtonType extends Object {}

    /**
     * A button that opens a specified URL
     *
     * @url - HTTP or tg:// URL to open
     */
    public static class InlineKeyboardButtonTypeUrl extends InlineKeyboardButtonType {

        public String url;

        public InlineKeyboardButtonTypeUrl() {}

        public InlineKeyboardButtonTypeUrl(String url) {

            this.url = url;

        }

        @Override
        public int getConstructor() { return 1130741420; }

    }


    /**
     * A button that opens a specified URL and automatically logs in in current user if they allowed to do that
     *
     * @url - An HTTP URL to open
     * @id - Unique button identifier
     * @forwardText - If non-empty, new text of the button in forwarded messages
     */
    public static class InlineKeyboardButtonTypeLoginUrl extends InlineKeyboardButtonType {

        public String url;
        public int id;
        public String forwardText;

        public InlineKeyboardButtonTypeLoginUrl() {}

        public InlineKeyboardButtonTypeLoginUrl(String url, int id, String forwardText) {

            this.url = url;
            this.id = id;
            this.forwardText = forwardText;

        }

        @Override
        public int getConstructor() { return 281435539; }

    }


    /**
     * A button that sends a callback query to a bot
     *
     * @data - Data to be sent to the bot via a callback query
     */
    public static class InlineKeyboardButtonTypeCallback extends InlineKeyboardButtonType {

        public byte[] data;

        public InlineKeyboardButtonTypeCallback() {}

        public InlineKeyboardButtonTypeCallback(byte[] data) {

            this.data = data;

        }

        @Override
        public int getConstructor() { return -1127515139; }

    }


    /**
     * A button that asks for password of the current user and then sends a callback query to a bot
     *
     * @data - Data to be sent to the bot via a callback query
     */
    public static class InlineKeyboardButtonTypeCallbackWithPassword extends InlineKeyboardButtonType {

        public byte[] data;

        public InlineKeyboardButtonTypeCallbackWithPassword() {}

        public InlineKeyboardButtonTypeCallbackWithPassword(byte[] data) {

            this.data = data;

        }

        @Override
        public int getConstructor() { return 908018248; }

    }


    /**
     * A button with a game that sends a callback query to a bot
     * This button must be in the first column and row of the keyboard and can be attached only to a message with content of the type messageGame
     */
    public static class InlineKeyboardButtonTypeCallbackGame extends InlineKeyboardButtonType {

        @Override
        public int getConstructor() { return -383429528; }

    }


    /**
     * A button that forces an inline query to the bot to be inserted in the input field
     *
     * @query - Inline query to be sent to the bot
     * @inCurrentChat - True, if the inline query should be sent from the current chat
     */
    public static class InlineKeyboardButtonTypeSwitchInline extends InlineKeyboardButtonType {

        public String query;
        public boolean inCurrentChat;

        public InlineKeyboardButtonTypeSwitchInline() {}

        public InlineKeyboardButtonTypeSwitchInline(String query, boolean inCurrentChat) {

            this.query = query;
            this.inCurrentChat = inCurrentChat;

        }

        @Override
        public int getConstructor() { return -2035563307; }

    }


    /**
     * A button to buy something
     * This button must be in the first column and row of the keyboard and can be attached only to a message with content of the type messageInvoice
     */
    public static class InlineKeyboardButtonTypeBuy extends InlineKeyboardButtonType {

        @Override
        public int getConstructor() { return 1360739440; }

    }


    /**
     * Represents a single button in an inline keyboard
     *
     * @text - Text of the button
     * @type - Type of the button
     */
    public static class InlineKeyboardButton extends Object {

        public String text;
        public InlineKeyboardButtonType type;

        public InlineKeyboardButton() {}

        public InlineKeyboardButton(String text, InlineKeyboardButtonType type) {

            this.text = text;
            this.type = type;

        }

        @Override
        public int getConstructor() { return -372105704; }

    }


    /**
     * Contains a description of a custom keyboard and actions that can be done with it to quickly reply to bots
     */
    public static abstract class ReplyMarkup extends Object {}

    /**
     * Instructs application to remove the keyboard once this message has been received
     * This kind of keyboard can't be received in an incoming message
     * Instead, UpdateChatReplyMarkup with message_id == 0 will be sent
     *
     * @isPersonal - True, if the keyboard is removed only for the mentioned users or the target user of a reply
     */
    public static class ReplyMarkupRemoveKeyboard extends ReplyMarkup {

        public boolean isPersonal;

        public ReplyMarkupRemoveKeyboard() {}

        public ReplyMarkupRemoveKeyboard(boolean isPersonal) {

            this.isPersonal = isPersonal;

        }

        @Override
        public int getConstructor() { return -691252879; }

    }


    /**
     * Instructs application to force a reply to this message
     *
     * @isPersonal - True, if a forced reply must automatically be shown to the current user
     *               For outgoing messages, specify true to show the forced reply only for the mentioned users and for the target user of a reply
     */
    public static class ReplyMarkupForceReply extends ReplyMarkup {

        public boolean isPersonal;

        public ReplyMarkupForceReply() {}

        public ReplyMarkupForceReply(boolean isPersonal) {

            this.isPersonal = isPersonal;

        }

        @Override
        public int getConstructor() { return 1039104593; }

    }


    /**
     * Contains a custom keyboard layout to quickly reply to bots
     *
     * @rows - A list of rows of bot keyboard buttons
     * @resizeKeyboard - True, if the application needs to resize the keyboard vertically
     * @oneTime - True, if the application needs to hide the keyboard after use
     * @isPersonal - True, if the keyboard must automatically be shown to the current user
     *               For outgoing messages, specify true to show the keyboard only for the mentioned users and for the target user of a reply
     */
    public static class ReplyMarkupShowKeyboard extends ReplyMarkup {

        public KeyboardButton[][] rows;
        public boolean resizeKeyboard;
        public boolean oneTime;
        public boolean isPersonal;

        public ReplyMarkupShowKeyboard() {}

        public ReplyMarkupShowKeyboard(KeyboardButton[][] rows, boolean resizeKeyboard, boolean oneTime, boolean isPersonal) {

            this.rows = rows;
            this.resizeKeyboard = resizeKeyboard;
            this.oneTime = oneTime;
            this.isPersonal = isPersonal;

        }

        @Override
        public int getConstructor() { return -992627133; }

    }


    /**
     * Contains an inline keyboard layout
     *
     * @rows - A list of rows of inline keyboard buttons
     */
    public static class ReplyMarkupInlineKeyboard extends ReplyMarkup {

        public InlineKeyboardButton[][] rows;

        public ReplyMarkupInlineKeyboard() {}

        public ReplyMarkupInlineKeyboard(InlineKeyboardButton[][] rows) {

            this.rows = rows;

        }

        @Override
        public int getConstructor() { return -619317658; }

    }


    /**
     * Contains information about an inline button of type inlineKeyboardButtonTypeLoginUrl
     */
    public static abstract class LoginUrlInfo extends Object {}

    /**
     * An HTTP url needs to be open
     *
     * @url - The URL to open
     * @skipConfirm - True, if there is no need to show an ordinary open URL confirm
     */
    public static class LoginUrlInfoOpen extends LoginUrlInfo {

        public String url;
        public boolean skipConfirm;

        public LoginUrlInfoOpen() {}

        public LoginUrlInfoOpen(String url, boolean skipConfirm) {

            this.url = url;
            this.skipConfirm = skipConfirm;

        }

        @Override
        public int getConstructor() { return -1079045420; }

    }


    /**
     * An authorization confirmation dialog needs to be shown to the user
     *
     * @url - An HTTP URL to be opened
     * @domain - A domain of the URL
     * @botUserId - User identifier of a bot linked with the website
     * @requestWriteAccess - True, if the user needs to be requested to give the permission to the bot to send them messages
     */
    public static class LoginUrlInfoRequestConfirmation extends LoginUrlInfo {

        public String url;
        public String domain;
        public int botUserId;
        public boolean requestWriteAccess;

        public LoginUrlInfoRequestConfirmation() {}

        public LoginUrlInfoRequestConfirmation(String url, String domain, int botUserId, boolean requestWriteAccess) {

            this.url = url;
            this.domain = domain;
            this.botUserId = botUserId;
            this.requestWriteAccess = requestWriteAccess;

        }

        @Override
        public int getConstructor() { return -1761898342; }

    }


    /**
     * Contains information about a message thread
     *
     * @chatId - Identifier of the chat to which the message thread belongs
     * @messageThreadId - Message thread identifier, unique within the chat
     * @messages - The messages from which the thread starts
     *             The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
     * @draftMessage - A draft of a message in the message thread
     */
    public static class MessageThreadInfo extends Object {

        public long chatId;
        public long messageThreadId;
        public Message[] messages;
        @Nullable public DraftMessage draftMessage;

        public MessageThreadInfo() {}

        public MessageThreadInfo(long chatId, long messageThreadId, Message[] messages, @Nullable DraftMessage draftMessage) {

            this.chatId = chatId;
            this.messageThreadId = messageThreadId;
            this.messages = messages;
            this.draftMessage = draftMessage;

        }

        @Override
        public int getConstructor() { return -800726069; }

    }


    /**
     * Describes a text object inside an instant-view web page
     */
    public static abstract class RichText extends Object {}

    /**
     * A plain text
     *
     * @text - Text
     */
    public static class RichTextPlain extends RichText {

        public String text;

        public RichTextPlain() {}

        public RichTextPlain(String text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 482617702; }

    }


    /**
     * A bold rich text
     *
     * @text - Text
     */
    public static class RichTextBold extends RichText {

        public RichText text;

        public RichTextBold() {}

        public RichTextBold(RichText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 1670844268; }

    }


    /**
     * An italicized rich text
     *
     * @text - Text
     */
    public static class RichTextItalic extends RichText {

        public RichText text;

        public RichTextItalic() {}

        public RichTextItalic(RichText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 1853354047; }

    }


    /**
     * An underlined rich text
     *
     * @text - Text
     */
    public static class RichTextUnderline extends RichText {

        public RichText text;

        public RichTextUnderline() {}

        public RichTextUnderline(RichText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return -536019572; }

    }


    /**
     * A strikethrough rich text
     *
     * @text - Text
     */
    public static class RichTextStrikethrough extends RichText {

        public RichText text;

        public RichTextStrikethrough() {}

        public RichTextStrikethrough(RichText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 723413585; }

    }


    /**
     * A fixed-width rich text
     *
     * @text - Text
     */
    public static class RichTextFixed extends RichText {

        public RichText text;

        public RichTextFixed() {}

        public RichTextFixed(RichText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return -1271496249; }

    }


    /**
     * A rich text URL link
     *
     * @text - Text
     * @url - URL
     * @isCached - True, if the URL has cached instant view server-side
     */
    public static class RichTextUrl extends RichText {

        public RichText text;
        public String url;
        public boolean isCached;

        public RichTextUrl() {}

        public RichTextUrl(RichText text, String url, boolean isCached) {

            this.text = text;
            this.url = url;
            this.isCached = isCached;

        }

        @Override
        public int getConstructor() { return 83939092; }

    }


    /**
     * A rich text email link
     *
     * @text - Text
     * @emailAddress - Email address
     */
    public static class RichTextEmailAddress extends RichText {

        public RichText text;
        public String emailAddress;

        public RichTextEmailAddress() {}

        public RichTextEmailAddress(RichText text, String emailAddress) {

            this.text = text;
            this.emailAddress = emailAddress;

        }

        @Override
        public int getConstructor() { return 40018679; }

    }


    /**
     * A subscript rich text
     *
     * @text - Text
     */
    public static class RichTextSubscript extends RichText {

        public RichText text;

        public RichTextSubscript() {}

        public RichTextSubscript(RichText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return -868197812; }

    }


    /**
     * A superscript rich text
     *
     * @text - Text
     */
    public static class RichTextSuperscript extends RichText {

        public RichText text;

        public RichTextSuperscript() {}

        public RichTextSuperscript(RichText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return -382241437; }

    }


    /**
     * A marked rich text
     *
     * @text - Text
     */
    public static class RichTextMarked extends RichText {

        public RichText text;

        public RichTextMarked() {}

        public RichTextMarked(RichText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return -1271999614; }

    }


    /**
     * A rich text phone number
     *
     * @text - Text
     * @phoneNumber - Phone number
     */
    public static class RichTextPhoneNumber extends RichText {

        public RichText text;
        public String phoneNumber;

        public RichTextPhoneNumber() {}

        public RichTextPhoneNumber(RichText text, String phoneNumber) {

            this.text = text;
            this.phoneNumber = phoneNumber;

        }

        @Override
        public int getConstructor() { return 128521539; }

    }


    /**
     * A small image inside the text
     *
     * @document - The image represented as a document
     *             The image can be in GIF, JPEG or PNG format
     * @width - Width of a bounding box in which the image should be shown
     *          0 if unknown
     * @height - Height of a bounding box in which the image should be shown
     *           0 if unknown
     */
    public static class RichTextIcon extends RichText {

        public Document document;
        public int width;
        public int height;

        public RichTextIcon() {}

        public RichTextIcon(Document document, int width, int height) {

            this.document = document;
            this.width = width;
            this.height = height;

        }

        @Override
        public int getConstructor() { return -1480316158; }

    }


    /**
     * A rich text reference of a text on the same web page
     *
     * @text - The text
     * @referenceText - The text to show on click
     * @url - An HTTP URL, opening the reference
     */
    public static class RichTextReference extends RichText {

        public RichText text;
        public RichText referenceText;
        public String url;

        public RichTextReference() {}

        public RichTextReference(RichText text, RichText referenceText, String url) {

            this.text = text;
            this.referenceText = referenceText;
            this.url = url;

        }

        @Override
        public int getConstructor() { return -144433301; }

    }


    /**
     * An anchor
     *
     * @name - Anchor name
     */
    public static class RichTextAnchor extends RichText {

        public String name;

        public RichTextAnchor() {}

        public RichTextAnchor(String name) {

            this.name = name;

        }

        @Override
        public int getConstructor() { return 1316950068; }

    }


    /**
     * A link to an anchor on the same web page
     *
     * @text - The link text
     * @name - The anchor name
     *         If the name is empty, the link should bring back to top
     * @url - An HTTP URL, opening the anchor
     */
    public static class RichTextAnchorLink extends RichText {

        public RichText text;
        public String name;
        public String url;

        public RichTextAnchorLink() {}

        public RichTextAnchorLink(RichText text, String name, String url) {

            this.text = text;
            this.name = name;
            this.url = url;

        }

        @Override
        public int getConstructor() { return -367827961; }

    }


    /**
     * A concatenation of rich texts
     *
     * @texts - Texts
     */
    public static class RichTexts extends RichText {

        public RichText[] texts;

        public RichTexts() {}

        public RichTexts(RichText[] texts) {

            this.texts = texts;

        }

        @Override
        public int getConstructor() { return 1647457821; }

    }


    /**
     * Contains a caption of an instant view web page block, consisting of a text and a trailing credit
     *
     * @text - Content of the caption
     * @credit - Block credit (like HTML tag <cite>)
     */
    public static class PageBlockCaption extends Object {

        public RichText text;
        public RichText credit;

        public PageBlockCaption() {}

        public PageBlockCaption(RichText text, RichText credit) {

            this.text = text;
            this.credit = credit;

        }

        @Override
        public int getConstructor() { return -1180064650; }

    }


    /**
     * Describes an item of a list page block
     *
     * @label - Item label
     * @pageBlocks - Item blocks
     */
    public static class PageBlockListItem extends Object {

        public String label;
        public PageBlock[] pageBlocks;

        public PageBlockListItem() {}

        public PageBlockListItem(String label, PageBlock[] pageBlocks) {

            this.label = label;
            this.pageBlocks = pageBlocks;

        }

        @Override
        public int getConstructor() { return 323186259; }

    }


    /**
     * Describes a horizontal alignment of a table cell content
     */
    public static abstract class PageBlockHorizontalAlignment extends Object {}

    /**
     * The content should be left-aligned
     */
    public static class PageBlockHorizontalAlignmentLeft extends PageBlockHorizontalAlignment {

        @Override
        public int getConstructor() { return 848701417; }

    }


    /**
     * The content should be center-aligned
     */
    public static class PageBlockHorizontalAlignmentCenter extends PageBlockHorizontalAlignment {

        @Override
        public int getConstructor() { return -1009203990; }

    }


    /**
     * The content should be right-aligned
     */
    public static class PageBlockHorizontalAlignmentRight extends PageBlockHorizontalAlignment {

        @Override
        public int getConstructor() { return 1371369214; }

    }


    /**
     * Describes a Vertical alignment of a table cell content
     */
    public static abstract class PageBlockVerticalAlignment extends Object {}

    /**
     * The content should be top-aligned
     */
    public static class PageBlockVerticalAlignmentTop extends PageBlockVerticalAlignment {

        @Override
        public int getConstructor() { return 195500454; }

    }


    /**
     * The content should be middle-aligned
     */
    public static class PageBlockVerticalAlignmentMiddle extends PageBlockVerticalAlignment {

        @Override
        public int getConstructor() { return -2123096587; }

    }


    /**
     * The content should be bottom-aligned
     */
    public static class PageBlockVerticalAlignmentBottom extends PageBlockVerticalAlignment {

        @Override
        public int getConstructor() { return 2092531158; }

    }


    /**
     * Represents a cell of a table
     *
     * @text - Cell text
     *         If the text is null, then the cell should be invisible
     * @isHeader - True, if it is a header cell
     * @colspan - The number of columns the cell should span
     * @rowspan - The number of rows the cell should span
     * @align - Horizontal cell content alignment
     * @valign - Vertical cell content alignment
     */
    public static class PageBlockTableCell extends Object {

        @Nullable public RichText text;
        public boolean isHeader;
        public int colspan;
        public int rowspan;
        public PageBlockHorizontalAlignment align;
        public PageBlockVerticalAlignment valign;

        public PageBlockTableCell() {}

        public PageBlockTableCell(@Nullable RichText text, boolean isHeader, int colspan, int rowspan, PageBlockHorizontalAlignment align, PageBlockVerticalAlignment valign) {

            this.text = text;
            this.isHeader = isHeader;
            this.colspan = colspan;
            this.rowspan = rowspan;
            this.align = align;
            this.valign = valign;

        }

        @Override
        public int getConstructor() { return 1417658214; }

    }


    /**
     * Contains information about a related article
     *
     * @url - Related article URL
     * @title - Article title
     * @description - Article description
     * @photo - Article photo
     * @author - Article author
     * @publishDate - Point in time (Unix timestamp) when the article was published
     *                0 if unknown
     */
    public static class PageBlockRelatedArticle extends Object {

        public String url;
        @Nullable public String title;
        @Nullable public String description;
        @Nullable public Photo photo;
        @Nullable public String author;
        public int publishDate;

        public PageBlockRelatedArticle() {}

        public PageBlockRelatedArticle(String url, @Nullable String title, @Nullable String description, @Nullable Photo photo, @Nullable String author, int publishDate) {

            this.url = url;
            this.title = title;
            this.description = description;
            this.photo = photo;
            this.author = author;
            this.publishDate = publishDate;

        }

        @Override
        public int getConstructor() { return 481199251; }

    }


    /**
     * Describes a block of an instant view web page
     */
    public static abstract class PageBlock extends Object {}

    /**
     * The title of a page
     *
     * @title - Title
     */
    public static class PageBlockTitle extends PageBlock {

        public RichText title;

        public PageBlockTitle() {}

        public PageBlockTitle(RichText title) {

            this.title = title;

        }

        @Override
        public int getConstructor() { return 1629664784; }

    }


    /**
     * The subtitle of a page
     *
     * @subtitle - Subtitle
     */
    public static class PageBlockSubtitle extends PageBlock {

        public RichText subtitle;

        public PageBlockSubtitle() {}

        public PageBlockSubtitle(RichText subtitle) {

            this.subtitle = subtitle;

        }

        @Override
        public int getConstructor() { return 264524263; }

    }


    /**
     * The author and publishing date of a page
     *
     * @author - Author
     * @publishDate - Point in time (Unix timestamp) when the article was published
     *                0 if unknown
     */
    public static class PageBlockAuthorDate extends PageBlock {

        public RichText author;
        public int publishDate;

        public PageBlockAuthorDate() {}

        public PageBlockAuthorDate(RichText author, int publishDate) {

            this.author = author;
            this.publishDate = publishDate;

        }

        @Override
        public int getConstructor() { return 1300231184; }

    }


    /**
     * A header
     *
     * @header - Header
     */
    public static class PageBlockHeader extends PageBlock {

        public RichText header;

        public PageBlockHeader() {}

        public PageBlockHeader(RichText header) {

            this.header = header;

        }

        @Override
        public int getConstructor() { return 1402854811; }

    }


    /**
     * A subheader
     *
     * @subheader - Subheader
     */
    public static class PageBlockSubheader extends PageBlock {

        public RichText subheader;

        public PageBlockSubheader() {}

        public PageBlockSubheader(RichText subheader) {

            this.subheader = subheader;

        }

        @Override
        public int getConstructor() { return 1263956774; }

    }


    /**
     * A kicker
     *
     * @kicker - Kicker
     */
    public static class PageBlockKicker extends PageBlock {

        public RichText kicker;

        public PageBlockKicker() {}

        public PageBlockKicker(RichText kicker) {

            this.kicker = kicker;

        }

        @Override
        public int getConstructor() { return 1361282635; }

    }


    /**
     * A text paragraph
     *
     * @text - Paragraph text
     */
    public static class PageBlockParagraph extends PageBlock {

        public RichText text;

        public PageBlockParagraph() {}

        public PageBlockParagraph(RichText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 1182402406; }

    }


    /**
     * A preformatted text paragraph
     *
     * @text - Paragraph text
     * @language - Programming language for which the text should be formatted
     */
    public static class PageBlockPreformatted extends PageBlock {

        public RichText text;
        public String language;

        public PageBlockPreformatted() {}

        public PageBlockPreformatted(RichText text, String language) {

            this.text = text;
            this.language = language;

        }

        @Override
        public int getConstructor() { return -1066346178; }

    }


    /**
     * The footer of a page
     *
     * @footer - Footer
     */
    public static class PageBlockFooter extends PageBlock {

        public RichText footer;

        public PageBlockFooter() {}

        public PageBlockFooter(RichText footer) {

            this.footer = footer;

        }

        @Override
        public int getConstructor() { return 886429480; }

    }


    /**
     * An empty block separating a page
     */
    public static class PageBlockDivider extends PageBlock {

        @Override
        public int getConstructor() { return -618614392; }

    }


    /**
     * An invisible anchor on a page, which can be used in a URL to open the page from the specified anchor
     *
     * @name - Name of the anchor
     */
    public static class PageBlockAnchor extends PageBlock {

        public String name;

        public PageBlockAnchor() {}

        public PageBlockAnchor(String name) {

            this.name = name;

        }

        @Override
        public int getConstructor() { return -837994576; }

    }


    /**
     * A list of data blocks
     *
     * @items - The items of the list
     */
    public static class PageBlockList extends PageBlock {

        public PageBlockListItem[] items;

        public PageBlockList() {}

        public PageBlockList(PageBlockListItem[] items) {

            this.items = items;

        }

        @Override
        public int getConstructor() { return -1037074852; }

    }


    /**
     * A block quote
     *
     * @text - Quote text
     * @credit - Quote credit
     */
    public static class PageBlockBlockQuote extends PageBlock {

        public RichText text;
        public RichText credit;

        public PageBlockBlockQuote() {}

        public PageBlockBlockQuote(RichText text, RichText credit) {

            this.text = text;
            this.credit = credit;

        }

        @Override
        public int getConstructor() { return 1657834142; }

    }


    /**
     * A pull quote
     *
     * @text - Quote text
     * @credit - Quote credit
     */
    public static class PageBlockPullQuote extends PageBlock {

        public RichText text;
        public RichText credit;

        public PageBlockPullQuote() {}

        public PageBlockPullQuote(RichText text, RichText credit) {

            this.text = text;
            this.credit = credit;

        }

        @Override
        public int getConstructor() { return 490242317; }

    }


    /**
     * An animation
     *
     * @animation - Animation file
     * @caption - Animation caption
     * @needAutoplay - True, if the animation should be played automatically
     */
    public static class PageBlockAnimation extends PageBlock {

        @Nullable public Animation animation;
        public PageBlockCaption caption;
        public boolean needAutoplay;

        public PageBlockAnimation() {}

        public PageBlockAnimation(@Nullable Animation animation, PageBlockCaption caption, boolean needAutoplay) {

            this.animation = animation;
            this.caption = caption;
            this.needAutoplay = needAutoplay;

        }

        @Override
        public int getConstructor() { return 1355669513; }

    }


    /**
     * An audio file
     *
     * @audio - Audio file
     * @caption - Audio file caption
     */
    public static class PageBlockAudio extends PageBlock {

        @Nullable public Audio audio;
        public PageBlockCaption caption;

        public PageBlockAudio() {}

        public PageBlockAudio(@Nullable Audio audio, PageBlockCaption caption) {

            this.audio = audio;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return -63371245; }

    }


    /**
     * A photo
     *
     * @photo - Photo file
     * @caption - Photo caption
     * @url - URL that needs to be opened when the photo is clicked
     */
    public static class PageBlockPhoto extends PageBlock {

        @Nullable public Photo photo;
        public PageBlockCaption caption;
        public String url;

        public PageBlockPhoto() {}

        public PageBlockPhoto(@Nullable Photo photo, PageBlockCaption caption, String url) {

            this.photo = photo;
            this.caption = caption;
            this.url = url;

        }

        @Override
        public int getConstructor() { return 417601156; }

    }


    /**
     * A video
     *
     * @video - Video file
     * @caption - Video caption
     * @needAutoplay - True, if the video should be played automatically
     * @isLooped - True, if the video should be looped
     */
    public static class PageBlockVideo extends PageBlock {

        @Nullable public Video video;
        public PageBlockCaption caption;
        public boolean needAutoplay;
        public boolean isLooped;

        public PageBlockVideo() {}

        public PageBlockVideo(@Nullable Video video, PageBlockCaption caption, boolean needAutoplay, boolean isLooped) {

            this.video = video;
            this.caption = caption;
            this.needAutoplay = needAutoplay;
            this.isLooped = isLooped;

        }

        @Override
        public int getConstructor() { return 510041394; }

    }


    /**
     * A voice note
     *
     * @voiceNote - Voice note
     * @caption - Voice note caption
     */
    public static class PageBlockVoiceNote extends PageBlock {

        @Nullable public VoiceNote voiceNote;
        public PageBlockCaption caption;

        public PageBlockVoiceNote() {}

        public PageBlockVoiceNote(@Nullable VoiceNote voiceNote, PageBlockCaption caption) {

            this.voiceNote = voiceNote;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 1823310463; }

    }


    /**
     * A page cover
     *
     * @cover - Cover
     */
    public static class PageBlockCover extends PageBlock {

        public PageBlock cover;

        public PageBlockCover() {}

        public PageBlockCover(PageBlock cover) {

            this.cover = cover;

        }

        @Override
        public int getConstructor() { return 972174080; }

    }


    /**
     * An embedded web page
     *
     * @url - Web page URL, if available
     * @html - HTML-markup of the embedded page
     * @posterPhoto - Poster photo, if available
     * @width - Block width
     *          0 if unknown
     * @height - Block height
     *           0 if unknown
     * @caption - Block caption
     * @isFullWidth - True, if the block should be full width
     * @allowScrolling - True, if scrolling should be allowed
     */
    public static class PageBlockEmbedded extends PageBlock {

        public String url;
        public String html;
        @Nullable public Photo posterPhoto;
        public int width;
        public int height;
        public PageBlockCaption caption;
        public boolean isFullWidth;
        public boolean allowScrolling;

        public PageBlockEmbedded() {}

        public PageBlockEmbedded(String url, String html, @Nullable Photo posterPhoto, int width, int height, PageBlockCaption caption, boolean isFullWidth, boolean allowScrolling) {

            this.url = url;
            this.html = html;
            this.posterPhoto = posterPhoto;
            this.width = width;
            this.height = height;
            this.caption = caption;
            this.isFullWidth = isFullWidth;
            this.allowScrolling = allowScrolling;

        }

        @Override
        public int getConstructor() { return -1942577763; }

    }


    /**
     * An embedded post
     *
     * @url - Web page URL
     * @author - Post author
     * @authorPhoto - Post author photo
     * @date - Point in time (Unix timestamp) when the post was created
     *         0 if unknown
     * @pageBlocks - Post content
     * @caption - Post caption
     */
    public static class PageBlockEmbeddedPost extends PageBlock {

        public String url;
        public String author;
        @Nullable public Photo authorPhoto;
        public int date;
        public PageBlock[] pageBlocks;
        public PageBlockCaption caption;

        public PageBlockEmbeddedPost() {}

        public PageBlockEmbeddedPost(String url, String author, @Nullable Photo authorPhoto, int date, PageBlock[] pageBlocks, PageBlockCaption caption) {

            this.url = url;
            this.author = author;
            this.authorPhoto = authorPhoto;
            this.date = date;
            this.pageBlocks = pageBlocks;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 397600949; }

    }


    /**
     * A collage
     *
     * @pageBlocks - Collage item contents
     * @caption - Block caption
     */
    public static class PageBlockCollage extends PageBlock {

        public PageBlock[] pageBlocks;
        public PageBlockCaption caption;

        public PageBlockCollage() {}

        public PageBlockCollage(PageBlock[] pageBlocks, PageBlockCaption caption) {

            this.pageBlocks = pageBlocks;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 1163760110; }

    }


    /**
     * A slideshow
     *
     * @pageBlocks - Slideshow item contents
     * @caption - Block caption
     */
    public static class PageBlockSlideshow extends PageBlock {

        public PageBlock[] pageBlocks;
        public PageBlockCaption caption;

        public PageBlockSlideshow() {}

        public PageBlockSlideshow(PageBlock[] pageBlocks, PageBlockCaption caption) {

            this.pageBlocks = pageBlocks;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 539217375; }

    }


    /**
     * A link to a chat
     *
     * @title - Chat title
     * @photo - Chat photo
     * @username - Chat username, by which all other information about the chat should be resolved
     */
    public static class PageBlockChatLink extends PageBlock {

        public String title;
        @Nullable public ChatPhotoInfo photo;
        public String username;

        public PageBlockChatLink() {}

        public PageBlockChatLink(String title, @Nullable ChatPhotoInfo photo, String username) {

            this.title = title;
            this.photo = photo;
            this.username = username;

        }

        @Override
        public int getConstructor() { return -202091253; }

    }


    /**
     * A table
     *
     * @caption - Table caption
     * @cells - Table cells
     * @isBordered - True, if the table is bordered
     * @isStriped - True, if the table is striped
     */
    public static class PageBlockTable extends PageBlock {

        public RichText caption;
        public PageBlockTableCell[][] cells;
        public boolean isBordered;
        public boolean isStriped;

        public PageBlockTable() {}

        public PageBlockTable(RichText caption, PageBlockTableCell[][] cells, boolean isBordered, boolean isStriped) {

            this.caption = caption;
            this.cells = cells;
            this.isBordered = isBordered;
            this.isStriped = isStriped;

        }

        @Override
        public int getConstructor() { return -942649288; }

    }


    /**
     * A collapsible block
     *
     * @header - Always visible heading for the block
     * @pageBlocks - Block contents
     * @isOpen - True, if the block is open by default
     */
    public static class PageBlockDetails extends PageBlock {

        public RichText header;
        public PageBlock[] pageBlocks;
        public boolean isOpen;

        public PageBlockDetails() {}

        public PageBlockDetails(RichText header, PageBlock[] pageBlocks, boolean isOpen) {

            this.header = header;
            this.pageBlocks = pageBlocks;
            this.isOpen = isOpen;

        }

        @Override
        public int getConstructor() { return -1599869809; }

    }


    /**
     * Related articles
     *
     * @header - Block header
     * @articles - List of related articles
     */
    public static class PageBlockRelatedArticles extends PageBlock {

        public RichText header;
        public PageBlockRelatedArticle[] articles;

        public PageBlockRelatedArticles() {}

        public PageBlockRelatedArticles(RichText header, PageBlockRelatedArticle[] articles) {

            this.header = header;
            this.articles = articles;

        }

        @Override
        public int getConstructor() { return -1807324374; }

    }


    /**
     * A map
     *
     * @location - Location of the map center
     * @zoom - Map zoom level
     * @width - Map width
     * @height - Map height
     * @caption - Block caption
     */
    public static class PageBlockMap extends PageBlock {

        public Location location;
        public int zoom;
        public int width;
        public int height;
        public PageBlockCaption caption;

        public PageBlockMap() {}

        public PageBlockMap(Location location, int zoom, int width, int height, PageBlockCaption caption) {

            this.location = location;
            this.zoom = zoom;
            this.width = width;
            this.height = height;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 1510961171; }

    }


    /**
     * Describes an instant view page for a web page
     *
     * @pageBlocks - Content of the web page
     * @viewCount - Number of the instant view views
     *              0 if unknown
     * @version - Version of the instant view, currently can be 1 or 2
     * @isRtl - True, if the instant view must be shown from right to left
     * @isFull - True, if the instant view contains the full page
     *           A network request might be needed to get the full web page instant view
     */
    public static class WebPageInstantView extends Object {

        public PageBlock[] pageBlocks;
        public int viewCount;
        public int version;
        public boolean isRtl;
        public boolean isFull;

        public WebPageInstantView() {}

        public WebPageInstantView(PageBlock[] pageBlocks, int viewCount, int version, boolean isRtl, boolean isFull) {

            this.pageBlocks = pageBlocks;
            this.viewCount = viewCount;
            this.version = version;
            this.isRtl = isRtl;
            this.isFull = isFull;

        }

        @Override
        public int getConstructor() { return 1069193541; }

    }


    /**
     * Describes a web page preview
     *
     * @url - Original URL of the link
     * @displayUrl - URL to display
     * @type - Type of the web page
     *         Can be: article, photo, audio, video, document, profile, app, or something else
     * @siteName - Short name of the site (e.g., Google Docs, App Store)
     * @title - Title of the content
     * @description - Description of the content
     * @photo - Image representing the content
     * @embedUrl - URL to show in the embedded preview
     * @embedType - MIME type of the embedded preview, (e.g., text/html or video/mp4)
     * @embedWidth - Width of the embedded preview
     * @embedHeight - Height of the embedded preview
     * @duration - Duration of the content, in seconds
     * @author - Author of the content
     * @animation - Preview of the content as an animation, if available
     * @audio - Preview of the content as an audio file, if available
     * @document - Preview of the content as a document, if available (currently only available for small PDF files and ZIP archives)
     * @sticker - Preview of the content as a sticker for small WEBP files, if available
     * @video - Preview of the content as a video, if available
     * @videoNote - Preview of the content as a video note, if available
     * @voiceNote - Preview of the content as a voice note, if available
     * @instantViewVersion - Version of instant view, available for the web page (currently can be 1 or 2), 0 if none
     */
    public static class WebPage extends Object {

        public String url;
        public String displayUrl;
        public String type;
        public String siteName;
        public String title;
        public FormattedText description;
        @Nullable public Photo photo;
        public String embedUrl;
        public String embedType;
        public int embedWidth;
        public int embedHeight;
        public int duration;
        public String author;
        @Nullable public Animation animation;
        @Nullable public Audio audio;
        @Nullable public Document document;
        @Nullable public Sticker sticker;
        @Nullable public Video video;
        @Nullable public VideoNote videoNote;
        @Nullable public VoiceNote voiceNote;
        public int instantViewVersion;

        public WebPage() {}

        public WebPage(String url, String displayUrl, String type, String siteName, String title, FormattedText description, @Nullable Photo photo, String embedUrl, String embedType, int embedWidth, int embedHeight, int duration, String author, @Nullable Animation animation, @Nullable Audio audio, @Nullable Document document, @Nullable Sticker sticker, @Nullable Video video, @Nullable VideoNote videoNote, @Nullable VoiceNote voiceNote, int instantViewVersion) {

            this.url = url;
            this.displayUrl = displayUrl;
            this.type = type;
            this.siteName = siteName;
            this.title = title;
            this.description = description;
            this.photo = photo;
            this.embedUrl = embedUrl;
            this.embedType = embedType;
            this.embedWidth = embedWidth;
            this.embedHeight = embedHeight;
            this.duration = duration;
            this.author = author;
            this.animation = animation;
            this.audio = audio;
            this.document = document;
            this.sticker = sticker;
            this.video = video;
            this.videoNote = videoNote;
            this.voiceNote = voiceNote;
            this.instantViewVersion = instantViewVersion;

        }

        @Override
        public int getConstructor() { return -577333714; }

    }


    /**
     * Contains information about a country
     *
     * @countryCode - A two-letter ISO 3166-1 alpha-2 country code
     * @name - Native name of the country
     * @englishName - English name of the country
     * @isHidden - True, if the country should be hidden from the list of all countries
     * @callingCodes - List of country calling codes
     */
    public static class CountryInfo extends Object {

        public String countryCode;
        public String name;
        public String englishName;
        public boolean isHidden;
        public String[] callingCodes;

        public CountryInfo() {}

        public CountryInfo(String countryCode, String name, String englishName, boolean isHidden, String[] callingCodes) {

            this.countryCode = countryCode;
            this.name = name;
            this.englishName = englishName;
            this.isHidden = isHidden;
            this.callingCodes = callingCodes;

        }

        @Override
        public int getConstructor() { return 1617195722; }

    }


    /**
     * Contains information about countries
     *
     * @countries - The list of countries
     */
    public static class Countries extends Object {

        public CountryInfo[] countries;

        public Countries() {}

        public Countries(CountryInfo[] countries) {

            this.countries = countries;

        }

        @Override
        public int getConstructor() { return 1854211813; }

    }


    /**
     * Contains information about a phone number
     *
     * @country - Information about the country to which the phone number belongs
     * @countryCallingCode - The part of the phone number denoting country calling code or its part
     * @formattedPhoneNumber - The phone number without country calling code formatted accordingly to local rules
     */
    public static class PhoneNumberInfo extends Object {

        @Nullable public CountryInfo country;
        public String countryCallingCode;
        public String formattedPhoneNumber;

        public PhoneNumberInfo() {}

        public PhoneNumberInfo(@Nullable CountryInfo country, String countryCallingCode, String formattedPhoneNumber) {

            this.country = country;
            this.countryCallingCode = countryCallingCode;
            this.formattedPhoneNumber = formattedPhoneNumber;

        }

        @Override
        public int getConstructor() { return 560180961; }

    }


    /**
     * Describes an action associated with a bank card number
     *
     * @text - Action text
     * @url - The URL to be opened
     */
    public static class BankCardActionOpenUrl extends Object {

        public String text;
        public String url;

        public BankCardActionOpenUrl() {}

        public BankCardActionOpenUrl(String text, String url) {

            this.text = text;
            this.url = url;

        }

        @Override
        public int getConstructor() { return -196454267; }

    }


    /**
     * Information about a bank card
     *
     * @title - Title of the bank card description
     * @actions - Actions that can be done with the bank card number
     */
    public static class BankCardInfo extends Object {

        public String title;
        public BankCardActionOpenUrl[] actions;

        public BankCardInfo() {}

        public BankCardInfo(String title, BankCardActionOpenUrl[] actions) {

            this.title = title;
            this.actions = actions;

        }

        @Override
        public int getConstructor() { return -2116647730; }

    }


    /**
     * Describes an address
     *
     * @countryCode - A two-letter ISO 3166-1 alpha-2 country code
     * @state - State, if applicable
     * @city - City
     * @streetLine1 - First line of the address
     * @streetLine2 - Second line of the address
     * @postalCode - Address postal code
     */
    public static class Address extends Object {

        public String countryCode;
        public String state;
        public String city;
        public String streetLine1;
        public String streetLine2;
        public String postalCode;

        public Address() {}

        public Address(String countryCode, String state, String city, String streetLine1, String streetLine2, String postalCode) {

            this.countryCode = countryCode;
            this.state = state;
            this.city = city;
            this.streetLine1 = streetLine1;
            this.streetLine2 = streetLine2;
            this.postalCode = postalCode;

        }

        @Override
        public int getConstructor() { return -2043654342; }

    }


    /**
     * Portion of the price of a product (e.g., "delivery cost", "tax amount")
     *
     * @label - Label for this portion of the product price
     * @amount - Currency amount in minimal quantity of the currency
     */
    public static class LabeledPricePart extends Object {

        public String label;
        public long amount;

        public LabeledPricePart() {}

        public LabeledPricePart(String label, long amount) {

            this.label = label;
            this.amount = amount;

        }

        @Override
        public int getConstructor() { return 552789798; }

    }


    /**
     * Product invoice
     *
     * @currency - ISO 4217 currency code
     * @priceParts - A list of objects used to calculate the total price of the product
     * @isTest - True, if the payment is a test payment
     * @needName - True, if the user's name is needed for payment
     * @needPhoneNumber - True, if the user's phone number is needed for payment
     * @needEmailAddress - True, if the user's email address is needed for payment
     * @needShippingAddress - True, if the user's shipping address is needed for payment
     * @sendPhoneNumberToProvider - True, if the user's phone number will be sent to the provider
     * @sendEmailAddressToProvider - True, if the user's email address will be sent to the provider
     * @isFlexible - True, if the total price depends on the shipping method
     */
    public static class Invoice extends Object {

        public String currency;
        public LabeledPricePart[] priceParts;
        public boolean isTest;
        public boolean needName;
        public boolean needPhoneNumber;
        public boolean needEmailAddress;
        public boolean needShippingAddress;
        public boolean sendPhoneNumberToProvider;
        public boolean sendEmailAddressToProvider;
        public boolean isFlexible;

        public Invoice() {}

        public Invoice(String currency, LabeledPricePart[] priceParts, boolean isTest, boolean needName, boolean needPhoneNumber, boolean needEmailAddress, boolean needShippingAddress, boolean sendPhoneNumberToProvider, boolean sendEmailAddressToProvider, boolean isFlexible) {

            this.currency = currency;
            this.priceParts = priceParts;
            this.isTest = isTest;
            this.needName = needName;
            this.needPhoneNumber = needPhoneNumber;
            this.needEmailAddress = needEmailAddress;
            this.needShippingAddress = needShippingAddress;
            this.sendPhoneNumberToProvider = sendPhoneNumberToProvider;
            this.sendEmailAddressToProvider = sendEmailAddressToProvider;
            this.isFlexible = isFlexible;

        }

        @Override
        public int getConstructor() { return -368451690; }

    }


    /**
     * Order information
     *
     * @name - Name of the user
     * @phoneNumber - Phone number of the user
     * @emailAddress - Email address of the user
     * @shippingAddress - Shipping address for this order
     */
    public static class OrderInfo extends Object {

        public String name;
        public String phoneNumber;
        public String emailAddress;
        @Nullable public Address shippingAddress;

        public OrderInfo() {}

        public OrderInfo(String name, String phoneNumber, String emailAddress, @Nullable Address shippingAddress) {

            this.name = name;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
            this.shippingAddress = shippingAddress;

        }

        @Override
        public int getConstructor() { return 783997294; }

    }


    /**
     * One shipping option
     *
     * @id - Shipping option identifier
     * @title - Option title
     * @priceParts - A list of objects used to calculate the total shipping costs
     */
    public static class ShippingOption extends Object {

        public String id;
        public String title;
        public LabeledPricePart[] priceParts;

        public ShippingOption() {}

        public ShippingOption(String id, String title, LabeledPricePart[] priceParts) {

            this.id = id;
            this.title = title;
            this.priceParts = priceParts;

        }

        @Override
        public int getConstructor() { return 1425690001; }

    }


    /**
     * Contains information about saved card credentials
     *
     * @id - Unique identifier of the saved credentials
     * @title - Title of the saved credentials
     */
    public static class SavedCredentials extends Object {

        public String id;
        public String title;

        public SavedCredentials() {}

        public SavedCredentials(String id, String title) {

            this.id = id;
            this.title = title;

        }

        @Override
        public int getConstructor() { return -370273060; }

    }


    /**
     * Contains information about the payment method chosen by the user
     */
    public static abstract class InputCredentials extends Object {}

    /**
     * Applies if a user chooses some previously saved payment credentials
     * To use their previously saved credentials, the user must have a valid temporary password
     *
     * @savedCredentialsId - Identifier of the saved credentials
     */
    public static class InputCredentialsSaved extends InputCredentials {

        public String savedCredentialsId;

        public InputCredentialsSaved() {}

        public InputCredentialsSaved(String savedCredentialsId) {

            this.savedCredentialsId = savedCredentialsId;

        }

        @Override
        public int getConstructor() { return -2034385364; }

    }


    /**
     * Applies if a user enters new credentials on a payment provider website
     *
     * @data - Contains JSON-encoded data with a credential identifier from the payment provider
     * @allowSave - True, if the credential identifier can be saved on the server side
     */
    public static class InputCredentialsNew extends InputCredentials {

        public String data;
        public boolean allowSave;

        public InputCredentialsNew() {}

        public InputCredentialsNew(String data, boolean allowSave) {

            this.data = data;
            this.allowSave = allowSave;

        }

        @Override
        public int getConstructor() { return -829689558; }

    }


    /**
     * Applies if a user enters new credentials using Android Pay
     *
     * @data - JSON-encoded data with the credential identifier
     */
    public static class InputCredentialsAndroidPay extends InputCredentials {

        public String data;

        public InputCredentialsAndroidPay() {}

        public InputCredentialsAndroidPay(String data) {

            this.data = data;

        }

        @Override
        public int getConstructor() { return 1979566832; }

    }


    /**
     * Applies if a user enters new credentials using Apple Pay
     *
     * @data - JSON-encoded data with the credential identifier
     */
    public static class InputCredentialsApplePay extends InputCredentials {

        public String data;

        public InputCredentialsApplePay() {}

        public InputCredentialsApplePay(String data) {

            this.data = data;

        }

        @Override
        public int getConstructor() { return -1246570799; }

    }


    /**
     * Stripe payment provider
     *
     * @publishableKey - Stripe API publishable key
     * @needCountry - True, if the user country must be provided
     * @needPostalCode - True, if the user ZIP/postal code must be provided
     * @needCardholderName - True, if the cardholder name must be provided
     */
    public static class PaymentsProviderStripe extends Object {

        public String publishableKey;
        public boolean needCountry;
        public boolean needPostalCode;
        public boolean needCardholderName;

        public PaymentsProviderStripe() {}

        public PaymentsProviderStripe(String publishableKey, boolean needCountry, boolean needPostalCode, boolean needCardholderName) {

            this.publishableKey = publishableKey;
            this.needCountry = needCountry;
            this.needPostalCode = needPostalCode;
            this.needCardholderName = needCardholderName;

        }

        @Override
        public int getConstructor() { return 1090791032; }

    }


    /**
     * Contains information about an invoice payment form
     *
     * @invoice - Full information of the invoice
     * @url - Payment form URL
     * @paymentsProvider - Contains information about the payment provider, if available, to support it natively without the need for opening the URL
     * @savedOrderInfo - Saved server-side order information
     * @savedCredentials - Contains information about saved card credentials
     * @canSaveCredentials - True, if the user can choose to save credentials
     * @needPassword - True, if the user will be able to save credentials protected by a password they set up
     */
    public static class PaymentForm extends Object {

        public Invoice invoice;
        public String url;
        @Nullable public PaymentsProviderStripe paymentsProvider;
        @Nullable public OrderInfo savedOrderInfo;
        @Nullable public SavedCredentials savedCredentials;
        public boolean canSaveCredentials;
        public boolean needPassword;

        public PaymentForm() {}

        public PaymentForm(Invoice invoice, String url, @Nullable PaymentsProviderStripe paymentsProvider, @Nullable OrderInfo savedOrderInfo, @Nullable SavedCredentials savedCredentials, boolean canSaveCredentials, boolean needPassword) {

            this.invoice = invoice;
            this.url = url;
            this.paymentsProvider = paymentsProvider;
            this.savedOrderInfo = savedOrderInfo;
            this.savedCredentials = savedCredentials;
            this.canSaveCredentials = canSaveCredentials;
            this.needPassword = needPassword;

        }

        @Override
        public int getConstructor() { return -200418230; }

    }


    /**
     * Contains a temporary identifier of validated order information, which is stored for one hour
     * Also contains the available shipping options
     *
     * @orderInfoId - Temporary identifier of the order information
     * @shippingOptions - Available shipping options
     */
    public static class ValidatedOrderInfo extends Object {

        public String orderInfoId;
        public ShippingOption[] shippingOptions;

        public ValidatedOrderInfo() {}

        public ValidatedOrderInfo(String orderInfoId, ShippingOption[] shippingOptions) {

            this.orderInfoId = orderInfoId;
            this.shippingOptions = shippingOptions;

        }

        @Override
        public int getConstructor() { return 1511451484; }

    }


    /**
     * Contains the result of a payment request
     *
     * @success - True, if the payment request was successful
     *            Otherwise the verification_url will be not empty
     * @verificationUrl - URL for additional payment credentials verification
     */
    public static class PaymentResult extends Object {

        public boolean success;
        public String verificationUrl;

        public PaymentResult() {}

        public PaymentResult(boolean success, String verificationUrl) {

            this.success = success;
            this.verificationUrl = verificationUrl;

        }

        @Override
        public int getConstructor() { return -804263843; }

    }


    /**
     * Contains information about a successful payment
     *
     * @date - Point in time (Unix timestamp) when the payment was made
     * @paymentsProviderUserId - User identifier of the payment provider bot
     * @invoice - Contains information about the invoice
     * @orderInfo - Contains order information
     * @shippingOption - Chosen shipping option
     * @credentialsTitle - Title of the saved credentials
     */
    public static class PaymentReceipt extends Object {

        public int date;
        public int paymentsProviderUserId;
        public Invoice invoice;
        @Nullable public OrderInfo orderInfo;
        @Nullable public ShippingOption shippingOption;
        public String credentialsTitle;

        public PaymentReceipt() {}

        public PaymentReceipt(int date, int paymentsProviderUserId, Invoice invoice, @Nullable OrderInfo orderInfo, @Nullable ShippingOption shippingOption, String credentialsTitle) {

            this.date = date;
            this.paymentsProviderUserId = paymentsProviderUserId;
            this.invoice = invoice;
            this.orderInfo = orderInfo;
            this.shippingOption = shippingOption;
            this.credentialsTitle = credentialsTitle;

        }

        @Override
        public int getConstructor() { return -1171223545; }

    }


    /**
     * File with the date it was uploaded
     *
     * @file - The file
     * @date - Point in time (Unix timestamp) when the file was uploaded
     */
    public static class DatedFile extends Object {

        public File file;
        public int date;

        public DatedFile() {}

        public DatedFile(File file, int date) {

            this.file = file;
            this.date = date;

        }

        @Override
        public int getConstructor() { return -1840795491; }

    }


    /**
     * Contains the type of a Telegram Passport element
     */
    public static abstract class PassportElementType extends Object {}

    /**
     * A Telegram Passport element containing the user's personal details
     */
    public static class PassportElementTypePersonalDetails extends PassportElementType {

        @Override
        public int getConstructor() { return -1032136365; }

    }


    /**
     * A Telegram Passport element containing the user's passport
     */
    public static class PassportElementTypePassport extends PassportElementType {

        @Override
        public int getConstructor() { return -436360376; }

    }


    /**
     * A Telegram Passport element containing the user's driver license
     */
    public static class PassportElementTypeDriverLicense extends PassportElementType {

        @Override
        public int getConstructor() { return 1827298379; }

    }


    /**
     * A Telegram Passport element containing the user's identity card
     */
    public static class PassportElementTypeIdentityCard extends PassportElementType {

        @Override
        public int getConstructor() { return -502356132; }

    }


    /**
     * A Telegram Passport element containing the user's internal passport
     */
    public static class PassportElementTypeInternalPassport extends PassportElementType {

        @Override
        public int getConstructor() { return -793781959; }

    }


    /**
     * A Telegram Passport element containing the user's address
     */
    public static class PassportElementTypeAddress extends PassportElementType {

        @Override
        public int getConstructor() { return 496327874; }

    }


    /**
     * A Telegram Passport element containing the user's utility bill
     */
    public static class PassportElementTypeUtilityBill extends PassportElementType {

        @Override
        public int getConstructor() { return 627084906; }

    }


    /**
     * A Telegram Passport element containing the user's bank statement
     */
    public static class PassportElementTypeBankStatement extends PassportElementType {

        @Override
        public int getConstructor() { return 574095667; }

    }


    /**
     * A Telegram Passport element containing the user's rental agreement
     */
    public static class PassportElementTypeRentalAgreement extends PassportElementType {

        @Override
        public int getConstructor() { return -2060583280; }

    }


    /**
     * A Telegram Passport element containing the registration page of the user's passport
     */
    public static class PassportElementTypePassportRegistration extends PassportElementType {

        @Override
        public int getConstructor() { return -159478209; }

    }


    /**
     * A Telegram Passport element containing the user's temporary registration
     */
    public static class PassportElementTypeTemporaryRegistration extends PassportElementType {

        @Override
        public int getConstructor() { return 1092498527; }

    }


    /**
     * A Telegram Passport element containing the user's phone number
     */
    public static class PassportElementTypePhoneNumber extends PassportElementType {

        @Override
        public int getConstructor() { return -995361172; }

    }


    /**
     * A Telegram Passport element containing the user's email address
     */
    public static class PassportElementTypeEmailAddress extends PassportElementType {

        @Override
        public int getConstructor() { return -79321405; }

    }


    /**
     * Represents a date according to the Gregorian calendar
     *
     * @day - Day of the month, 1-31
     * @month - Month, 1-12
     * @year - Year, 1-9999
     */
    public static class Date extends Object {

        public int day;
        public int month;
        public int year;

        public Date() {}

        public Date(int day, int month, int year) {

            this.day = day;
            this.month = month;
            this.year = year;

        }

        @Override
        public int getConstructor() { return -277956960; }

    }


    /**
     * Contains the user's personal details
     *
     * @firstName - First name of the user written in English
     * @middleName - Middle name of the user written in English
     * @lastName - Last name of the user written in English
     * @nativeFirstName - Native first name of the user
     * @nativeMiddleName - Native middle name of the user
     * @nativeLastName - Native last name of the user
     * @birthdate - Birthdate of the user
     * @gender - Gender of the user, "male" or "female"
     * @countryCode - A two-letter ISO 3166-1 alpha-2 country code of the user's country
     * @residenceCountryCode - A two-letter ISO 3166-1 alpha-2 country code of the user's residence country
     */
    public static class PersonalDetails extends Object {

        public String firstName;
        public String middleName;
        public String lastName;
        public String nativeFirstName;
        public String nativeMiddleName;
        public String nativeLastName;
        public Date birthdate;
        public String gender;
        public String countryCode;
        public String residenceCountryCode;

        public PersonalDetails() {}

        public PersonalDetails(String firstName, String middleName, String lastName, String nativeFirstName, String nativeMiddleName, String nativeLastName, Date birthdate, String gender, String countryCode, String residenceCountryCode) {

            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.nativeFirstName = nativeFirstName;
            this.nativeMiddleName = nativeMiddleName;
            this.nativeLastName = nativeLastName;
            this.birthdate = birthdate;
            this.gender = gender;
            this.countryCode = countryCode;
            this.residenceCountryCode = residenceCountryCode;

        }

        @Override
        public int getConstructor() { return -1061656137; }

    }


    /**
     * An identity document
     *
     * @number - Document number
     * @expiryDate - Document expiry date
     * @frontSide - Front side of the document
     * @reverseSide - Reverse side of the document
     *                Only for driver license and identity card
     * @selfie - Selfie with the document
     * @translation - List of files containing a certified English translation of the document
     */
    public static class IdentityDocument extends Object {

        public String number;
        @Nullable public Date expiryDate;
        public DatedFile frontSide;
        public DatedFile reverseSide;
        @Nullable public DatedFile selfie;
        public DatedFile[] translation;

        public IdentityDocument() {}

        public IdentityDocument(String number, @Nullable Date expiryDate, DatedFile frontSide, DatedFile reverseSide, @Nullable DatedFile selfie, DatedFile[] translation) {

            this.number = number;
            this.expiryDate = expiryDate;
            this.frontSide = frontSide;
            this.reverseSide = reverseSide;
            this.selfie = selfie;
            this.translation = translation;

        }

        @Override
        public int getConstructor() { return 445952972; }

    }


    /**
     * An identity document to be saved to Telegram Passport
     *
     * @number - Document number
     * @expiryDate - Document expiry date, if available
     * @frontSide - Front side of the document
     * @reverseSide - Reverse side of the document
     *                Only for driver license and identity card
     * @selfie - Selfie with the document, if available
     * @translation - List of files containing a certified English translation of the document
     */
    public static class InputIdentityDocument extends Object {

        public String number;
        public Date expiryDate;
        public InputFile frontSide;
        public InputFile reverseSide;
        public InputFile selfie;
        public InputFile[] translation;

        public InputIdentityDocument() {}

        public InputIdentityDocument(String number, Date expiryDate, InputFile frontSide, InputFile reverseSide, InputFile selfie, InputFile[] translation) {

            this.number = number;
            this.expiryDate = expiryDate;
            this.frontSide = frontSide;
            this.reverseSide = reverseSide;
            this.selfie = selfie;
            this.translation = translation;

        }

        @Override
        public int getConstructor() { return -381776063; }

    }


    /**
     * A personal document, containing some information about a user
     *
     * @files - List of files containing the pages of the document
     * @translation - List of files containing a certified English translation of the document
     */
    public static class PersonalDocument extends Object {

        public DatedFile[] files;
        public DatedFile[] translation;

        public PersonalDocument() {}

        public PersonalDocument(DatedFile[] files, DatedFile[] translation) {

            this.files = files;
            this.translation = translation;

        }

        @Override
        public int getConstructor() { return -1011634661; }

    }


    /**
     * A personal document to be saved to Telegram Passport
     *
     * @files - List of files containing the pages of the document
     * @translation - List of files containing a certified English translation of the document
     */
    public static class InputPersonalDocument extends Object {

        public InputFile[] files;
        public InputFile[] translation;

        public InputPersonalDocument() {}

        public InputPersonalDocument(InputFile[] files, InputFile[] translation) {

            this.files = files;
            this.translation = translation;

        }

        @Override
        public int getConstructor() { return 1676966826; }

    }


    /**
     * Contains information about a Telegram Passport element
     */
    public static abstract class PassportElement extends Object {}

    /**
     * A Telegram Passport element containing the user's personal details
     *
     * @personalDetails - Personal details of the user
     */
    public static class PassportElementPersonalDetails extends PassportElement {

        public PersonalDetails personalDetails;

        public PassportElementPersonalDetails() {}

        public PassportElementPersonalDetails(PersonalDetails personalDetails) {

            this.personalDetails = personalDetails;

        }

        @Override
        public int getConstructor() { return 1217724035; }

    }


    /**
     * A Telegram Passport element containing the user's passport
     *
     * @passport - Passport
     */
    public static class PassportElementPassport extends PassportElement {

        public IdentityDocument passport;

        public PassportElementPassport() {}

        public PassportElementPassport(IdentityDocument passport) {

            this.passport = passport;

        }

        @Override
        public int getConstructor() { return -263985373; }

    }


    /**
     * A Telegram Passport element containing the user's driver license
     *
     * @driverLicense - Driver license
     */
    public static class PassportElementDriverLicense extends PassportElement {

        public IdentityDocument driverLicense;

        public PassportElementDriverLicense() {}

        public PassportElementDriverLicense(IdentityDocument driverLicense) {

            this.driverLicense = driverLicense;

        }

        @Override
        public int getConstructor() { return 1643580589; }

    }


    /**
     * A Telegram Passport element containing the user's identity card
     *
     * @identityCard - Identity card
     */
    public static class PassportElementIdentityCard extends PassportElement {

        public IdentityDocument identityCard;

        public PassportElementIdentityCard() {}

        public PassportElementIdentityCard(IdentityDocument identityCard) {

            this.identityCard = identityCard;

        }

        @Override
        public int getConstructor() { return 2083775797; }

    }


    /**
     * A Telegram Passport element containing the user's internal passport
     *
     * @internalPassport - Internal passport
     */
    public static class PassportElementInternalPassport extends PassportElement {

        public IdentityDocument internalPassport;

        public PassportElementInternalPassport() {}

        public PassportElementInternalPassport(IdentityDocument internalPassport) {

            this.internalPassport = internalPassport;

        }

        @Override
        public int getConstructor() { return 36220295; }

    }


    /**
     * A Telegram Passport element containing the user's address
     *
     * @address - Address
     */
    public static class PassportElementAddress extends PassportElement {

        public Address address;

        public PassportElementAddress() {}

        public PassportElementAddress(Address address) {

            this.address = address;

        }

        @Override
        public int getConstructor() { return -782625232; }

    }


    /**
     * A Telegram Passport element containing the user's utility bill
     *
     * @utilityBill - Utility bill
     */
    public static class PassportElementUtilityBill extends PassportElement {

        public PersonalDocument utilityBill;

        public PassportElementUtilityBill() {}

        public PassportElementUtilityBill(PersonalDocument utilityBill) {

            this.utilityBill = utilityBill;

        }

        @Override
        public int getConstructor() { return -234611246; }

    }


    /**
     * A Telegram Passport element containing the user's bank statement
     *
     * @bankStatement - Bank statement
     */
    public static class PassportElementBankStatement extends PassportElement {

        public PersonalDocument bankStatement;

        public PassportElementBankStatement() {}

        public PassportElementBankStatement(PersonalDocument bankStatement) {

            this.bankStatement = bankStatement;

        }

        @Override
        public int getConstructor() { return -366464408; }

    }


    /**
     * A Telegram Passport element containing the user's rental agreement
     *
     * @rentalAgreement - Rental agreement
     */
    public static class PassportElementRentalAgreement extends PassportElement {

        public PersonalDocument rentalAgreement;

        public PassportElementRentalAgreement() {}

        public PassportElementRentalAgreement(PersonalDocument rentalAgreement) {

            this.rentalAgreement = rentalAgreement;

        }

        @Override
        public int getConstructor() { return -290141400; }

    }


    /**
     * A Telegram Passport element containing the user's passport registration pages
     *
     * @passportRegistration - Passport registration pages
     */
    public static class PassportElementPassportRegistration extends PassportElement {

        public PersonalDocument passportRegistration;

        public PassportElementPassportRegistration() {}

        public PassportElementPassportRegistration(PersonalDocument passportRegistration) {

            this.passportRegistration = passportRegistration;

        }

        @Override
        public int getConstructor() { return 618323071; }

    }


    /**
     * A Telegram Passport element containing the user's temporary registration
     *
     * @temporaryRegistration - Temporary registration
     */
    public static class PassportElementTemporaryRegistration extends PassportElement {

        public PersonalDocument temporaryRegistration;

        public PassportElementTemporaryRegistration() {}

        public PassportElementTemporaryRegistration(PersonalDocument temporaryRegistration) {

            this.temporaryRegistration = temporaryRegistration;

        }

        @Override
        public int getConstructor() { return 1237626864; }

    }


    /**
     * A Telegram Passport element containing the user's phone number
     *
     * @phoneNumber - Phone number
     */
    public static class PassportElementPhoneNumber extends PassportElement {

        public String phoneNumber;

        public PassportElementPhoneNumber() {}

        public PassportElementPhoneNumber(String phoneNumber) {

            this.phoneNumber = phoneNumber;

        }

        @Override
        public int getConstructor() { return -1320118375; }

    }


    /**
     * A Telegram Passport element containing the user's email address
     *
     * @emailAddress - Email address
     */
    public static class PassportElementEmailAddress extends PassportElement {

        public String emailAddress;

        public PassportElementEmailAddress() {}

        public PassportElementEmailAddress(String emailAddress) {

            this.emailAddress = emailAddress;

        }

        @Override
        public int getConstructor() { return -1528129531; }

    }


    /**
     * Contains information about a Telegram Passport element to be saved
     */
    public static abstract class InputPassportElement extends Object {}

    /**
     * A Telegram Passport element to be saved containing the user's personal details
     *
     * @personalDetails - Personal details of the user
     */
    public static class InputPassportElementPersonalDetails extends InputPassportElement {

        public PersonalDetails personalDetails;

        public InputPassportElementPersonalDetails() {}

        public InputPassportElementPersonalDetails(PersonalDetails personalDetails) {

            this.personalDetails = personalDetails;

        }

        @Override
        public int getConstructor() { return 164791359; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's passport
     *
     * @passport - The passport to be saved
     */
    public static class InputPassportElementPassport extends InputPassportElement {

        public InputIdentityDocument passport;

        public InputPassportElementPassport() {}

        public InputPassportElementPassport(InputIdentityDocument passport) {

            this.passport = passport;

        }

        @Override
        public int getConstructor() { return -497011356; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's driver license
     *
     * @driverLicense - The driver license to be saved
     */
    public static class InputPassportElementDriverLicense extends InputPassportElement {

        public InputIdentityDocument driverLicense;

        public InputPassportElementDriverLicense() {}

        public InputPassportElementDriverLicense(InputIdentityDocument driverLicense) {

            this.driverLicense = driverLicense;

        }

        @Override
        public int getConstructor() { return 304813264; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's identity card
     *
     * @identityCard - The identity card to be saved
     */
    public static class InputPassportElementIdentityCard extends InputPassportElement {

        public InputIdentityDocument identityCard;

        public InputPassportElementIdentityCard() {}

        public InputPassportElementIdentityCard(InputIdentityDocument identityCard) {

            this.identityCard = identityCard;

        }

        @Override
        public int getConstructor() { return -9963390; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's internal passport
     *
     * @internalPassport - The internal passport to be saved
     */
    public static class InputPassportElementInternalPassport extends InputPassportElement {

        public InputIdentityDocument internalPassport;

        public InputPassportElementInternalPassport() {}

        public InputPassportElementInternalPassport(InputIdentityDocument internalPassport) {

            this.internalPassport = internalPassport;

        }

        @Override
        public int getConstructor() { return 715360043; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's address
     *
     * @address - The address to be saved
     */
    public static class InputPassportElementAddress extends InputPassportElement {

        public Address address;

        public InputPassportElementAddress() {}

        public InputPassportElementAddress(Address address) {

            this.address = address;

        }

        @Override
        public int getConstructor() { return 461630480; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's utility bill
     *
     * @utilityBill - The utility bill to be saved
     */
    public static class InputPassportElementUtilityBill extends InputPassportElement {

        public InputPersonalDocument utilityBill;

        public InputPassportElementUtilityBill() {}

        public InputPassportElementUtilityBill(InputPersonalDocument utilityBill) {

            this.utilityBill = utilityBill;

        }

        @Override
        public int getConstructor() { return 1389203841; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's bank statement
     *
     * @bankStatement - The bank statement to be saved
     */
    public static class InputPassportElementBankStatement extends InputPassportElement {

        public InputPersonalDocument bankStatement;

        public InputPassportElementBankStatement() {}

        public InputPassportElementBankStatement(InputPersonalDocument bankStatement) {

            this.bankStatement = bankStatement;

        }

        @Override
        public int getConstructor() { return -26585208; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's rental agreement
     *
     * @rentalAgreement - The rental agreement to be saved
     */
    public static class InputPassportElementRentalAgreement extends InputPassportElement {

        public InputPersonalDocument rentalAgreement;

        public InputPassportElementRentalAgreement() {}

        public InputPassportElementRentalAgreement(InputPersonalDocument rentalAgreement) {

            this.rentalAgreement = rentalAgreement;

        }

        @Override
        public int getConstructor() { return 1736154155; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's passport registration
     *
     * @passportRegistration - The passport registration page to be saved
     */
    public static class InputPassportElementPassportRegistration extends InputPassportElement {

        public InputPersonalDocument passportRegistration;

        public InputPassportElementPassportRegistration() {}

        public InputPassportElementPassportRegistration(InputPersonalDocument passportRegistration) {

            this.passportRegistration = passportRegistration;

        }

        @Override
        public int getConstructor() { return 1314562128; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's temporary registration
     *
     * @temporaryRegistration - The temporary registration document to be saved
     */
    public static class InputPassportElementTemporaryRegistration extends InputPassportElement {

        public InputPersonalDocument temporaryRegistration;

        public InputPassportElementTemporaryRegistration() {}

        public InputPassportElementTemporaryRegistration(InputPersonalDocument temporaryRegistration) {

            this.temporaryRegistration = temporaryRegistration;

        }

        @Override
        public int getConstructor() { return -1913238047; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's phone number
     *
     * @phoneNumber - The phone number to be saved
     */
    public static class InputPassportElementPhoneNumber extends InputPassportElement {

        public String phoneNumber;

        public InputPassportElementPhoneNumber() {}

        public InputPassportElementPhoneNumber(String phoneNumber) {

            this.phoneNumber = phoneNumber;

        }

        @Override
        public int getConstructor() { return 1319357497; }

    }


    /**
     * A Telegram Passport element to be saved containing the user's email address
     *
     * @emailAddress - The email address to be saved
     */
    public static class InputPassportElementEmailAddress extends InputPassportElement {

        public String emailAddress;

        public InputPassportElementEmailAddress() {}

        public InputPassportElementEmailAddress(String emailAddress) {

            this.emailAddress = emailAddress;

        }

        @Override
        public int getConstructor() { return -248605659; }

    }


    /**
     * Contains information about saved Telegram Passport elements
     *
     * @elements - Telegram Passport elements
     */
    public static class PassportElements extends Object {

        public PassportElement[] elements;

        public PassportElements() {}

        public PassportElements(PassportElement[] elements) {

            this.elements = elements;

        }

        @Override
        public int getConstructor() { return 1264617556; }

    }


    /**
     * Contains the description of an error in a Telegram Passport element
     */
    public static abstract class PassportElementErrorSource extends Object {}

    /**
     * The element contains an error in an unspecified place
     * The error will be considered resolved when new data is added
     */
    public static class PassportElementErrorSourceUnspecified extends PassportElementErrorSource {

        @Override
        public int getConstructor() { return -378320830; }

    }


    /**
     * One of the data fields contains an error
     * The error will be considered resolved when the value of the field changes
     *
     * @fieldName - Field name
     */
    public static class PassportElementErrorSourceDataField extends PassportElementErrorSource {

        public String fieldName;

        public PassportElementErrorSourceDataField() {}

        public PassportElementErrorSourceDataField(String fieldName) {

            this.fieldName = fieldName;

        }

        @Override
        public int getConstructor() { return -308650776; }

    }


    /**
     * The front side of the document contains an error
     * The error will be considered resolved when the file with the front side changes
     */
    public static class PassportElementErrorSourceFrontSide extends PassportElementErrorSource {

        @Override
        public int getConstructor() { return 1895658292; }

    }


    /**
     * The reverse side of the document contains an error
     * The error will be considered resolved when the file with the reverse side changes
     */
    public static class PassportElementErrorSourceReverseSide extends PassportElementErrorSource {

        @Override
        public int getConstructor() { return 1918630391; }

    }


    /**
     * The selfie with the document contains an error
     * The error will be considered resolved when the file with the selfie changes
     */
    public static class PassportElementErrorSourceSelfie extends PassportElementErrorSource {

        @Override
        public int getConstructor() { return -797043672; }

    }


    /**
     * One of files with the translation of the document contains an error
     * The error will be considered resolved when the file changes
     *
     * @fileIndex - Index of a file with the error
     */
    public static class PassportElementErrorSourceTranslationFile extends PassportElementErrorSource {

        public int fileIndex;

        public PassportElementErrorSourceTranslationFile() {}

        public PassportElementErrorSourceTranslationFile(int fileIndex) {

            this.fileIndex = fileIndex;

        }

        @Override
        public int getConstructor() { return -689621228; }

    }


    /**
     * The translation of the document contains an error
     * The error will be considered resolved when the list of translation files changes
     */
    public static class PassportElementErrorSourceTranslationFiles extends PassportElementErrorSource {

        @Override
        public int getConstructor() { return 581280796; }

    }


    /**
     * The file contains an error
     * The error will be considered resolved when the file changes
     *
     * @fileIndex - Index of a file with the error
     */
    public static class PassportElementErrorSourceFile extends PassportElementErrorSource {

        public int fileIndex;

        public PassportElementErrorSourceFile() {}

        public PassportElementErrorSourceFile(int fileIndex) {

            this.fileIndex = fileIndex;

        }

        @Override
        public int getConstructor() { return 2020358960; }

    }


    /**
     * The list of attached files contains an error
     * The error will be considered resolved when the list of files changes
     */
    public static class PassportElementErrorSourceFiles extends PassportElementErrorSource {

        @Override
        public int getConstructor() { return 1894164178; }

    }


    /**
     * Contains the description of an error in a Telegram Passport element
     *
     * @type - Type of the Telegram Passport element which has the error
     * @message - Error message
     * @source - Error source
     */
    public static class PassportElementError extends Object {

        public PassportElementType type;
        public String message;
        public PassportElementErrorSource source;

        public PassportElementError() {}

        public PassportElementError(PassportElementType type, String message, PassportElementErrorSource source) {

            this.type = type;
            this.message = message;
            this.source = source;

        }

        @Override
        public int getConstructor() { return -1861902395; }

    }


    /**
     * Contains information about a Telegram Passport element that was requested by a service
     *
     * @type - Type of the element
     * @isSelfieRequired - True, if a selfie is required with the identity document
     * @isTranslationRequired - True, if a certified English translation is required with the document
     * @isNativeNameRequired - True, if personal details must include the user's name in the language of their country of residence
     */
    public static class PassportSuitableElement extends Object {

        public PassportElementType type;
        public boolean isSelfieRequired;
        public boolean isTranslationRequired;
        public boolean isNativeNameRequired;

        public PassportSuitableElement() {}

        public PassportSuitableElement(PassportElementType type, boolean isSelfieRequired, boolean isTranslationRequired, boolean isNativeNameRequired) {

            this.type = type;
            this.isSelfieRequired = isSelfieRequired;
            this.isTranslationRequired = isTranslationRequired;
            this.isNativeNameRequired = isNativeNameRequired;

        }

        @Override
        public int getConstructor() { return -789019876; }

    }


    /**
     * Contains a description of the required Telegram Passport element that was requested by a service
     *
     * @suitableElements - List of Telegram Passport elements any of which is enough to provide
     */
    public static class PassportRequiredElement extends Object {

        public PassportSuitableElement[] suitableElements;

        public PassportRequiredElement() {}

        public PassportRequiredElement(PassportSuitableElement[] suitableElements) {

            this.suitableElements = suitableElements;

        }

        @Override
        public int getConstructor() { return -1983641651; }

    }


    /**
     * Contains information about a Telegram Passport authorization form that was requested
     *
     * @id - Unique identifier of the authorization form
     * @requiredElements - Information about the Telegram Passport elements that need to be provided to complete the form
     * @privacyPolicyUrl - URL for the privacy policy of the service
     */
    public static class PassportAuthorizationForm extends Object {

        public int id;
        public PassportRequiredElement[] requiredElements;
        @Nullable public String privacyPolicyUrl;

        public PassportAuthorizationForm() {}

        public PassportAuthorizationForm(int id, PassportRequiredElement[] requiredElements, @Nullable String privacyPolicyUrl) {

            this.id = id;
            this.requiredElements = requiredElements;
            this.privacyPolicyUrl = privacyPolicyUrl;

        }

        @Override
        public int getConstructor() { return -1070673218; }

    }


    /**
     * Contains information about a Telegram Passport elements and corresponding errors
     *
     * @elements - Telegram Passport elements
     * @errors - Errors in the elements that are already available
     */
    public static class PassportElementsWithErrors extends Object {

        public PassportElement[] elements;
        public PassportElementError[] errors;

        public PassportElementsWithErrors() {}

        public PassportElementsWithErrors(PassportElement[] elements, PassportElementError[] errors) {

            this.elements = elements;
            this.errors = errors;

        }

        @Override
        public int getConstructor() { return 1308923044; }

    }


    /**
     * Contains encrypted Telegram Passport data credentials
     *
     * @data - The encrypted credentials
     * @hash - The decrypted data hash
     * @secret - Secret for data decryption, encrypted with the service's public key
     */
    public static class EncryptedCredentials extends Object {

        public byte[] data;
        public byte[] hash;
        public byte[] secret;

        public EncryptedCredentials() {}

        public EncryptedCredentials(byte[] data, byte[] hash, byte[] secret) {

            this.data = data;
            this.hash = hash;
            this.secret = secret;

        }

        @Override
        public int getConstructor() { return 1331106766; }

    }


    /**
     * Contains information about an encrypted Telegram Passport element
     * For bots only
     *
     * @type - Type of Telegram Passport element
     * @data - Encrypted JSON-encoded data about the user
     * @frontSide - The front side of an identity document
     * @reverseSide - The reverse side of an identity document
     * @selfie - Selfie with the document
     * @translation - List of files containing a certified English translation of the document
     * @files - List of attached files
     * @value - Unencrypted data, phone number or email address
     * @hash - Hash of the entire element
     */
    public static class EncryptedPassportElement extends Object {

        public PassportElementType type;
        public byte[] data;
        public DatedFile frontSide;
        @Nullable public DatedFile reverseSide;
        @Nullable public DatedFile selfie;
        public DatedFile[] translation;
        public DatedFile[] files;
        public String value;
        public String hash;

        public EncryptedPassportElement() {}

        public EncryptedPassportElement(PassportElementType type, byte[] data, DatedFile frontSide, @Nullable DatedFile reverseSide, @Nullable DatedFile selfie, DatedFile[] translation, DatedFile[] files, String value, String hash) {

            this.type = type;
            this.data = data;
            this.frontSide = frontSide;
            this.reverseSide = reverseSide;
            this.selfie = selfie;
            this.translation = translation;
            this.files = files;
            this.value = value;
            this.hash = hash;

        }

        @Override
        public int getConstructor() { return 2002386193; }

    }


    /**
     * Contains the description of an error in a Telegram Passport element
     * For bots only
     */
    public static abstract class InputPassportElementErrorSource extends Object {}

    /**
     * The element contains an error in an unspecified place
     * The error will be considered resolved when new data is added
     *
     * @elementHash - Current hash of the entire element
     */
    public static class InputPassportElementErrorSourceUnspecified extends InputPassportElementErrorSource {

        public byte[] elementHash;

        public InputPassportElementErrorSourceUnspecified() {}

        public InputPassportElementErrorSourceUnspecified(byte[] elementHash) {

            this.elementHash = elementHash;

        }

        @Override
        public int getConstructor() { return 267230319; }

    }


    /**
     * A data field contains an error
     * The error is considered resolved when the field's value changes
     *
     * @fieldName - Field name
     * @dataHash - Current data hash
     */
    public static class InputPassportElementErrorSourceDataField extends InputPassportElementErrorSource {

        public String fieldName;
        public byte[] dataHash;

        public InputPassportElementErrorSourceDataField() {}

        public InputPassportElementErrorSourceDataField(String fieldName, byte[] dataHash) {

            this.fieldName = fieldName;
            this.dataHash = dataHash;

        }

        @Override
        public int getConstructor() { return -426795002; }

    }


    /**
     * The front side of the document contains an error
     * The error is considered resolved when the file with the front side of the document changes
     *
     * @fileHash - Current hash of the file containing the front side
     */
    public static class InputPassportElementErrorSourceFrontSide extends InputPassportElementErrorSource {

        public byte[] fileHash;

        public InputPassportElementErrorSourceFrontSide() {}

        public InputPassportElementErrorSourceFrontSide(byte[] fileHash) {

            this.fileHash = fileHash;

        }

        @Override
        public int getConstructor() { return 588023741; }

    }


    /**
     * The reverse side of the document contains an error
     * The error is considered resolved when the file with the reverse side of the document changes
     *
     * @fileHash - Current hash of the file containing the reverse side
     */
    public static class InputPassportElementErrorSourceReverseSide extends InputPassportElementErrorSource {

        public byte[] fileHash;

        public InputPassportElementErrorSourceReverseSide() {}

        public InputPassportElementErrorSourceReverseSide(byte[] fileHash) {

            this.fileHash = fileHash;

        }

        @Override
        public int getConstructor() { return 413072891; }

    }


    /**
     * The selfie contains an error
     * The error is considered resolved when the file with the selfie changes
     *
     * @fileHash - Current hash of the file containing the selfie
     */
    public static class InputPassportElementErrorSourceSelfie extends InputPassportElementErrorSource {

        public byte[] fileHash;

        public InputPassportElementErrorSourceSelfie() {}

        public InputPassportElementErrorSourceSelfie(byte[] fileHash) {

            this.fileHash = fileHash;

        }

        @Override
        public int getConstructor() { return -773575528; }

    }


    /**
     * One of the files containing the translation of the document contains an error
     * The error is considered resolved when the file with the translation changes
     *
     * @fileHash - Current hash of the file containing the translation
     */
    public static class InputPassportElementErrorSourceTranslationFile extends InputPassportElementErrorSource {

        public byte[] fileHash;

        public InputPassportElementErrorSourceTranslationFile() {}

        public InputPassportElementErrorSourceTranslationFile(byte[] fileHash) {

            this.fileHash = fileHash;

        }

        @Override
        public int getConstructor() { return 505842299; }

    }


    /**
     * The translation of the document contains an error
     * The error is considered resolved when the list of files changes
     *
     * @fileHashes - Current hashes of all files with the translation
     */
    public static class InputPassportElementErrorSourceTranslationFiles extends InputPassportElementErrorSource {

        public byte[][] fileHashes;

        public InputPassportElementErrorSourceTranslationFiles() {}

        public InputPassportElementErrorSourceTranslationFiles(byte[][] fileHashes) {

            this.fileHashes = fileHashes;

        }

        @Override
        public int getConstructor() { return -527254048; }

    }


    /**
     * The file contains an error
     * The error is considered resolved when the file changes
     *
     * @fileHash - Current hash of the file which has the error
     */
    public static class InputPassportElementErrorSourceFile extends InputPassportElementErrorSource {

        public byte[] fileHash;

        public InputPassportElementErrorSourceFile() {}

        public InputPassportElementErrorSourceFile(byte[] fileHash) {

            this.fileHash = fileHash;

        }

        @Override
        public int getConstructor() { return -298492469; }

    }


    /**
     * The list of attached files contains an error
     * The error is considered resolved when the file list changes
     *
     * @fileHashes - Current hashes of all attached files
     */
    public static class InputPassportElementErrorSourceFiles extends InputPassportElementErrorSource {

        public byte[][] fileHashes;

        public InputPassportElementErrorSourceFiles() {}

        public InputPassportElementErrorSourceFiles(byte[][] fileHashes) {

            this.fileHashes = fileHashes;

        }

        @Override
        public int getConstructor() { return -2008541640; }

    }


    /**
     * Contains the description of an error in a Telegram Passport element
     * For bots only
     *
     * @type - Type of Telegram Passport element that has the error
     * @message - Error message
     * @source - Error source
     */
    public static class InputPassportElementError extends Object {

        public PassportElementType type;
        public String message;
        public InputPassportElementErrorSource source;

        public InputPassportElementError() {}

        public InputPassportElementError(PassportElementType type, String message, InputPassportElementErrorSource source) {

            this.type = type;
            this.message = message;
            this.source = source;

        }

        @Override
        public int getConstructor() { return 285756898; }

    }


    /**
     * Contains the content of a message
     */
    public static abstract class MessageContent extends Object {}

    /**
     * A text message
     *
     * @text - Text of the message
     * @webPage - A preview of the web page that's mentioned in the text
     */
    public static class MessageText extends MessageContent {

        public FormattedText text;
        @Nullable public WebPage webPage;

        public MessageText() {}

        public MessageText(FormattedText text, @Nullable WebPage webPage) {

            this.text = text;
            this.webPage = webPage;

        }

        @Override
        public int getConstructor() { return 1989037971; }

    }


    /**
     * An animation message (GIF-style).
     *
     * @animation - The animation description
     * @caption - Animation caption
     * @isSecret - True, if the animation thumbnail must be blurred and the animation must be shown only while tapped
     */
    public static class MessageAnimation extends MessageContent {

        public Animation animation;
        public FormattedText caption;
        public boolean isSecret;

        public MessageAnimation() {}

        public MessageAnimation(Animation animation, FormattedText caption, boolean isSecret) {

            this.animation = animation;
            this.caption = caption;
            this.isSecret = isSecret;

        }

        @Override
        public int getConstructor() { return 1306939396; }

    }


    /**
     * An audio message
     *
     * @audio - The audio description
     * @caption - Audio caption
     */
    public static class MessageAudio extends MessageContent {

        public Audio audio;
        public FormattedText caption;

        public MessageAudio() {}

        public MessageAudio(Audio audio, FormattedText caption) {

            this.audio = audio;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 276722716; }

    }


    /**
     * A document message (general file)
     *
     * @document - The document description
     * @caption - Document caption
     */
    public static class MessageDocument extends MessageContent {

        public Document document;
        public FormattedText caption;

        public MessageDocument() {}

        public MessageDocument(Document document, FormattedText caption) {

            this.document = document;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 596945783; }

    }


    /**
     * A photo message
     *
     * @photo - The photo description
     * @caption - Photo caption
     * @isSecret - True, if the photo must be blurred and must be shown only while tapped
     */
    public static class MessagePhoto extends MessageContent {

        public Photo photo;
        public FormattedText caption;
        public boolean isSecret;

        public MessagePhoto() {}

        public MessagePhoto(Photo photo, FormattedText caption, boolean isSecret) {

            this.photo = photo;
            this.caption = caption;
            this.isSecret = isSecret;

        }

        @Override
        public int getConstructor() { return -1851395174; }

    }


    /**
     * An expired photo message (self-destructed after TTL has elapsed)
     */
    public static class MessageExpiredPhoto extends MessageContent {

        @Override
        public int getConstructor() { return -1404641801; }

    }


    /**
     * A sticker message
     *
     * @sticker - The sticker description
     */
    public static class MessageSticker extends MessageContent {

        public Sticker sticker;

        public MessageSticker() {}

        public MessageSticker(Sticker sticker) {

            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return 1779022878; }

    }


    /**
     * A video message
     *
     * @video - The video description
     * @caption - Video caption
     * @isSecret - True, if the video thumbnail must be blurred and the video must be shown only while tapped
     */
    public static class MessageVideo extends MessageContent {

        public Video video;
        public FormattedText caption;
        public boolean isSecret;

        public MessageVideo() {}

        public MessageVideo(Video video, FormattedText caption, boolean isSecret) {

            this.video = video;
            this.caption = caption;
            this.isSecret = isSecret;

        }

        @Override
        public int getConstructor() { return 2021281344; }

    }


    /**
     * An expired video message (self-destructed after TTL has elapsed)
     */
    public static class MessageExpiredVideo extends MessageContent {

        @Override
        public int getConstructor() { return -1212209981; }

    }


    /**
     * A video note message
     *
     * @videoNote - The video note description
     * @isViewed - True, if at least one of the recipients has viewed the video note
     * @isSecret - True, if the video note thumbnail must be blurred and the video note must be shown only while tapped
     */
    public static class MessageVideoNote extends MessageContent {

        public VideoNote videoNote;
        public boolean isViewed;
        public boolean isSecret;

        public MessageVideoNote() {}

        public MessageVideoNote(VideoNote videoNote, boolean isViewed, boolean isSecret) {

            this.videoNote = videoNote;
            this.isViewed = isViewed;
            this.isSecret = isSecret;

        }

        @Override
        public int getConstructor() { return 963323014; }

    }


    /**
     * A voice note message
     *
     * @voiceNote - The voice note description
     * @caption - Voice note caption
     * @isListened - True, if at least one of the recipients has listened to the voice note
     */
    public static class MessageVoiceNote extends MessageContent {

        public VoiceNote voiceNote;
        public FormattedText caption;
        public boolean isListened;

        public MessageVoiceNote() {}

        public MessageVoiceNote(VoiceNote voiceNote, FormattedText caption, boolean isListened) {

            this.voiceNote = voiceNote;
            this.caption = caption;
            this.isListened = isListened;

        }

        @Override
        public int getConstructor() { return 527777781; }

    }


    /**
     * A message with a location
     *
     * @location - The location description
     * @livePeriod - Time relative to the message sent date until which the location can be updated, in seconds
     * @expiresIn - Left time for which the location can be updated, in seconds
     *              UpdateMessageContent is not sent when this field changes
     */
    public static class MessageLocation extends MessageContent {

        public Location location;
        public int livePeriod;
        public int expiresIn;

        public MessageLocation() {}

        public MessageLocation(Location location, int livePeriod, int expiresIn) {

            this.location = location;
            this.livePeriod = livePeriod;
            this.expiresIn = expiresIn;

        }

        @Override
        public int getConstructor() { return -1301887786; }

    }


    /**
     * A message with information about a venue
     *
     * @venue - The venue description
     */
    public static class MessageVenue extends MessageContent {

        public Venue venue;

        public MessageVenue() {}

        public MessageVenue(Venue venue) {

            this.venue = venue;

        }

        @Override
        public int getConstructor() { return -2146492043; }

    }


    /**
     * A message with a user contact
     *
     * @contact - The contact description
     */
    public static class MessageContact extends MessageContent {

        public Contact contact;

        public MessageContact() {}

        public MessageContact(Contact contact) {

            this.contact = contact;

        }

        @Override
        public int getConstructor() { return -512684966; }

    }


    /**
     * A dice message
     * The dice value is randomly generated by the server
     *
     * @initialStateSticker - The animated sticker with the initial dice animation
     *                        May be null if unknown
     *                        UpdateMessageContent will be sent when the sticker became known
     * @finalStateSticker - The animated sticker with the final dice animation
     *                      May be null if unknown
     *                      UpdateMessageContent will be sent when the sticker became known
     * @emoji - Emoji on which the dice throw animation is based
     * @value - The dice value
     *          If the value is 0, the dice don't have final state yet
     * @successAnimationFrameNumber - Number of frame after which a success animation like a shower of confetti needs to be shown on updateMessageSendSucceeded
     */
    public static class MessageDice extends MessageContent {

        public Sticker initialStateSticker;
        public Sticker finalStateSticker;
        public String emoji;
        public int value;
        public int successAnimationFrameNumber;

        public MessageDice() {}

        public MessageDice(Sticker initialStateSticker, Sticker finalStateSticker, String emoji, int value, int successAnimationFrameNumber) {

            this.initialStateSticker = initialStateSticker;
            this.finalStateSticker = finalStateSticker;
            this.emoji = emoji;
            this.value = value;
            this.successAnimationFrameNumber = successAnimationFrameNumber;

        }

        @Override
        public int getConstructor() { return -1350654849; }

    }


    /**
     * A message with a game
     *
     * @game - The game description
     */
    public static class MessageGame extends MessageContent {

        public Game game;

        public MessageGame() {}

        public MessageGame(Game game) {

            this.game = game;

        }

        @Override
        public int getConstructor() { return -69441162; }

    }


    /**
     * A message with a poll
     *
     * @poll - The poll description
     */
    public static class MessagePoll extends MessageContent {

        public Poll poll;

        public MessagePoll() {}

        public MessagePoll(Poll poll) {

            this.poll = poll;

        }

        @Override
        public int getConstructor() { return -662130099; }

    }


    /**
     * A message with an invoice from a bot
     *
     * @title - Product title
     * @description - Product description
     * @photo - Product photo
     * @currency - Currency for the product price
     * @totalAmount - Product total price in the minimal quantity of the currency
     * @startParameter - Unique invoice bot start_parameter
     *                   To share an invoice use the URL https://t.me/{bot_username}?start={start_parameter}
     * @isTest - True, if the invoice is a test invoice
     * @needShippingAddress - True, if the shipping address should be specified
     * @receiptMessageId - The identifier of the message with the receipt, after the product has been purchased
     */
    public static class MessageInvoice extends MessageContent {

        public String title;
        public String description;
        @Nullable public Photo photo;
        public String currency;
        public long totalAmount;
        public String startParameter;
        public boolean isTest;
        public boolean needShippingAddress;
        public long receiptMessageId;

        public MessageInvoice() {}

        public MessageInvoice(String title, String description, @Nullable Photo photo, String currency, long totalAmount, String startParameter, boolean isTest, boolean needShippingAddress, long receiptMessageId) {

            this.title = title;
            this.description = description;
            this.photo = photo;
            this.currency = currency;
            this.totalAmount = totalAmount;
            this.startParameter = startParameter;
            this.isTest = isTest;
            this.needShippingAddress = needShippingAddress;
            this.receiptMessageId = receiptMessageId;

        }

        @Override
        public int getConstructor() { return -1916671476; }

    }


    /**
     * A message with information about an ended call
     *
     * @isVideo - True, if the call was a video call
     * @discardReason - Reason why the call was discarded
     * @duration - Call duration, in seconds
     */
    public static class MessageCall extends MessageContent {

        public boolean isVideo;
        public CallDiscardReason discardReason;
        public int duration;

        public MessageCall() {}

        public MessageCall(boolean isVideo, CallDiscardReason discardReason, int duration) {

            this.isVideo = isVideo;
            this.discardReason = discardReason;
            this.duration = duration;

        }

        @Override
        public int getConstructor() { return 538893824; }

    }


    /**
     * A newly created basic group
     *
     * @title - Title of the basic group
     * @memberUserIds - User identifiers of members in the basic group
     */
    public static class MessageBasicGroupChatCreate extends MessageContent {

        public String title;
        public int[] memberUserIds;

        public MessageBasicGroupChatCreate() {}

        public MessageBasicGroupChatCreate(String title, int[] memberUserIds) {

            this.title = title;
            this.memberUserIds = memberUserIds;

        }

        @Override
        public int getConstructor() { return 1575377646; }

    }


    /**
     * A newly created supergroup or channel
     *
     * @title - Title of the supergroup or channel
     */
    public static class MessageSupergroupChatCreate extends MessageContent {

        public String title;

        public MessageSupergroupChatCreate() {}

        public MessageSupergroupChatCreate(String title) {

            this.title = title;

        }

        @Override
        public int getConstructor() { return -434325733; }

    }


    /**
     * An updated chat title
     *
     * @title - New chat title
     */
    public static class MessageChatChangeTitle extends MessageContent {

        public String title;

        public MessageChatChangeTitle() {}

        public MessageChatChangeTitle(String title) {

            this.title = title;

        }

        @Override
        public int getConstructor() { return 748272449; }

    }


    /**
     * An updated chat photo
     *
     * @photo - New chat photo
     */
    public static class MessageChatChangePhoto extends MessageContent {

        public ChatPhoto photo;

        public MessageChatChangePhoto() {}

        public MessageChatChangePhoto(ChatPhoto photo) {

            this.photo = photo;

        }

        @Override
        public int getConstructor() { return -813415093; }

    }


    /**
     * A deleted chat photo
     */
    public static class MessageChatDeletePhoto extends MessageContent {

        @Override
        public int getConstructor() { return -184374809; }

    }


    /**
     * New chat members were added
     *
     * @memberUserIds - User identifiers of the new members
     */
    public static class MessageChatAddMembers extends MessageContent {

        public int[] memberUserIds;

        public MessageChatAddMembers() {}

        public MessageChatAddMembers(int[] memberUserIds) {

            this.memberUserIds = memberUserIds;

        }

        @Override
        public int getConstructor() { return 401228326; }

    }


    /**
     * A new member joined the chat by invite link
     */
    public static class MessageChatJoinByLink extends MessageContent {

        @Override
        public int getConstructor() { return 1846493311; }

    }


    /**
     * A chat member was deleted
     *
     * @userId - User identifier of the deleted chat member
     */
    public static class MessageChatDeleteMember extends MessageContent {

        public int userId;

        public MessageChatDeleteMember() {}

        public MessageChatDeleteMember(int userId) {

            this.userId = userId;

        }

        @Override
        public int getConstructor() { return 1164414043; }

    }


    /**
     * A basic group was upgraded to a supergroup and was deactivated as the result
     *
     * @supergroupId - Identifier of the supergroup to which the basic group was upgraded
     */
    public static class MessageChatUpgradeTo extends MessageContent {

        public int supergroupId;

        public MessageChatUpgradeTo() {}

        public MessageChatUpgradeTo(int supergroupId) {

            this.supergroupId = supergroupId;

        }

        @Override
        public int getConstructor() { return 1957816681; }

    }


    /**
     * A supergroup has been created from a basic group
     *
     * @title - Title of the newly created supergroup
     * @basicGroupId - The identifier of the original basic group
     */
    public static class MessageChatUpgradeFrom extends MessageContent {

        public String title;
        public int basicGroupId;

        public MessageChatUpgradeFrom() {}

        public MessageChatUpgradeFrom(String title, int basicGroupId) {

            this.title = title;
            this.basicGroupId = basicGroupId;

        }

        @Override
        public int getConstructor() { return 1642272558; }

    }


    /**
     * A message has been pinned
     *
     * @messageId - Identifier of the pinned message, can be an identifier of a deleted message or 0
     */
    public static class MessagePinMessage extends MessageContent {

        public long messageId;

        public MessagePinMessage() {}

        public MessagePinMessage(long messageId) {

            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return 953503801; }

    }


    /**
     * A screenshot of a message in the chat has been taken
     */
    public static class MessageScreenshotTaken extends MessageContent {

        @Override
        public int getConstructor() { return -1564971605; }

    }


    /**
     * The TTL (Time To Live) setting messages in a secret chat has been changed
     *
     * @ttl - New TTL
     */
    public static class MessageChatSetTtl extends MessageContent {

        public int ttl;

        public MessageChatSetTtl() {}

        public MessageChatSetTtl(int ttl) {

            this.ttl = ttl;

        }

        @Override
        public int getConstructor() { return 1810060209; }

    }


    /**
     * A non-standard action has happened in the chat
     *
     * @text - Message text to be shown in the chat
     */
    public static class MessageCustomServiceAction extends MessageContent {

        public String text;

        public MessageCustomServiceAction() {}

        public MessageCustomServiceAction(String text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 1435879282; }

    }


    /**
     * A new high score was achieved in a game
     *
     * @gameMessageId - Identifier of the message with the game, can be an identifier of a deleted message
     * @gameId - Identifier of the game
     *           May be different from the games presented in the message with the game
     * @score - New score
     */
    public static class MessageGameScore extends MessageContent {

        public long gameMessageId;
        public long gameId;
        public int score;

        public MessageGameScore() {}

        public MessageGameScore(long gameMessageId, long gameId, int score) {

            this.gameMessageId = gameMessageId;
            this.gameId = gameId;
            this.score = score;

        }

        @Override
        public int getConstructor() { return 1344904575; }

    }


    /**
     * A payment has been completed
     *
     * @invoiceMessageId - Identifier of the message with the corresponding invoice
     *                     Can be an identifier of a deleted message
     * @currency - Currency for the price of the product
     * @totalAmount - Total price for the product, in the minimal quantity of the currency
     */
    public static class MessagePaymentSuccessful extends MessageContent {

        public long invoiceMessageId;
        public String currency;
        public long totalAmount;

        public MessagePaymentSuccessful() {}

        public MessagePaymentSuccessful(long invoiceMessageId, String currency, long totalAmount) {

            this.invoiceMessageId = invoiceMessageId;
            this.currency = currency;
            this.totalAmount = totalAmount;

        }

        @Override
        public int getConstructor() { return -595962993; }

    }


    /**
     * A payment has been completed
     * For bots only
     *
     * @invoiceMessageId - Identifier of the message with the corresponding invoice
     *                     Can be an identifier of a deleted message
     * @currency - Currency for price of the product
     * @totalAmount - Total price for the product, in the minimal quantity of the currency
     * @invoicePayload - Invoice payload
     * @shippingOptionId - Identifier of the shipping option chosen by the user
     *                     May be empty if not applicable
     * @orderInfo - Information about the order
     * @telegramPaymentChargeId - Telegram payment identifier
     * @providerPaymentChargeId - Provider payment identifier
     */
    public static class MessagePaymentSuccessfulBot extends MessageContent {

        public long invoiceMessageId;
        public String currency;
        public long totalAmount;
        public byte[] invoicePayload;
        @Nullable public String shippingOptionId;
        @Nullable public OrderInfo orderInfo;
        public String telegramPaymentChargeId;
        public String providerPaymentChargeId;

        public MessagePaymentSuccessfulBot() {}

        public MessagePaymentSuccessfulBot(long invoiceMessageId, String currency, long totalAmount, byte[] invoicePayload, @Nullable String shippingOptionId, @Nullable OrderInfo orderInfo, String telegramPaymentChargeId, String providerPaymentChargeId) {

            this.invoiceMessageId = invoiceMessageId;
            this.currency = currency;
            this.totalAmount = totalAmount;
            this.invoicePayload = invoicePayload;
            this.shippingOptionId = shippingOptionId;
            this.orderInfo = orderInfo;
            this.telegramPaymentChargeId = telegramPaymentChargeId;
            this.providerPaymentChargeId = providerPaymentChargeId;

        }

        @Override
        public int getConstructor() { return -412310696; }

    }


    /**
     * A contact has registered with Telegram
     */
    public static class MessageContactRegistered extends MessageContent {

        @Override
        public int getConstructor() { return -1502020353; }

    }


    /**
     * The current user has connected a website by logging in using Telegram Login Widget on it
     *
     * @domainName - Domain name of the connected website
     */
    public static class MessageWebsiteConnected extends MessageContent {

        public String domainName;

        public MessageWebsiteConnected() {}

        public MessageWebsiteConnected(String domainName) {

            this.domainName = domainName;

        }

        @Override
        public int getConstructor() { return -1074551800; }

    }


    /**
     * Telegram Passport data has been sent
     *
     * @types - List of Telegram Passport element types sent
     */
    public static class MessagePassportDataSent extends MessageContent {

        public PassportElementType[] types;

        public MessagePassportDataSent() {}

        public MessagePassportDataSent(PassportElementType[] types) {

            this.types = types;

        }

        @Override
        public int getConstructor() { return 1017405171; }

    }


    /**
     * Telegram Passport data has been received
     * For bots only
     *
     * @elements - List of received Telegram Passport elements
     * @credentials - Encrypted data credentials
     */
    public static class MessagePassportDataReceived extends MessageContent {

        public EncryptedPassportElement[] elements;
        public EncryptedCredentials credentials;

        public MessagePassportDataReceived() {}

        public MessagePassportDataReceived(EncryptedPassportElement[] elements, EncryptedCredentials credentials) {

            this.elements = elements;
            this.credentials = credentials;

        }

        @Override
        public int getConstructor() { return -1367863624; }

    }


    /**
     * Message content that is not supported in the current TDLib version
     */
    public static class MessageUnsupported extends MessageContent {

        @Override
        public int getConstructor() { return -1816726139; }

    }


    /**
     * Represents a part of the text which must be formatted differently
     */
    public static abstract class TextEntityType extends Object {}

    /**
     * A mention of a user by their username
     */
    public static class TextEntityTypeMention extends TextEntityType {

        @Override
        public int getConstructor() { return 934535013; }

    }


    /**
     * A hashtag text, beginning with "#"
     */
    public static class TextEntityTypeHashtag extends TextEntityType {

        @Override
        public int getConstructor() { return -1023958307; }

    }


    /**
     * A cashtag text, beginning with "$" and consisting of capital english letters (i.e
     * "$USD")
     */
    public static class TextEntityTypeCashtag extends TextEntityType {

        @Override
        public int getConstructor() { return 1222915915; }

    }


    /**
     * A bot command, beginning with "/"
     * This shouldn't be highlighted if there are no bots in the chat
     */
    public static class TextEntityTypeBotCommand extends TextEntityType {

        @Override
        public int getConstructor() { return -1150997581; }

    }


    /**
     * An HTTP URL
     */
    public static class TextEntityTypeUrl extends TextEntityType {

        @Override
        public int getConstructor() { return -1312762756; }

    }


    /**
     * An email address
     */
    public static class TextEntityTypeEmailAddress extends TextEntityType {

        @Override
        public int getConstructor() { return 1425545249; }

    }


    /**
     * A phone number
     */
    public static class TextEntityTypePhoneNumber extends TextEntityType {

        @Override
        public int getConstructor() { return -1160140246; }

    }


    /**
     * A bank card number
     * The getBankCardInfo method can be used to get information about the bank card
     */
    public static class TextEntityTypeBankCardNumber extends TextEntityType {

        @Override
        public int getConstructor() { return 105986320; }

    }


    /**
     * A bold text
     */
    public static class TextEntityTypeBold extends TextEntityType {

        @Override
        public int getConstructor() { return -1128210000; }

    }


    /**
     * An italic text
     */
    public static class TextEntityTypeItalic extends TextEntityType {

        @Override
        public int getConstructor() { return -118253987; }

    }


    /**
     * An underlined text
     */
    public static class TextEntityTypeUnderline extends TextEntityType {

        @Override
        public int getConstructor() { return 792317842; }

    }


    /**
     * A strikethrough text
     */
    public static class TextEntityTypeStrikethrough extends TextEntityType {

        @Override
        public int getConstructor() { return 961529082; }

    }


    /**
     * Text that must be formatted as if inside a code HTML tag
     */
    public static class TextEntityTypeCode extends TextEntityType {

        @Override
        public int getConstructor() { return -974534326; }

    }


    /**
     * Text that must be formatted as if inside a pre HTML tag
     */
    public static class TextEntityTypePre extends TextEntityType {

        @Override
        public int getConstructor() { return 1648958606; }

    }


    /**
     * Text that must be formatted as if inside pre, and code HTML tags
     *
     * @language - Programming language of the code
     *             As defined by the sender
     */
    public static class TextEntityTypePreCode extends TextEntityType {

        public String language;

        public TextEntityTypePreCode() {}

        public TextEntityTypePreCode(String language) {

            this.language = language;

        }

        @Override
        public int getConstructor() { return -945325397; }

    }


    /**
     * A text description shown instead of a raw URL
     *
     * @url - HTTP or tg:// URL to be opened when the link is clicked
     */
    public static class TextEntityTypeTextUrl extends TextEntityType {

        public String url;

        public TextEntityTypeTextUrl() {}

        public TextEntityTypeTextUrl(String url) {

            this.url = url;

        }

        @Override
        public int getConstructor() { return 445719651; }

    }


    /**
     * A text shows instead of a raw mention of the user (e.g., when the user has no username)
     *
     * @userId - Identifier of the mentioned user
     */
    public static class TextEntityTypeMentionName extends TextEntityType {

        public int userId;

        public TextEntityTypeMentionName() {}

        public TextEntityTypeMentionName(int userId) {

            this.userId = userId;

        }

        @Override
        public int getConstructor() { return -791517091; }

    }


    /**
     * A thumbnail to be sent along with a file
     * Must be in JPEG or WEBP format for stickers, and less than 200 KB in size
     *
     * @thumbnail - Thumbnail file to send
     *              Sending thumbnails by file_id is currently not supported
     * @width - Thumbnail width, usually shouldn't exceed 320
     *          Use 0 if unknown
     * @height - Thumbnail height, usually shouldn't exceed 320
     *           Use 0 if unknown
     */
    public static class InputThumbnail extends Object {

        public InputFile thumbnail;
        public int width;
        public int height;

        public InputThumbnail() {}

        public InputThumbnail(InputFile thumbnail, int width, int height) {

            this.thumbnail = thumbnail;
            this.width = width;
            this.height = height;

        }

        @Override
        public int getConstructor() { return 1582387236; }

    }


    /**
     * Contains information about the time when a scheduled message will be sent
     */
    public static abstract class MessageSchedulingState extends Object {}

    /**
     * The message will be sent at the specified date
     *
     * @sendDate - Date the message will be sent
     *             The date must be within 367 days in the future
     */
    public static class MessageSchedulingStateSendAtDate extends MessageSchedulingState {

        public int sendDate;

        public MessageSchedulingStateSendAtDate() {}

        public MessageSchedulingStateSendAtDate(int sendDate) {

            this.sendDate = sendDate;

        }

        @Override
        public int getConstructor() { return -1485570073; }

    }


    /**
     * The message will be sent when the peer will be online
     * Applicable to private chats only and when the exact online status of the peer is known
     */
    public static class MessageSchedulingStateSendWhenOnline extends MessageSchedulingState {

        @Override
        public int getConstructor() { return 2092947464; }

    }


    /**
     * Options to be used when a message is sent
     *
     * @disableNotification - Pass true to disable notification for the message
     * @fromBackground - Pass true if the message is sent from the background
     * @schedulingState - Message scheduling state
     *                    Messages sent to a secret chat, live location messages and self-destructing messages can't be scheduled
     */
    public static class MessageSendOptions extends Object {

        public boolean disableNotification;
        public boolean fromBackground;
        public MessageSchedulingState schedulingState;

        public MessageSendOptions() {}

        public MessageSendOptions(boolean disableNotification, boolean fromBackground, MessageSchedulingState schedulingState) {

            this.disableNotification = disableNotification;
            this.fromBackground = fromBackground;
            this.schedulingState = schedulingState;

        }

        @Override
        public int getConstructor() { return 914544314; }

    }


    /**
     * Options to be used when a message content is copied without a link to the original message
     *
     * @sendCopy - True, if content of the message needs to be copied without a link to the original message
     *             Always true if the message is forwarded to a secret chat
     * @replaceCaption - True, if media caption of the message copy needs to be replaced
     *                   Ignored if send_copy is false
     * @newCaption - New message caption
     *               Ignored if replace_caption is false
     */
    public static class MessageCopyOptions extends Object {

        public boolean sendCopy;
        public boolean replaceCaption;
        public FormattedText newCaption;

        public MessageCopyOptions() {}

        public MessageCopyOptions(boolean sendCopy, boolean replaceCaption, FormattedText newCaption) {

            this.sendCopy = sendCopy;
            this.replaceCaption = replaceCaption;
            this.newCaption = newCaption;

        }

        @Override
        public int getConstructor() { return 1208442937; }

    }


    /**
     * The content of a message to send
     */
    public static abstract class InputMessageContent extends Object {}

    /**
     * A text message
     *
     * @text - Formatted text to be sent
     *         1-GetOption("message_text_length_max") characters
     *         Only Bold, Italic, Underline, Strikethrough, Code, Pre, PreCode, TextUrl and MentionName entities are allowed to be specified manually
     * @disableWebPagePreview - True, if rich web page previews for URLs in the message text should be disabled
     * @clearDraft - True, if a chat message draft should be deleted
     */
    public static class InputMessageText extends InputMessageContent {

        public FormattedText text;
        public boolean disableWebPagePreview;
        public boolean clearDraft;

        public InputMessageText() {}

        public InputMessageText(FormattedText text, boolean disableWebPagePreview, boolean clearDraft) {

            this.text = text;
            this.disableWebPagePreview = disableWebPagePreview;
            this.clearDraft = clearDraft;

        }

        @Override
        public int getConstructor() { return 247050392; }

    }


    /**
     * An animation message (GIF-style).
     *
     * @animation - Animation file to be sent
     * @thumbnail - Animation thumbnail, if available
     * @addedStickerFileIds - File identifiers of the stickers added to the animation, if applicable
     * @duration - Duration of the animation, in seconds
     * @width - Width of the animation
     *          May be replaced by the server
     * @height - Height of the animation
     *           May be replaced by the server
     * @caption - Animation caption
     *            0-GetOption("message_caption_length_max") characters
     */
    public static class InputMessageAnimation extends InputMessageContent {

        public InputFile animation;
        public InputThumbnail thumbnail;
        public int[] addedStickerFileIds;
        public int duration;
        public int width;
        public int height;
        public FormattedText caption;

        public InputMessageAnimation() {}

        public InputMessageAnimation(InputFile animation, InputThumbnail thumbnail, int[] addedStickerFileIds, int duration, int width, int height, FormattedText caption) {

            this.animation = animation;
            this.thumbnail = thumbnail;
            this.addedStickerFileIds = addedStickerFileIds;
            this.duration = duration;
            this.width = width;
            this.height = height;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 1208433535; }

    }


    /**
     * An audio message
     *
     * @audio - Audio file to be sent
     * @albumCoverThumbnail - Thumbnail of the cover for the album, if available
     * @duration - Duration of the audio, in seconds
     *             May be replaced by the server
     * @title - Title of the audio
     *          May be replaced by the server
     * @performer - Performer of the audio
     * @caption - Audio caption
     *            0-GetOption("message_caption_length_max") characters
     */
    public static class InputMessageAudio extends InputMessageContent {

        public InputFile audio;
        public InputThumbnail albumCoverThumbnail;
        public int duration;
        public String title;
        public String performer;
        public FormattedText caption;

        public InputMessageAudio() {}

        public InputMessageAudio(InputFile audio, InputThumbnail albumCoverThumbnail, int duration, String title, String performer, FormattedText caption) {

            this.audio = audio;
            this.albumCoverThumbnail = albumCoverThumbnail;
            this.duration = duration;
            this.title = title;
            this.performer = performer;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return -626786126; }

    }


    /**
     * A document message (general file)
     *
     * @document - Document to be sent
     * @thumbnail - Document thumbnail, if available
     * @disableContentTypeDetection - If true, automatic file type detection will be disabled and the document will be always sent as file
     *                                Always true for files sent to secret chats
     * @caption - Document caption
     *            0-GetOption("message_caption_length_max") characters
     */
    public static class InputMessageDocument extends InputMessageContent {

        public InputFile document;
        public InputThumbnail thumbnail;
        public boolean disableContentTypeDetection;
        public FormattedText caption;

        public InputMessageDocument() {}

        public InputMessageDocument(InputFile document, InputThumbnail thumbnail, boolean disableContentTypeDetection, FormattedText caption) {

            this.document = document;
            this.thumbnail = thumbnail;
            this.disableContentTypeDetection = disableContentTypeDetection;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 1633383097; }

    }


    /**
     * A photo message
     *
     * @photo - Photo to send
     * @thumbnail - Photo thumbnail to be sent, this is sent to the other party in secret chats only
     * @addedStickerFileIds - File identifiers of the stickers added to the photo, if applicable
     * @width - Photo width
     * @height - Photo height
     * @caption - Photo caption
     *            0-GetOption("message_caption_length_max") characters
     * @ttl - Photo TTL (Time To Live), in seconds (0-60)
     *        A non-zero TTL can be specified only in private chats
     */
    public static class InputMessagePhoto extends InputMessageContent {

        public InputFile photo;
        public InputThumbnail thumbnail;
        public int[] addedStickerFileIds;
        public int width;
        public int height;
        public FormattedText caption;
        public int ttl;

        public InputMessagePhoto() {}

        public InputMessagePhoto(InputFile photo, InputThumbnail thumbnail, int[] addedStickerFileIds, int width, int height, FormattedText caption, int ttl) {

            this.photo = photo;
            this.thumbnail = thumbnail;
            this.addedStickerFileIds = addedStickerFileIds;
            this.width = width;
            this.height = height;
            this.caption = caption;
            this.ttl = ttl;

        }

        @Override
        public int getConstructor() { return 1648801584; }

    }


    /**
     * A sticker message
     *
     * @sticker - Sticker to be sent
     * @thumbnail - Sticker thumbnail, if available
     * @width - Sticker width
     * @height - Sticker height
     */
    public static class InputMessageSticker extends InputMessageContent {

        public InputFile sticker;
        public InputThumbnail thumbnail;
        public int width;
        public int height;

        public InputMessageSticker() {}

        public InputMessageSticker(InputFile sticker, InputThumbnail thumbnail, int width, int height) {

            this.sticker = sticker;
            this.thumbnail = thumbnail;
            this.width = width;
            this.height = height;

        }

        @Override
        public int getConstructor() { return 740776325; }

    }


    /**
     * A video message
     *
     * @video - Video to be sent
     * @thumbnail - Video thumbnail, if available
     * @addedStickerFileIds - File identifiers of the stickers added to the video, if applicable
     * @duration - Duration of the video, in seconds
     * @width - Video width
     * @height - Video height
     * @supportsStreaming - True, if the video should be tried to be streamed
     * @caption - Video caption
     *            0-GetOption("message_caption_length_max") characters
     * @ttl - Video TTL (Time To Live), in seconds (0-60)
     *        A non-zero TTL can be specified only in private chats
     */
    public static class InputMessageVideo extends InputMessageContent {

        public InputFile video;
        public InputThumbnail thumbnail;
        public int[] addedStickerFileIds;
        public int duration;
        public int width;
        public int height;
        public boolean supportsStreaming;
        public FormattedText caption;
        public int ttl;

        public InputMessageVideo() {}

        public InputMessageVideo(InputFile video, InputThumbnail thumbnail, int[] addedStickerFileIds, int duration, int width, int height, boolean supportsStreaming, FormattedText caption, int ttl) {

            this.video = video;
            this.thumbnail = thumbnail;
            this.addedStickerFileIds = addedStickerFileIds;
            this.duration = duration;
            this.width = width;
            this.height = height;
            this.supportsStreaming = supportsStreaming;
            this.caption = caption;
            this.ttl = ttl;

        }

        @Override
        public int getConstructor() { return -2108486755; }

    }


    /**
     * A video note message
     *
     * @videoNote - Video note to be sent
     * @thumbnail - Video thumbnail, if available
     * @duration - Duration of the video, in seconds
     * @length - Video width and height
     *           Must be positive and not greater than 640
     */
    public static class InputMessageVideoNote extends InputMessageContent {

        public InputFile videoNote;
        public InputThumbnail thumbnail;
        public int duration;
        public int length;

        public InputMessageVideoNote() {}

        public InputMessageVideoNote(InputFile videoNote, InputThumbnail thumbnail, int duration, int length) {

            this.videoNote = videoNote;
            this.thumbnail = thumbnail;
            this.duration = duration;
            this.length = length;

        }

        @Override
        public int getConstructor() { return 279108859; }

    }


    /**
     * A voice note message
     *
     * @voiceNote - Voice note to be sent
     * @duration - Duration of the voice note, in seconds
     * @waveform - Waveform representation of the voice note, in 5-bit format
     * @caption - Voice note caption
     *            0-GetOption("message_caption_length_max") characters
     */
    public static class InputMessageVoiceNote extends InputMessageContent {

        public InputFile voiceNote;
        public int duration;
        public byte[] waveform;
        public FormattedText caption;

        public InputMessageVoiceNote() {}

        public InputMessageVoiceNote(InputFile voiceNote, int duration, byte[] waveform, FormattedText caption) {

            this.voiceNote = voiceNote;
            this.duration = duration;
            this.waveform = waveform;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 2136519657; }

    }


    /**
     * A message with a location
     *
     * @location - Location to be sent
     * @livePeriod - Period for which the location can be updated, in seconds
     *               Should be between 60 and 86400 for a live location and 0 otherwise
     */
    public static class InputMessageLocation extends InputMessageContent {

        public Location location;
        public int livePeriod;

        public InputMessageLocation() {}

        public InputMessageLocation(Location location, int livePeriod) {

            this.location = location;
            this.livePeriod = livePeriod;

        }

        @Override
        public int getConstructor() { return -1624179655; }

    }


    /**
     * A message with information about a venue
     *
     * @venue - Venue to send
     */
    public static class InputMessageVenue extends InputMessageContent {

        public Venue venue;

        public InputMessageVenue() {}

        public InputMessageVenue(Venue venue) {

            this.venue = venue;

        }

        @Override
        public int getConstructor() { return 1447926269; }

    }


    /**
     * A message containing a user contact
     *
     * @contact - Contact to send
     */
    public static class InputMessageContact extends InputMessageContent {

        public Contact contact;

        public InputMessageContact() {}

        public InputMessageContact(Contact contact) {

            this.contact = contact;

        }

        @Override
        public int getConstructor() { return -982446849; }

    }


    /**
     * A dice message
     *
     * @emoji - Emoji on which the dice throw animation is based
     * @clearDraft - True, if a chat message draft should be deleted
     */
    public static class InputMessageDice extends InputMessageContent {

        public String emoji;
        public boolean clearDraft;

        public InputMessageDice() {}

        public InputMessageDice(String emoji, boolean clearDraft) {

            this.emoji = emoji;
            this.clearDraft = clearDraft;

        }

        @Override
        public int getConstructor() { return 841574313; }

    }


    /**
     * A message with a game
     * Not supported for channels or secret chats
     *
     * @botUserId - User identifier of the bot that owns the game
     * @gameShortName - Short name of the game
     */
    public static class InputMessageGame extends InputMessageContent {

        public int botUserId;
        public String gameShortName;

        public InputMessageGame() {}

        public InputMessageGame(int botUserId, String gameShortName) {

            this.botUserId = botUserId;
            this.gameShortName = gameShortName;

        }

        @Override
        public int getConstructor() { return -1728000914; }

    }


    /**
     * A message with an invoice
     * Can be used only by bots and only in private chats
     *
     * @invoice - Invoice
     * @title - Product title
     * @description - Product description
     * @photoUrl - Product photo URL
     * @photoSize - Product photo size
     * @photoWidth - Product photo width
     * @photoHeight - Product photo height
     * @payload - The invoice payload
     * @providerToken - Payment provider token
     * @providerData - JSON-encoded data about the invoice, which will be shared with the payment provider
     * @startParameter - Unique invoice bot start_parameter for the generation of this invoice
     */
    public static class InputMessageInvoice extends InputMessageContent {

        public Invoice invoice;
        public String title;
        public String description;
        @Nullable public String photoUrl;
        public int photoSize;
        public int photoWidth;
        public int photoHeight;
        public byte[] payload;
        public String providerToken;
        public String providerData;
        public String startParameter;

        public InputMessageInvoice() {}

        public InputMessageInvoice(Invoice invoice, String title, String description, @Nullable String photoUrl, int photoSize, int photoWidth, int photoHeight, byte[] payload, String providerToken, String providerData, String startParameter) {

            this.invoice = invoice;
            this.title = title;
            this.description = description;
            this.photoUrl = photoUrl;
            this.photoSize = photoSize;
            this.photoWidth = photoWidth;
            this.photoHeight = photoHeight;
            this.payload = payload;
            this.providerToken = providerToken;
            this.providerData = providerData;
            this.startParameter = startParameter;

        }

        @Override
        public int getConstructor() { return 1038812175; }

    }


    /**
     * A message with a poll
     * Polls can't be sent to secret chats
     * Polls can be sent only to a private chat with a bot
     *
     * @question - Poll question, 1-255 characters (up to 300 characters for bots)
     * @options - List of poll answer options, 2-10 strings 1-100 characters each
     * @isAnonymous - True, if the poll voters are anonymous
     *                Non-anonymous polls can't be sent or forwarded to channels
     * @type - Type of the poll
     * @openPeriod - Amount of time the poll will be active after creation, in seconds
     *               For bots only
     * @closeDate - Point in time (Unix timestamp) when the poll will be automatically closed
     *              For bots only
     * @isClosed - True, if the poll needs to be sent already closed
     *             For bots only
     */
    public static class InputMessagePoll extends InputMessageContent {

        public String question;
        public String[] options;
        public boolean isAnonymous;
        public PollType type;
        public int openPeriod;
        public int closeDate;
        public boolean isClosed;

        public InputMessagePoll() {}

        public InputMessagePoll(String question, String[] options, boolean isAnonymous, PollType type, int openPeriod, int closeDate, boolean isClosed) {

            this.question = question;
            this.options = options;
            this.isAnonymous = isAnonymous;
            this.type = type;
            this.openPeriod = openPeriod;
            this.closeDate = closeDate;
            this.isClosed = isClosed;

        }

        @Override
        public int getConstructor() { return 2054629900; }

    }


    /**
     * A forwarded message
     *
     * @fromChatId - Identifier for the chat this forwarded message came from
     * @messageId - Identifier of the message to forward
     * @inGameShare - True, if a game message should be shared within a launched game
     *                Applies only to game messages
     * @copyOptions - Options to be used to copy content of the message without a link to the original message
     */
    public static class InputMessageForwarded extends InputMessageContent {

        public long fromChatId;
        public long messageId;
        public boolean inGameShare;
        public MessageCopyOptions copyOptions;

        public InputMessageForwarded() {}

        public InputMessageForwarded(long fromChatId, long messageId, boolean inGameShare, MessageCopyOptions copyOptions) {

            this.fromChatId = fromChatId;
            this.messageId = messageId;
            this.inGameShare = inGameShare;
            this.copyOptions = copyOptions;

        }

        @Override
        public int getConstructor() { return 1696232440; }

    }


    /**
     * Represents a filter for message search results
     */
    public static abstract class SearchMessagesFilter extends Object {}

    /**
     * Returns all found messages, no filter is applied
     */
    public static class SearchMessagesFilterEmpty extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return -869395657; }

    }


    /**
     * Returns only animation messages
     */
    public static class SearchMessagesFilterAnimation extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return -155713339; }

    }


    /**
     * Returns only audio messages
     */
    public static class SearchMessagesFilterAudio extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 867505275; }

    }


    /**
     * Returns only document messages
     */
    public static class SearchMessagesFilterDocument extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 1526331215; }

    }


    /**
     * Returns only photo messages
     */
    public static class SearchMessagesFilterPhoto extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 925932293; }

    }


    /**
     * Returns only video messages
     */
    public static class SearchMessagesFilterVideo extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 115538222; }

    }


    /**
     * Returns only voice note messages
     */
    public static class SearchMessagesFilterVoiceNote extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 1841439357; }

    }


    /**
     * Returns only photo and video messages
     */
    public static class SearchMessagesFilterPhotoAndVideo extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 1352130963; }

    }


    /**
     * Returns only messages containing URLs
     */
    public static class SearchMessagesFilterUrl extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return -1828724341; }

    }


    /**
     * Returns only messages containing chat photos
     */
    public static class SearchMessagesFilterChatPhoto extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return -1247751329; }

    }


    /**
     * Returns only call messages
     */
    public static class SearchMessagesFilterCall extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 1305231012; }

    }


    /**
     * Returns only incoming call messages with missed/declined discard reasons
     */
    public static class SearchMessagesFilterMissedCall extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 970663098; }

    }


    /**
     * Returns only video note messages
     */
    public static class SearchMessagesFilterVideoNote extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 564323321; }

    }


    /**
     * Returns only voice and video note messages
     */
    public static class SearchMessagesFilterVoiceAndVideoNote extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 664174819; }

    }


    /**
     * Returns only messages with mentions of the current user, or messages that are replies to their messages
     */
    public static class SearchMessagesFilterMention extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return 2001258652; }

    }


    /**
     * Returns only messages with unread mentions of the current user, or messages that are replies to their messages
     * When using this filter the results can't be additionally filtered by a query, a message thread or by the sending user
     */
    public static class SearchMessagesFilterUnreadMention extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return -95769149; }

    }


    /**
     * Returns only failed to send messages
     * This filter can be used only if the message database is used
     */
    public static class SearchMessagesFilterFailedToSend extends SearchMessagesFilter {

        @Override
        public int getConstructor() { return -596322564; }

    }


    /**
     * Describes the different types of activity in a chat
     */
    public static abstract class ChatAction extends Object {}

    /**
     * The user is typing a message
     */
    public static class ChatActionTyping extends ChatAction {

        @Override
        public int getConstructor() { return 380122167; }

    }


    /**
     * The user is recording a video
     */
    public static class ChatActionRecordingVideo extends ChatAction {

        @Override
        public int getConstructor() { return 216553362; }

    }


    /**
     * The user is uploading a video
     *
     * @progress - Upload progress, as a percentage
     */
    public static class ChatActionUploadingVideo extends ChatAction {

        public int progress;

        public ChatActionUploadingVideo() {}

        public ChatActionUploadingVideo(int progress) {

            this.progress = progress;

        }

        @Override
        public int getConstructor() { return 1234185270; }

    }


    /**
     * The user is recording a voice note
     */
    public static class ChatActionRecordingVoiceNote extends ChatAction {

        @Override
        public int getConstructor() { return -808850058; }

    }


    /**
     * The user is uploading a voice note
     *
     * @progress - Upload progress, as a percentage
     */
    public static class ChatActionUploadingVoiceNote extends ChatAction {

        public int progress;

        public ChatActionUploadingVoiceNote() {}

        public ChatActionUploadingVoiceNote(int progress) {

            this.progress = progress;

        }

        @Override
        public int getConstructor() { return -613643666; }

    }


    /**
     * The user is uploading a photo
     *
     * @progress - Upload progress, as a percentage
     */
    public static class ChatActionUploadingPhoto extends ChatAction {

        public int progress;

        public ChatActionUploadingPhoto() {}

        public ChatActionUploadingPhoto(int progress) {

            this.progress = progress;

        }

        @Override
        public int getConstructor() { return 654240583; }

    }


    /**
     * The user is uploading a document
     *
     * @progress - Upload progress, as a percentage
     */
    public static class ChatActionUploadingDocument extends ChatAction {

        public int progress;

        public ChatActionUploadingDocument() {}

        public ChatActionUploadingDocument(int progress) {

            this.progress = progress;

        }

        @Override
        public int getConstructor() { return 167884362; }

    }


    /**
     * The user is picking a location or venue to send
     */
    public static class ChatActionChoosingLocation extends ChatAction {

        @Override
        public int getConstructor() { return -2017893596; }

    }


    /**
     * The user is picking a contact to send
     */
    public static class ChatActionChoosingContact extends ChatAction {

        @Override
        public int getConstructor() { return -1222507496; }

    }


    /**
     * The user has started to play a game
     */
    public static class ChatActionStartPlayingGame extends ChatAction {

        @Override
        public int getConstructor() { return -865884164; }

    }


    /**
     * The user is recording a video note
     */
    public static class ChatActionRecordingVideoNote extends ChatAction {

        @Override
        public int getConstructor() { return 16523393; }

    }


    /**
     * The user is uploading a video note
     *
     * @progress - Upload progress, as a percentage
     */
    public static class ChatActionUploadingVideoNote extends ChatAction {

        public int progress;

        public ChatActionUploadingVideoNote() {}

        public ChatActionUploadingVideoNote(int progress) {

            this.progress = progress;

        }

        @Override
        public int getConstructor() { return 1172364918; }

    }


    /**
     * The user has cancelled the previous action
     */
    public static class ChatActionCancel extends ChatAction {

        @Override
        public int getConstructor() { return 1160523958; }

    }


    /**
     * Describes the last time the user was online
     */
    public static abstract class UserStatus extends Object {}

    /**
     * The user status was never changed
     */
    public static class UserStatusEmpty extends UserStatus {

        @Override
        public int getConstructor() { return 164646985; }

    }


    /**
     * The user is online
     *
     * @expires - Point in time (Unix timestamp) when the user's online status will expire
     */
    public static class UserStatusOnline extends UserStatus {

        public int expires;

        public UserStatusOnline() {}

        public UserStatusOnline(int expires) {

            this.expires = expires;

        }

        @Override
        public int getConstructor() { return -1529460876; }

    }


    /**
     * The user is offline
     *
     * @wasOnline - Point in time (Unix timestamp) when the user was last online
     */
    public static class UserStatusOffline extends UserStatus {

        public int wasOnline;

        public UserStatusOffline() {}

        public UserStatusOffline(int wasOnline) {

            this.wasOnline = wasOnline;

        }

        @Override
        public int getConstructor() { return -759984891; }

    }


    /**
     * The user was online recently
     */
    public static class UserStatusRecently extends UserStatus {

        @Override
        public int getConstructor() { return -496024847; }

    }


    /**
     * The user is offline, but was online last week
     */
    public static class UserStatusLastWeek extends UserStatus {

        @Override
        public int getConstructor() { return 129960444; }

    }


    /**
     * The user is offline, but was online last month
     */
    public static class UserStatusLastMonth extends UserStatus {

        @Override
        public int getConstructor() { return 2011940674; }

    }


    /**
     * Represents a list of stickers
     *
     * @stickers - List of stickers
     */
    public static class Stickers extends Object {

        public Sticker[] stickers;

        public Stickers() {}

        public Stickers(Sticker[] stickers) {

            this.stickers = stickers;

        }

        @Override
        public int getConstructor() { return 1974859260; }

    }


    /**
     * Represents a list of emoji
     *
     * @emojis - List of emojis
     */
    public static class Emojis extends Object {

        public String[] emojis;

        public Emojis() {}

        public Emojis(String[] emojis) {

            this.emojis = emojis;

        }

        @Override
        public int getConstructor() { return 950339552; }

    }


    /**
     * Represents a sticker set
     *
     * @id - Identifier of the sticker set
     * @title - Title of the sticker set
     * @name - Name of the sticker set
     * @thumbnail - Sticker set thumbnail in WEBP or TGS format with width and height 100
     *              The file can be downloaded only before the thumbnail is changed
     * @isInstalled - True, if the sticker set has been installed by the current user
     * @isArchived - True, if the sticker set has been archived
     *               A sticker set can't be installed and archived simultaneously
     * @isOfficial - True, if the sticker set is official
     * @isAnimated - True, is the stickers in the set are animated
     * @isMasks - True, if the stickers in the set are masks
     * @isViewed - True for already viewed trending sticker sets
     * @stickers - List of stickers in this set
     * @emojis - A list of emoji corresponding to the stickers in the same order
     *           The list is only for informational purposes, because a sticker is always sent with a fixed emoji from the corresponding Sticker object
     */
    public static class StickerSet extends Object {

        public long id;
        public String title;
        public String name;
        @Nullable public Thumbnail thumbnail;
        public boolean isInstalled;
        public boolean isArchived;
        public boolean isOfficial;
        public boolean isAnimated;
        public boolean isMasks;
        public boolean isViewed;
        public Sticker[] stickers;
        public Emojis[] emojis;

        public StickerSet() {}

        public StickerSet(long id, String title, String name, @Nullable Thumbnail thumbnail, boolean isInstalled, boolean isArchived, boolean isOfficial, boolean isAnimated, boolean isMasks, boolean isViewed, Sticker[] stickers, Emojis[] emojis) {

            this.id = id;
            this.title = title;
            this.name = name;
            this.thumbnail = thumbnail;
            this.isInstalled = isInstalled;
            this.isArchived = isArchived;
            this.isOfficial = isOfficial;
            this.isAnimated = isAnimated;
            this.isMasks = isMasks;
            this.isViewed = isViewed;
            this.stickers = stickers;
            this.emojis = emojis;

        }

        @Override
        public int getConstructor() { return 853438190; }

    }


    /**
     * Represents short information about a sticker set
     *
     * @id - Identifier of the sticker set
     * @title - Title of the sticker set
     * @name - Name of the sticker set
     * @thumbnail - Sticker set thumbnail in WEBP or TGS format with width and height 100
     * @isInstalled - True, if the sticker set has been installed by current user
     * @isArchived - True, if the sticker set has been archived
     *               A sticker set can't be installed and archived simultaneously
     * @isOfficial - True, if the sticker set is official
     * @isAnimated - True, is the stickers in the set are animated
     * @isMasks - True, if the stickers in the set are masks
     * @isViewed - True for already viewed trending sticker sets
     * @size - Total number of stickers in the set
     * @covers - Contains up to the first 5 stickers from the set, depending on the context
     *           If the application needs more stickers the full set should be requested
     */
    public static class StickerSetInfo extends Object {

        public long id;
        public String title;
        public String name;
        @Nullable public Thumbnail thumbnail;
        public boolean isInstalled;
        public boolean isArchived;
        public boolean isOfficial;
        public boolean isAnimated;
        public boolean isMasks;
        public boolean isViewed;
        public int size;
        public Sticker[] covers;

        public StickerSetInfo() {}

        public StickerSetInfo(long id, String title, String name, @Nullable Thumbnail thumbnail, boolean isInstalled, boolean isArchived, boolean isOfficial, boolean isAnimated, boolean isMasks, boolean isViewed, int size, Sticker[] covers) {

            this.id = id;
            this.title = title;
            this.name = name;
            this.thumbnail = thumbnail;
            this.isInstalled = isInstalled;
            this.isArchived = isArchived;
            this.isOfficial = isOfficial;
            this.isAnimated = isAnimated;
            this.isMasks = isMasks;
            this.isViewed = isViewed;
            this.size = size;
            this.covers = covers;

        }

        @Override
        public int getConstructor() { return 703844215; }

    }


    /**
     * Represents a list of sticker sets
     *
     * @totalCount - Approximate total number of sticker sets found
     * @sets - List of sticker sets
     */
    public static class StickerSets extends Object {

        public int totalCount;
        public StickerSetInfo[] sets;

        public StickerSets() {}

        public StickerSets(int totalCount, StickerSetInfo[] sets) {

            this.totalCount = totalCount;
            this.sets = sets;

        }

        @Override
        public int getConstructor() { return -1883828812; }

    }


    /**
     * Describes the reason why a call was discarded
     */
    public static abstract class CallDiscardReason extends Object {}

    /**
     * The call wasn't discarded, or the reason is unknown
     */
    public static class CallDiscardReasonEmpty extends CallDiscardReason {

        @Override
        public int getConstructor() { return -1258917949; }

    }


    /**
     * The call was ended before the conversation started
     * It was cancelled by the caller or missed by the other party
     */
    public static class CallDiscardReasonMissed extends CallDiscardReason {

        @Override
        public int getConstructor() { return 1680358012; }

    }


    /**
     * The call was ended before the conversation started
     * It was declined by the other party
     */
    public static class CallDiscardReasonDeclined extends CallDiscardReason {

        @Override
        public int getConstructor() { return -1729926094; }

    }


    /**
     * The call was ended during the conversation because the users were disconnected
     */
    public static class CallDiscardReasonDisconnected extends CallDiscardReason {

        @Override
        public int getConstructor() { return -1342872670; }

    }


    /**
     * The call was ended because one of the parties hung up
     */
    public static class CallDiscardReasonHungUp extends CallDiscardReason {

        @Override
        public int getConstructor() { return 438216166; }

    }


    /**
     * Specifies the supported call protocols
     *
     * @udpP2p - True, if UDP peer-to-peer connections are supported
     * @udpReflector - True, if connection through UDP reflectors is supported
     * @minLayer - The minimum supported API layer
     * @maxLayer - The maximum supported API layer
     * @libraryVersions - List of supported libtgvoip versions
     */
    public static class CallProtocol extends Object {

        public boolean udpP2p;
        public boolean udpReflector;
        public int minLayer;
        public int maxLayer;
        public String[] libraryVersions;

        public CallProtocol() {}

        public CallProtocol(boolean udpP2p, boolean udpReflector, int minLayer, int maxLayer, String[] libraryVersions) {

            this.udpP2p = udpP2p;
            this.udpReflector = udpReflector;
            this.minLayer = minLayer;
            this.maxLayer = maxLayer;
            this.libraryVersions = libraryVersions;

        }

        @Override
        public int getConstructor() { return -1075562897; }

    }


    /**
     * Describes the type of a call server
     */
    public static abstract class CallServerType extends Object {}

    /**
     * A Telegram call reflector
     *
     * @peerTag - A peer tag to be used with the reflector
     */
    public static class CallServerTypeTelegramReflector extends CallServerType {

        public byte[] peerTag;

        public CallServerTypeTelegramReflector() {}

        public CallServerTypeTelegramReflector(byte[] peerTag) {

            this.peerTag = peerTag;

        }

        @Override
        public int getConstructor() { return -1507850700; }

    }


    /**
     * A WebRTC server
     *
     * @username - Username to be used for authentication
     * @password - Authentication password
     * @supportsTurn - True, if the server supports TURN
     * @supportsStun - True, if the server supports STUN
     */
    public static class CallServerTypeWebrtc extends CallServerType {

        public String username;
        public String password;
        public boolean supportsTurn;
        public boolean supportsStun;

        public CallServerTypeWebrtc() {}

        public CallServerTypeWebrtc(String username, String password, boolean supportsTurn, boolean supportsStun) {

            this.username = username;
            this.password = password;
            this.supportsTurn = supportsTurn;
            this.supportsStun = supportsStun;

        }

        @Override
        public int getConstructor() { return 1250622821; }

    }


    /**
     * Describes a server for relaying call data
     *
     * @id - Server identifier
     * @ipAddress - Server IPv4 address
     * @ipv6Address - Server IPv6 address
     * @port - Server port number
     * @type - Server type
     */
    public static class CallServer extends Object {

        public long id;
        public String ipAddress;
        public String ipv6Address;
        public int port;
        public CallServerType type;

        public CallServer() {}

        public CallServer(long id, String ipAddress, String ipv6Address, int port, CallServerType type) {

            this.id = id;
            this.ipAddress = ipAddress;
            this.ipv6Address = ipv6Address;
            this.port = port;
            this.type = type;

        }

        @Override
        public int getConstructor() { return 1865932695; }

    }


    /**
     * Contains the call identifier
     *
     * @id - Call identifier
     */
    public static class CallId extends Object {

        public int id;

        public CallId() {}

        public CallId(int id) {

            this.id = id;

        }

        @Override
        public int getConstructor() { return 65717769; }

    }


    /**
     * Describes the current call state
     */
    public static abstract class CallState extends Object {}

    /**
     * The call is pending, waiting to be accepted by a user
     *
     * @isCreated - True, if the call has already been created by the server
     * @isReceived - True, if the call has already been received by the other party
     */
    public static class CallStatePending extends CallState {

        public boolean isCreated;
        public boolean isReceived;

        public CallStatePending() {}

        public CallStatePending(boolean isCreated, boolean isReceived) {

            this.isCreated = isCreated;
            this.isReceived = isReceived;

        }

        @Override
        public int getConstructor() { return 1073048620; }

    }


    /**
     * The call has been answered and encryption keys are being exchanged
     */
    public static class CallStateExchangingKeys extends CallState {

        @Override
        public int getConstructor() { return -1848149403; }

    }


    /**
     * The call is ready to use
     *
     * @protocol - Call protocols supported by the peer
     * @servers - List of available call servers
     * @config - A JSON-encoded call config
     * @encryptionKey - Call encryption key
     * @emojis - Encryption key emojis fingerprint
     * @allowP2p - True, if peer-to-peer connection is allowed by users privacy settings
     */
    public static class CallStateReady extends CallState {

        public CallProtocol protocol;
        public CallServer[] servers;
        public String config;
        public byte[] encryptionKey;
        public String[] emojis;
        public boolean allowP2p;

        public CallStateReady() {}

        public CallStateReady(CallProtocol protocol, CallServer[] servers, String config, byte[] encryptionKey, String[] emojis, boolean allowP2p) {

            this.protocol = protocol;
            this.servers = servers;
            this.config = config;
            this.encryptionKey = encryptionKey;
            this.emojis = emojis;
            this.allowP2p = allowP2p;

        }

        @Override
        public int getConstructor() { return -2000107571; }

    }


    /**
     * The call is hanging up after discardCall has been called
     */
    public static class CallStateHangingUp extends CallState {

        @Override
        public int getConstructor() { return -2133790038; }

    }


    /**
     * The call has ended successfully
     *
     * @reason - The reason, why the call has ended
     * @needRating - True, if the call rating should be sent to the server
     * @needDebugInformation - True, if the call debug information should be sent to the server
     */
    public static class CallStateDiscarded extends CallState {

        public CallDiscardReason reason;
        public boolean needRating;
        public boolean needDebugInformation;

        public CallStateDiscarded() {}

        public CallStateDiscarded(CallDiscardReason reason, boolean needRating, boolean needDebugInformation) {

            this.reason = reason;
            this.needRating = needRating;
            this.needDebugInformation = needDebugInformation;

        }

        @Override
        public int getConstructor() { return -190853167; }

    }


    /**
     * The call has ended with an error
     *
     * @error - Error
     *          An error with the code 4005000 will be returned if an outgoing call is missed because of an expired timeout
     */
    public static class CallStateError extends CallState {

        public Error error;

        public CallStateError() {}

        public CallStateError(Error error) {

            this.error = error;

        }

        @Override
        public int getConstructor() { return -975215467; }

    }


    /**
     * Describes the exact type of a problem with a call
     */
    public static abstract class CallProblem extends Object {}

    /**
     * The user heard their own voice
     */
    public static class CallProblemEcho extends CallProblem {

        @Override
        public int getConstructor() { return 801116548; }

    }


    /**
     * The user heard background noise
     */
    public static class CallProblemNoise extends CallProblem {

        @Override
        public int getConstructor() { return 1053065359; }

    }


    /**
     * The other side kept disappearing
     */
    public static class CallProblemInterruptions extends CallProblem {

        @Override
        public int getConstructor() { return 1119493218; }

    }


    /**
     * The speech was distorted
     */
    public static class CallProblemDistortedSpeech extends CallProblem {

        @Override
        public int getConstructor() { return 379960581; }

    }


    /**
     * The user couldn't hear the other side
     */
    public static class CallProblemSilentLocal extends CallProblem {

        @Override
        public int getConstructor() { return 253652790; }

    }


    /**
     * The other side couldn't hear the user
     */
    public static class CallProblemSilentRemote extends CallProblem {

        @Override
        public int getConstructor() { return 573634714; }

    }


    /**
     * The call ended unexpectedly
     */
    public static class CallProblemDropped extends CallProblem {

        @Override
        public int getConstructor() { return -1207311487; }

    }


    /**
     * The video was distorted
     */
    public static class CallProblemDistortedVideo extends CallProblem {

        @Override
        public int getConstructor() { return 385245706; }

    }


    /**
     * The video was pixelated
     */
    public static class CallProblemPixelatedVideo extends CallProblem {

        @Override
        public int getConstructor() { return 2115315411; }

    }


    /**
     * Describes a call
     *
     * @id - Call identifier, not persistent
     * @userId - Peer user identifier
     * @isOutgoing - True, if the call is outgoing
     * @isVideo - True, if the call is a video call
     * @state - Call state
     */
    public static class Call extends Object {

        public int id;
        public int userId;
        public boolean isOutgoing;
        public boolean isVideo;
        public CallState state;

        public Call() {}

        public Call(int id, int userId, boolean isOutgoing, boolean isVideo, CallState state) {

            this.id = id;
            this.userId = userId;
            this.isOutgoing = isOutgoing;
            this.isVideo = isVideo;
            this.state = state;

        }

        @Override
        public int getConstructor() { return 1504070790; }

    }


    /**
     * Contains settings for the authentication of the user's phone number
     *
     * @allowFlashCall - Pass true if the authentication code may be sent via flash call to the specified phone number
     * @isCurrentPhoneNumber - Pass true if the authenticated phone number is used on the current device
     * @allowSmsRetrieverApi - For official applications only
     *                         True, if the application can use Android SMS Retriever API (requires Google Play Services >= 10.2) to automatically receive the authentication code from the SMS
     *                         See https://developers.google.com/identity/sms-retriever/ for more details
     */
    public static class PhoneNumberAuthenticationSettings extends Object {

        public boolean allowFlashCall;
        public boolean isCurrentPhoneNumber;
        public boolean allowSmsRetrieverApi;

        public PhoneNumberAuthenticationSettings() {}

        public PhoneNumberAuthenticationSettings(boolean allowFlashCall, boolean isCurrentPhoneNumber, boolean allowSmsRetrieverApi) {

            this.allowFlashCall = allowFlashCall;
            this.isCurrentPhoneNumber = isCurrentPhoneNumber;
            this.allowSmsRetrieverApi = allowSmsRetrieverApi;

        }

        @Override
        public int getConstructor() { return -859198743; }

    }


    /**
     * Represents a list of animations
     *
     * @animations - List of animations
     */
    public static class Animations extends Object {

        public Animation[] animations;

        public Animations() {}

        public Animations(Animation[] animations) {

            this.animations = animations;

        }

        @Override
        public int getConstructor() { return 344216945; }

    }


    /**
     * Represents the result of an ImportContacts request
     *
     * @userIds - User identifiers of the imported contacts in the same order as they were specified in the request
     *            0 if the contact is not yet a registered user
     * @importerCount - The number of users that imported the corresponding contact
     *                  0 for already registered users or if unavailable
     */
    public static class ImportedContacts extends Object {

        public int[] userIds;
        public int[] importerCount;

        public ImportedContacts() {}

        public ImportedContacts(int[] userIds, int[] importerCount) {

            this.userIds = userIds;
            this.importerCount = importerCount;

        }

        @Override
        public int getConstructor() { return -741685354; }

    }


    /**
     * Contains an HTTP URL
     *
     * @url - The URL
     */
    public static class HttpUrl extends Object {

        public String url;

        public HttpUrl() {}

        public HttpUrl(String url) {

            this.url = url;

        }

        @Override
        public int getConstructor() { return -2018019930; }

    }


    /**
     * Represents a single result of an inline query
     * For bots only
     */
    public static abstract class InputInlineQueryResult extends Object {}

    /**
     * Represents a link to an animated GIF or an animated (i.e
     * Without sound) H.264/MPEG-4 AVC video
     *
     * @id - Unique identifier of the query result
     * @title - Title of the query result
     * @thumbnailUrl - URL of the result thumbnail (JPEG, GIF, or MPEG4), if it exists
     * @thumbnailMimeType - MIME type of the video thumbnail
     *                      If non-empty, must be one of "image/jpeg", "image/gif" and "video/mp4"
     * @videoUrl - The URL of the video file (file size must not exceed 1MB)
     * @videoMimeType - MIME type of the video file
     *                  Must be one of "image/gif" and "video/mp4"
     * @videoDuration - Duration of the video, in seconds
     * @videoWidth - Width of the video
     * @videoHeight - Height of the video
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageAnimation, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultAnimation extends InputInlineQueryResult {

        public String id;
        public String title;
        public String thumbnailUrl;
        public String thumbnailMimeType;
        public String videoUrl;
        public String videoMimeType;
        public int videoDuration;
        public int videoWidth;
        public int videoHeight;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultAnimation() {}

        public InputInlineQueryResultAnimation(String id, String title, String thumbnailUrl, String thumbnailMimeType, String videoUrl, String videoMimeType, int videoDuration, int videoWidth, int videoHeight, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.title = title;
            this.thumbnailUrl = thumbnailUrl;
            this.thumbnailMimeType = thumbnailMimeType;
            this.videoUrl = videoUrl;
            this.videoMimeType = videoMimeType;
            this.videoDuration = videoDuration;
            this.videoWidth = videoWidth;
            this.videoHeight = videoHeight;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return -1489808874; }

    }


    /**
     * Represents a link to an article or web page
     *
     * @id - Unique identifier of the query result
     * @url - URL of the result, if it exists
     * @hideUrl - True, if the URL must be not shown
     * @title - Title of the result
     * @description - A short description of the result
     * @thumbnailUrl - URL of the result thumbnail, if it exists
     * @thumbnailWidth - Thumbnail width, if known
     * @thumbnailHeight - Thumbnail height, if known
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultArticle extends InputInlineQueryResult {

        public String id;
        public String url;
        public boolean hideUrl;
        public String title;
        public String description;
        public String thumbnailUrl;
        public int thumbnailWidth;
        public int thumbnailHeight;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultArticle() {}

        public InputInlineQueryResultArticle(String id, String url, boolean hideUrl, String title, String description, String thumbnailUrl, int thumbnailWidth, int thumbnailHeight, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.url = url;
            this.hideUrl = hideUrl;
            this.title = title;
            this.description = description;
            this.thumbnailUrl = thumbnailUrl;
            this.thumbnailWidth = thumbnailWidth;
            this.thumbnailHeight = thumbnailHeight;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 1973670156; }

    }


    /**
     * Represents a link to an MP3 audio file
     *
     * @id - Unique identifier of the query result
     * @title - Title of the audio file
     * @performer - Performer of the audio file
     * @audioUrl - The URL of the audio file
     * @audioDuration - Audio file duration, in seconds
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageAudio, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultAudio extends InputInlineQueryResult {

        public String id;
        public String title;
        public String performer;
        public String audioUrl;
        public int audioDuration;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultAudio() {}

        public InputInlineQueryResultAudio(String id, String title, String performer, String audioUrl, int audioDuration, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.title = title;
            this.performer = performer;
            this.audioUrl = audioUrl;
            this.audioDuration = audioDuration;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 1260139988; }

    }


    /**
     * Represents a user contact
     *
     * @id - Unique identifier of the query result
     * @contact - User contact
     * @thumbnailUrl - URL of the result thumbnail, if it exists
     * @thumbnailWidth - Thumbnail width, if known
     * @thumbnailHeight - Thumbnail height, if known
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultContact extends InputInlineQueryResult {

        public String id;
        public Contact contact;
        public String thumbnailUrl;
        public int thumbnailWidth;
        public int thumbnailHeight;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultContact() {}

        public InputInlineQueryResultContact(String id, Contact contact, String thumbnailUrl, int thumbnailWidth, int thumbnailHeight, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.contact = contact;
            this.thumbnailUrl = thumbnailUrl;
            this.thumbnailWidth = thumbnailWidth;
            this.thumbnailHeight = thumbnailHeight;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 1846064594; }

    }


    /**
     * Represents a link to a file
     *
     * @id - Unique identifier of the query result
     * @title - Title of the resulting file
     * @description - Short description of the result, if known
     * @documentUrl - URL of the file
     * @mimeType - MIME type of the file content
     *             Only "application/pdf" and "application/zip" are currently allowed
     * @thumbnailUrl - The URL of the file thumbnail, if it exists
     * @thumbnailWidth - Width of the thumbnail
     * @thumbnailHeight - Height of the thumbnail
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageDocument, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultDocument extends InputInlineQueryResult {

        public String id;
        public String title;
        public String description;
        public String documentUrl;
        public String mimeType;
        public String thumbnailUrl;
        public int thumbnailWidth;
        public int thumbnailHeight;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultDocument() {}

        public InputInlineQueryResultDocument(String id, String title, String description, String documentUrl, String mimeType, String thumbnailUrl, int thumbnailWidth, int thumbnailHeight, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.title = title;
            this.description = description;
            this.documentUrl = documentUrl;
            this.mimeType = mimeType;
            this.thumbnailUrl = thumbnailUrl;
            this.thumbnailWidth = thumbnailWidth;
            this.thumbnailHeight = thumbnailHeight;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 578801869; }

    }


    /**
     * Represents a game
     *
     * @id - Unique identifier of the query result
     * @gameShortName - Short name of the game
     * @replyMarkup - Message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     */
    public static class InputInlineQueryResultGame extends InputInlineQueryResult {

        public String id;
        public String gameShortName;
        public ReplyMarkup replyMarkup;

        public InputInlineQueryResultGame() {}

        public InputInlineQueryResultGame(String id, String gameShortName, ReplyMarkup replyMarkup) {

            this.id = id;
            this.gameShortName = gameShortName;
            this.replyMarkup = replyMarkup;

        }

        @Override
        public int getConstructor() { return 966074327; }

    }


    /**
     * Represents a point on the map
     *
     * @id - Unique identifier of the query result
     * @location - Location result
     * @livePeriod - Amount of time relative to the message sent time until the location can be updated, in seconds
     * @title - Title of the result
     * @thumbnailUrl - URL of the result thumbnail, if it exists
     * @thumbnailWidth - Thumbnail width, if known
     * @thumbnailHeight - Thumbnail height, if known
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultLocation extends InputInlineQueryResult {

        public String id;
        public Location location;
        public int livePeriod;
        public String title;
        public String thumbnailUrl;
        public int thumbnailWidth;
        public int thumbnailHeight;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultLocation() {}

        public InputInlineQueryResultLocation(String id, Location location, int livePeriod, String title, String thumbnailUrl, int thumbnailWidth, int thumbnailHeight, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.location = location;
            this.livePeriod = livePeriod;
            this.title = title;
            this.thumbnailUrl = thumbnailUrl;
            this.thumbnailWidth = thumbnailWidth;
            this.thumbnailHeight = thumbnailHeight;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return -1887650218; }

    }


    /**
     * Represents link to a JPEG image
     *
     * @id - Unique identifier of the query result
     * @title - Title of the result, if known
     * @description - A short description of the result, if known
     * @thumbnailUrl - URL of the photo thumbnail, if it exists
     * @photoUrl - The URL of the JPEG photo (photo size must not exceed 5MB)
     * @photoWidth - Width of the photo
     * @photoHeight - Height of the photo
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessagePhoto, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultPhoto extends InputInlineQueryResult {

        public String id;
        public String title;
        public String description;
        public String thumbnailUrl;
        public String photoUrl;
        public int photoWidth;
        public int photoHeight;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultPhoto() {}

        public InputInlineQueryResultPhoto(String id, String title, String description, String thumbnailUrl, String photoUrl, int photoWidth, int photoHeight, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.title = title;
            this.description = description;
            this.thumbnailUrl = thumbnailUrl;
            this.photoUrl = photoUrl;
            this.photoWidth = photoWidth;
            this.photoHeight = photoHeight;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return -1123338721; }

    }


    /**
     * Represents a link to a WEBP or TGS sticker
     *
     * @id - Unique identifier of the query result
     * @thumbnailUrl - URL of the sticker thumbnail, if it exists
     * @stickerUrl - The URL of the WEBP or TGS sticker (sticker file size must not exceed 5MB)
     * @stickerWidth - Width of the sticker
     * @stickerHeight - Height of the sticker
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, inputMessageSticker, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultSticker extends InputInlineQueryResult {

        public String id;
        public String thumbnailUrl;
        public String stickerUrl;
        public int stickerWidth;
        public int stickerHeight;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultSticker() {}

        public InputInlineQueryResultSticker(String id, String thumbnailUrl, String stickerUrl, int stickerWidth, int stickerHeight, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.thumbnailUrl = thumbnailUrl;
            this.stickerUrl = stickerUrl;
            this.stickerWidth = stickerWidth;
            this.stickerHeight = stickerHeight;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 274007129; }

    }


    /**
     * Represents information about a venue
     *
     * @id - Unique identifier of the query result
     * @venue - Venue result
     * @thumbnailUrl - URL of the result thumbnail, if it exists
     * @thumbnailWidth - Thumbnail width, if known
     * @thumbnailHeight - Thumbnail height, if known
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultVenue extends InputInlineQueryResult {

        public String id;
        public Venue venue;
        public String thumbnailUrl;
        public int thumbnailWidth;
        public int thumbnailHeight;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultVenue() {}

        public InputInlineQueryResultVenue(String id, Venue venue, String thumbnailUrl, int thumbnailWidth, int thumbnailHeight, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.venue = venue;
            this.thumbnailUrl = thumbnailUrl;
            this.thumbnailWidth = thumbnailWidth;
            this.thumbnailHeight = thumbnailHeight;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 541704509; }

    }


    /**
     * Represents a link to a page containing an embedded video player or a video file
     *
     * @id - Unique identifier of the query result
     * @title - Title of the result
     * @description - A short description of the result, if known
     * @thumbnailUrl - The URL of the video thumbnail (JPEG), if it exists
     * @videoUrl - URL of the embedded video player or video file
     * @mimeType - MIME type of the content of the video URL, only "text/html" or "video/mp4" are currently supported
     * @videoWidth - Width of the video
     * @videoHeight - Height of the video
     * @videoDuration - Video duration, in seconds
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageVideo, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultVideo extends InputInlineQueryResult {

        public String id;
        public String title;
        public String description;
        public String thumbnailUrl;
        public String videoUrl;
        public String mimeType;
        public int videoWidth;
        public int videoHeight;
        public int videoDuration;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultVideo() {}

        public InputInlineQueryResultVideo(String id, String title, String description, String thumbnailUrl, String videoUrl, String mimeType, int videoWidth, int videoHeight, int videoDuration, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.title = title;
            this.description = description;
            this.thumbnailUrl = thumbnailUrl;
            this.videoUrl = videoUrl;
            this.mimeType = mimeType;
            this.videoWidth = videoWidth;
            this.videoHeight = videoHeight;
            this.videoDuration = videoDuration;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 1724073191; }

    }


    /**
     * Represents a link to an opus-encoded audio file within an OGG container, single channel audio
     *
     * @id - Unique identifier of the query result
     * @title - Title of the voice note
     * @voiceNoteUrl - The URL of the voice note file
     * @voiceNoteDuration - Duration of the voice note, in seconds
     * @replyMarkup - The message reply markup
     *                Must be of type replyMarkupInlineKeyboard or null
     * @inputMessageContent - The content of the message to be sent
     *                        Must be one of the following types: InputMessageText, InputMessageVoiceNote, InputMessageLocation, InputMessageVenue or InputMessageContact
     */
    public static class InputInlineQueryResultVoiceNote extends InputInlineQueryResult {

        public String id;
        public String title;
        public String voiceNoteUrl;
        public int voiceNoteDuration;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public InputInlineQueryResultVoiceNote() {}

        public InputInlineQueryResultVoiceNote(String id, String title, String voiceNoteUrl, int voiceNoteDuration, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.id = id;
            this.title = title;
            this.voiceNoteUrl = voiceNoteUrl;
            this.voiceNoteDuration = voiceNoteDuration;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return -1790072503; }

    }


    /**
     * Represents a single result of an inline query
     */
    public static abstract class InlineQueryResult extends Object {}

    /**
     * Represents a link to an article or web page
     *
     * @id - Unique identifier of the query result
     * @url - URL of the result, if it exists
     * @hideUrl - True, if the URL must be not shown
     * @title - Title of the result
     * @description - A short description of the result
     * @thumbnail - Result thumbnail in JPEG format
     */
    public static class InlineQueryResultArticle extends InlineQueryResult {

        public String id;
        public String url;
        public boolean hideUrl;
        public String title;
        public String description;
        @Nullable public Thumbnail thumbnail;

        public InlineQueryResultArticle() {}

        public InlineQueryResultArticle(String id, String url, boolean hideUrl, String title, String description, @Nullable Thumbnail thumbnail) {

            this.id = id;
            this.url = url;
            this.hideUrl = hideUrl;
            this.title = title;
            this.description = description;
            this.thumbnail = thumbnail;

        }

        @Override
        public int getConstructor() { return 206340825; }

    }


    /**
     * Represents a user contact
     *
     * @id - Unique identifier of the query result
     * @contact - A user contact
     * @thumbnail - Result thumbnail in JPEG format
     */
    public static class InlineQueryResultContact extends InlineQueryResult {

        public String id;
        public Contact contact;
        @Nullable public Thumbnail thumbnail;

        public InlineQueryResultContact() {}

        public InlineQueryResultContact(String id, Contact contact, @Nullable Thumbnail thumbnail) {

            this.id = id;
            this.contact = contact;
            this.thumbnail = thumbnail;

        }

        @Override
        public int getConstructor() { return -181960174; }

    }


    /**
     * Represents a point on the map
     *
     * @id - Unique identifier of the query result
     * @location - Location result
     * @title - Title of the result
     * @thumbnail - Result thumbnail in JPEG format
     */
    public static class InlineQueryResultLocation extends InlineQueryResult {

        public String id;
        public Location location;
        public String title;
        @Nullable public Thumbnail thumbnail;

        public InlineQueryResultLocation() {}

        public InlineQueryResultLocation(String id, Location location, String title, @Nullable Thumbnail thumbnail) {

            this.id = id;
            this.location = location;
            this.title = title;
            this.thumbnail = thumbnail;

        }

        @Override
        public int getConstructor() { return 466004752; }

    }


    /**
     * Represents information about a venue
     *
     * @id - Unique identifier of the query result
     * @venue - Venue result
     * @thumbnail - Result thumbnail in JPEG format
     */
    public static class InlineQueryResultVenue extends InlineQueryResult {

        public String id;
        public Venue venue;
        @Nullable public Thumbnail thumbnail;

        public InlineQueryResultVenue() {}

        public InlineQueryResultVenue(String id, Venue venue, @Nullable Thumbnail thumbnail) {

            this.id = id;
            this.venue = venue;
            this.thumbnail = thumbnail;

        }

        @Override
        public int getConstructor() { return 1281036382; }

    }


    /**
     * Represents information about a game
     *
     * @id - Unique identifier of the query result
     * @game - Game result
     */
    public static class InlineQueryResultGame extends InlineQueryResult {

        public String id;
        public Game game;

        public InlineQueryResultGame() {}

        public InlineQueryResultGame(String id, Game game) {

            this.id = id;
            this.game = game;

        }

        @Override
        public int getConstructor() { return 1706916987; }

    }


    /**
     * Represents an animation file
     *
     * @id - Unique identifier of the query result
     * @animation - Animation file
     * @title - Animation title
     */
    public static class InlineQueryResultAnimation extends InlineQueryResult {

        public String id;
        public Animation animation;
        public String title;

        public InlineQueryResultAnimation() {}

        public InlineQueryResultAnimation(String id, Animation animation, String title) {

            this.id = id;
            this.animation = animation;
            this.title = title;

        }

        @Override
        public int getConstructor() { return 2009984267; }

    }


    /**
     * Represents an audio file
     *
     * @id - Unique identifier of the query result
     * @audio - Audio file
     */
    public static class InlineQueryResultAudio extends InlineQueryResult {

        public String id;
        public Audio audio;

        public InlineQueryResultAudio() {}

        public InlineQueryResultAudio(String id, Audio audio) {

            this.id = id;
            this.audio = audio;

        }

        @Override
        public int getConstructor() { return 842650360; }

    }


    /**
     * Represents a document
     *
     * @id - Unique identifier of the query result
     * @document - Document
     * @title - Document title
     * @description - Document description
     */
    public static class InlineQueryResultDocument extends InlineQueryResult {

        public String id;
        public Document document;
        public String title;
        public String description;

        public InlineQueryResultDocument() {}

        public InlineQueryResultDocument(String id, Document document, String title, String description) {

            this.id = id;
            this.document = document;
            this.title = title;
            this.description = description;

        }

        @Override
        public int getConstructor() { return -1491268539; }

    }


    /**
     * Represents a photo
     *
     * @id - Unique identifier of the query result
     * @photo - Photo
     * @title - Title of the result, if known
     * @description - A short description of the result, if known
     */
    public static class InlineQueryResultPhoto extends InlineQueryResult {

        public String id;
        public Photo photo;
        public String title;
        public String description;

        public InlineQueryResultPhoto() {}

        public InlineQueryResultPhoto(String id, Photo photo, String title, String description) {

            this.id = id;
            this.photo = photo;
            this.title = title;
            this.description = description;

        }

        @Override
        public int getConstructor() { return 1848319440; }

    }


    /**
     * Represents a sticker
     *
     * @id - Unique identifier of the query result
     * @sticker - Sticker
     */
    public static class InlineQueryResultSticker extends InlineQueryResult {

        public String id;
        public Sticker sticker;

        public InlineQueryResultSticker() {}

        public InlineQueryResultSticker(String id, Sticker sticker) {

            this.id = id;
            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return -1848224245; }

    }


    /**
     * Represents a video
     *
     * @id - Unique identifier of the query result
     * @video - Video
     * @title - Title of the video
     * @description - Description of the video
     */
    public static class InlineQueryResultVideo extends InlineQueryResult {

        public String id;
        public Video video;
        public String title;
        public String description;

        public InlineQueryResultVideo() {}

        public InlineQueryResultVideo(String id, Video video, String title, String description) {

            this.id = id;
            this.video = video;
            this.title = title;
            this.description = description;

        }

        @Override
        public int getConstructor() { return -1373158683; }

    }


    /**
     * Represents a voice note
     *
     * @id - Unique identifier of the query result
     * @voiceNote - Voice note
     * @title - Title of the voice note
     */
    public static class InlineQueryResultVoiceNote extends InlineQueryResult {

        public String id;
        public VoiceNote voiceNote;
        public String title;

        public InlineQueryResultVoiceNote() {}

        public InlineQueryResultVoiceNote(String id, VoiceNote voiceNote, String title) {

            this.id = id;
            this.voiceNote = voiceNote;
            this.title = title;

        }

        @Override
        public int getConstructor() { return -1897393105; }

    }


    /**
     * Represents the results of the inline query
     * Use sendInlineQueryResultMessage to send the result of the query
     *
     * @inlineQueryId - Unique identifier of the inline query
     * @nextOffset - The offset for the next request
     *               If empty, there are no more results
     * @results - Results of the query
     * @switchPmText - If non-empty, this text should be shown on the button, which opens a private chat with the bot and sends the bot a start message with the switch_pm_parameter
     * @switchPmParameter - Parameter for the bot start message
     */
    public static class InlineQueryResults extends Object {

        public long inlineQueryId;
        public String nextOffset;
        public InlineQueryResult[] results;
        public String switchPmText;
        public String switchPmParameter;

        public InlineQueryResults() {}

        public InlineQueryResults(long inlineQueryId, String nextOffset, InlineQueryResult[] results, String switchPmText, String switchPmParameter) {

            this.inlineQueryId = inlineQueryId;
            this.nextOffset = nextOffset;
            this.results = results;
            this.switchPmText = switchPmText;
            this.switchPmParameter = switchPmParameter;

        }

        @Override
        public int getConstructor() { return 1000709656; }

    }


    /**
     * Represents a payload of a callback query
     */
    public static abstract class CallbackQueryPayload extends Object {}

    /**
     * The payload for a general callback button
     *
     * @data - Data that was attached to the callback button
     */
    public static class CallbackQueryPayloadData extends CallbackQueryPayload {

        public byte[] data;

        public CallbackQueryPayloadData() {}

        public CallbackQueryPayloadData(byte[] data) {

            this.data = data;

        }

        @Override
        public int getConstructor() { return -1977729946; }

    }


    /**
     * The payload for a callback button requiring password
     *
     * @password - The password for the current user
     * @data - Data that was attached to the callback button
     */
    public static class CallbackQueryPayloadDataWithPassword extends CallbackQueryPayload {

        public String password;
        public byte[] data;

        public CallbackQueryPayloadDataWithPassword() {}

        public CallbackQueryPayloadDataWithPassword(String password, byte[] data) {

            this.password = password;
            this.data = data;

        }

        @Override
        public int getConstructor() { return 1340266738; }

    }


    /**
     * The payload for a game callback button
     *
     * @gameShortName - A short name of the game that was attached to the callback button
     */
    public static class CallbackQueryPayloadGame extends CallbackQueryPayload {

        public String gameShortName;

        public CallbackQueryPayloadGame() {}

        public CallbackQueryPayloadGame(String gameShortName) {

            this.gameShortName = gameShortName;

        }

        @Override
        public int getConstructor() { return 1303571512; }

    }


    /**
     * Contains a bot's answer to a callback query
     *
     * @text - Text of the answer
     * @showAlert - True, if an alert should be shown to the user instead of a toast notification
     * @url - URL to be opened
     */
    public static class CallbackQueryAnswer extends Object {

        public String text;
        public boolean showAlert;
        public String url;

        public CallbackQueryAnswer() {}

        public CallbackQueryAnswer(String text, boolean showAlert, String url) {

            this.text = text;
            this.showAlert = showAlert;
            this.url = url;

        }

        @Override
        public int getConstructor() { return 360867933; }

    }


    /**
     * Contains the result of a custom request
     *
     * @result - A JSON-serialized result
     */
    public static class CustomRequestResult extends Object {

        public String result;

        public CustomRequestResult() {}

        public CustomRequestResult(String result) {

            this.result = result;

        }

        @Override
        public int getConstructor() { return -2009960452; }

    }


    /**
     * Contains one row of the game high score table
     *
     * @position - Position in the high score table
     * @userId - User identifier
     * @score - User score
     */
    public static class GameHighScore extends Object {

        public int position;
        public int userId;
        public int score;

        public GameHighScore() {}

        public GameHighScore(int position, int userId, int score) {

            this.position = position;
            this.userId = userId;
            this.score = score;

        }

        @Override
        public int getConstructor() { return -30778358; }

    }


    /**
     * Contains a list of game high scores
     *
     * @scores - A list of game high scores
     */
    public static class GameHighScores extends Object {

        public GameHighScore[] scores;

        public GameHighScores() {}

        public GameHighScores(GameHighScore[] scores) {

            this.scores = scores;

        }

        @Override
        public int getConstructor() { return -725770727; }

    }


    /**
     * Represents a chat event
     */
    public static abstract class ChatEventAction extends Object {}

    /**
     * A message was edited
     *
     * @oldMessage - The original message before the edit
     * @newMessage - The message after it was edited
     */
    public static class ChatEventMessageEdited extends ChatEventAction {

        public Message oldMessage;
        public Message newMessage;

        public ChatEventMessageEdited() {}

        public ChatEventMessageEdited(Message oldMessage, Message newMessage) {

            this.oldMessage = oldMessage;
            this.newMessage = newMessage;

        }

        @Override
        public int getConstructor() { return -430967304; }

    }


    /**
     * A message was deleted
     *
     * @message - Deleted message
     */
    public static class ChatEventMessageDeleted extends ChatEventAction {

        public Message message;

        public ChatEventMessageDeleted() {}

        public ChatEventMessageDeleted(Message message) {

            this.message = message;

        }

        @Override
        public int getConstructor() { return -892974601; }

    }


    /**
     * A poll in a message was stopped
     *
     * @message - The message with the poll
     */
    public static class ChatEventPollStopped extends ChatEventAction {

        public Message message;

        public ChatEventPollStopped() {}

        public ChatEventPollStopped(Message message) {

            this.message = message;

        }

        @Override
        public int getConstructor() { return 2009893861; }

    }


    /**
     * A message was pinned
     *
     * @message - Pinned message
     */
    public static class ChatEventMessagePinned extends ChatEventAction {

        public Message message;

        public ChatEventMessagePinned() {}

        public ChatEventMessagePinned(Message message) {

            this.message = message;

        }

        @Override
        public int getConstructor() { return 438742298; }

    }


    /**
     * A message was unpinned
     */
    public static class ChatEventMessageUnpinned extends ChatEventAction {

        @Override
        public int getConstructor() { return 2002594849; }

    }


    /**
     * A new member joined the chat
     */
    public static class ChatEventMemberJoined extends ChatEventAction {

        @Override
        public int getConstructor() { return -235468508; }

    }


    /**
     * A member left the chat
     */
    public static class ChatEventMemberLeft extends ChatEventAction {

        @Override
        public int getConstructor() { return -948420593; }

    }


    /**
     * A new chat member was invited
     *
     * @userId - New member user identifier
     * @status - New member status
     */
    public static class ChatEventMemberInvited extends ChatEventAction {

        public int userId;
        public ChatMemberStatus status;

        public ChatEventMemberInvited() {}

        public ChatEventMemberInvited(int userId, ChatMemberStatus status) {

            this.userId = userId;
            this.status = status;

        }

        @Override
        public int getConstructor() { return -2093688706; }

    }


    /**
     * A chat member has gained/lost administrator status, or the list of their administrator privileges has changed
     *
     * @userId - Chat member user identifier
     * @oldStatus - Previous status of the chat member
     * @newStatus - New status of the chat member
     */
    public static class ChatEventMemberPromoted extends ChatEventAction {

        public int userId;
        public ChatMemberStatus oldStatus;
        public ChatMemberStatus newStatus;

        public ChatEventMemberPromoted() {}

        public ChatEventMemberPromoted(int userId, ChatMemberStatus oldStatus, ChatMemberStatus newStatus) {

            this.userId = userId;
            this.oldStatus = oldStatus;
            this.newStatus = newStatus;

        }

        @Override
        public int getConstructor() { return 1887176186; }

    }


    /**
     * A chat member was restricted/unrestricted or banned/unbanned, or the list of their restrictions has changed
     *
     * @userId - Chat member user identifier
     * @oldStatus - Previous status of the chat member
     * @newStatus - New status of the chat member
     */
    public static class ChatEventMemberRestricted extends ChatEventAction {

        public int userId;
        public ChatMemberStatus oldStatus;
        public ChatMemberStatus newStatus;

        public ChatEventMemberRestricted() {}

        public ChatEventMemberRestricted(int userId, ChatMemberStatus oldStatus, ChatMemberStatus newStatus) {

            this.userId = userId;
            this.oldStatus = oldStatus;
            this.newStatus = newStatus;

        }

        @Override
        public int getConstructor() { return 584946294; }

    }


    /**
     * The chat title was changed
     *
     * @oldTitle - Previous chat title
     * @newTitle - New chat title
     */
    public static class ChatEventTitleChanged extends ChatEventAction {

        public String oldTitle;
        public String newTitle;

        public ChatEventTitleChanged() {}

        public ChatEventTitleChanged(String oldTitle, String newTitle) {

            this.oldTitle = oldTitle;
            this.newTitle = newTitle;

        }

        @Override
        public int getConstructor() { return 1134103250; }

    }


    /**
     * The chat permissions was changed
     *
     * @oldPermissions - Previous chat permissions
     * @newPermissions - New chat permissions
     */
    public static class ChatEventPermissionsChanged extends ChatEventAction {

        public ChatPermissions oldPermissions;
        public ChatPermissions newPermissions;

        public ChatEventPermissionsChanged() {}

        public ChatEventPermissionsChanged(ChatPermissions oldPermissions, ChatPermissions newPermissions) {

            this.oldPermissions = oldPermissions;
            this.newPermissions = newPermissions;

        }

        @Override
        public int getConstructor() { return -1311557720; }

    }


    /**
     * The chat description was changed
     *
     * @oldDescription - Previous chat description
     * @newDescription - New chat description
     */
    public static class ChatEventDescriptionChanged extends ChatEventAction {

        public String oldDescription;
        public String newDescription;

        public ChatEventDescriptionChanged() {}

        public ChatEventDescriptionChanged(String oldDescription, String newDescription) {

            this.oldDescription = oldDescription;
            this.newDescription = newDescription;

        }

        @Override
        public int getConstructor() { return 39112478; }

    }


    /**
     * The chat username was changed
     *
     * @oldUsername - Previous chat username
     * @newUsername - New chat username
     */
    public static class ChatEventUsernameChanged extends ChatEventAction {

        public String oldUsername;
        public String newUsername;

        public ChatEventUsernameChanged() {}

        public ChatEventUsernameChanged(String oldUsername, String newUsername) {

            this.oldUsername = oldUsername;
            this.newUsername = newUsername;

        }

        @Override
        public int getConstructor() { return 1728558443; }

    }


    /**
     * The chat photo was changed
     *
     * @oldPhoto - Previous chat photo value
     * @newPhoto - New chat photo value
     */
    public static class ChatEventPhotoChanged extends ChatEventAction {

        @Nullable public ChatPhoto oldPhoto;
        @Nullable public ChatPhoto newPhoto;

        public ChatEventPhotoChanged() {}

        public ChatEventPhotoChanged(@Nullable ChatPhoto oldPhoto, @Nullable ChatPhoto newPhoto) {

            this.oldPhoto = oldPhoto;
            this.newPhoto = newPhoto;

        }

        @Override
        public int getConstructor() { return -811572541; }

    }


    /**
     * The can_invite_users permission of a supergroup chat was toggled
     *
     * @canInviteUsers - New value of can_invite_users permission
     */
    public static class ChatEventInvitesToggled extends ChatEventAction {

        public boolean canInviteUsers;

        public ChatEventInvitesToggled() {}

        public ChatEventInvitesToggled(boolean canInviteUsers) {

            this.canInviteUsers = canInviteUsers;

        }

        @Override
        public int getConstructor() { return -62548373; }

    }


    /**
     * The linked chat of a supergroup was changed
     *
     * @oldLinkedChatId - Previous supergroup linked chat identifier
     * @newLinkedChatId - New supergroup linked chat identifier
     */
    public static class ChatEventLinkedChatChanged extends ChatEventAction {

        public long oldLinkedChatId;
        public long newLinkedChatId;

        public ChatEventLinkedChatChanged() {}

        public ChatEventLinkedChatChanged(long oldLinkedChatId, long newLinkedChatId) {

            this.oldLinkedChatId = oldLinkedChatId;
            this.newLinkedChatId = newLinkedChatId;

        }

        @Override
        public int getConstructor() { return 1797419439; }

    }


    /**
     * The slow_mode_delay setting of a supergroup was changed
     *
     * @oldSlowModeDelay - Previous value of slow_mode_delay
     * @newSlowModeDelay - New value of slow_mode_delay
     */
    public static class ChatEventSlowModeDelayChanged extends ChatEventAction {

        public int oldSlowModeDelay;
        public int newSlowModeDelay;

        public ChatEventSlowModeDelayChanged() {}

        public ChatEventSlowModeDelayChanged(int oldSlowModeDelay, int newSlowModeDelay) {

            this.oldSlowModeDelay = oldSlowModeDelay;
            this.newSlowModeDelay = newSlowModeDelay;

        }

        @Override
        public int getConstructor() { return -1653195765; }

    }


    /**
     * The sign_messages setting of a channel was toggled
     *
     * @signMessages - New value of sign_messages
     */
    public static class ChatEventSignMessagesToggled extends ChatEventAction {

        public boolean signMessages;

        public ChatEventSignMessagesToggled() {}

        public ChatEventSignMessagesToggled(boolean signMessages) {

            this.signMessages = signMessages;

        }

        @Override
        public int getConstructor() { return -1313265634; }

    }


    /**
     * The supergroup sticker set was changed
     *
     * @oldStickerSetId - Previous identifier of the chat sticker set
     *                    0 if none
     * @newStickerSetId - New identifier of the chat sticker set
     *                    0 if none
     */
    public static class ChatEventStickerSetChanged extends ChatEventAction {

        public long oldStickerSetId;
        public long newStickerSetId;

        public ChatEventStickerSetChanged() {}

        public ChatEventStickerSetChanged(long oldStickerSetId, long newStickerSetId) {

            this.oldStickerSetId = oldStickerSetId;
            this.newStickerSetId = newStickerSetId;

        }

        @Override
        public int getConstructor() { return -1243130481; }

    }


    /**
     * The supergroup location was changed
     *
     * @oldLocation - Previous location
     * @newLocation - New location
     */
    public static class ChatEventLocationChanged extends ChatEventAction {

        @Nullable public ChatLocation oldLocation;
        @Nullable public ChatLocation newLocation;

        public ChatEventLocationChanged() {}

        public ChatEventLocationChanged(@Nullable ChatLocation oldLocation, @Nullable ChatLocation newLocation) {

            this.oldLocation = oldLocation;
            this.newLocation = newLocation;

        }

        @Override
        public int getConstructor() { return -405930674; }

    }


    /**
     * The is_all_history_available setting of a supergroup was toggled
     *
     * @isAllHistoryAvailable - New value of is_all_history_available
     */
    public static class ChatEventIsAllHistoryAvailableToggled extends ChatEventAction {

        public boolean isAllHistoryAvailable;

        public ChatEventIsAllHistoryAvailableToggled() {}

        public ChatEventIsAllHistoryAvailableToggled(boolean isAllHistoryAvailable) {

            this.isAllHistoryAvailable = isAllHistoryAvailable;

        }

        @Override
        public int getConstructor() { return -1599063019; }

    }


    /**
     * Represents a chat event
     *
     * @id - Chat event identifier
     * @date - Point in time (Unix timestamp) when the event happened
     * @userId - Identifier of the user who performed the action that triggered the event
     * @action - Action performed by the user
     */
    public static class ChatEvent extends Object {

        public long id;
        public int date;
        public int userId;
        public ChatEventAction action;

        public ChatEvent() {}

        public ChatEvent(long id, int date, int userId, ChatEventAction action) {

            this.id = id;
            this.date = date;
            this.userId = userId;
            this.action = action;

        }

        @Override
        public int getConstructor() { return -609912404; }

    }


    /**
     * Contains a list of chat events
     *
     * @events - List of events
     */
    public static class ChatEvents extends Object {

        public ChatEvent[] events;

        public ChatEvents() {}

        public ChatEvents(ChatEvent[] events) {

            this.events = events;

        }

        @Override
        public int getConstructor() { return -585329664; }

    }


    /**
     * Represents a set of filters used to obtain a chat event log
     *
     * @messageEdits - True, if message edits should be returned
     * @messageDeletions - True, if message deletions should be returned
     * @messagePins - True, if pin/unpin events should be returned
     * @memberJoins - True, if members joining events should be returned
     * @memberLeaves - True, if members leaving events should be returned
     * @memberInvites - True, if invited member events should be returned
     * @memberPromotions - True, if member promotion/demotion events should be returned
     * @memberRestrictions - True, if member restricted/unrestricted/banned/unbanned events should be returned
     * @infoChanges - True, if changes in chat information should be returned
     * @settingChanges - True, if changes in chat settings should be returned
     */
    public static class ChatEventLogFilters extends Object {

        public boolean messageEdits;
        public boolean messageDeletions;
        public boolean messagePins;
        public boolean memberJoins;
        public boolean memberLeaves;
        public boolean memberInvites;
        public boolean memberPromotions;
        public boolean memberRestrictions;
        public boolean infoChanges;
        public boolean settingChanges;

        public ChatEventLogFilters() {}

        public ChatEventLogFilters(boolean messageEdits, boolean messageDeletions, boolean messagePins, boolean memberJoins, boolean memberLeaves, boolean memberInvites, boolean memberPromotions, boolean memberRestrictions, boolean infoChanges, boolean settingChanges) {

            this.messageEdits = messageEdits;
            this.messageDeletions = messageDeletions;
            this.messagePins = messagePins;
            this.memberJoins = memberJoins;
            this.memberLeaves = memberLeaves;
            this.memberInvites = memberInvites;
            this.memberPromotions = memberPromotions;
            this.memberRestrictions = memberRestrictions;
            this.infoChanges = infoChanges;
            this.settingChanges = settingChanges;

        }

        @Override
        public int getConstructor() { return 941939684; }

    }


    /**
     * Represents the value of a string in a language pack
     */
    public static abstract class LanguagePackStringValue extends Object {}

    /**
     * An ordinary language pack string
     *
     * @value - String value
     */
    public static class LanguagePackStringValueOrdinary extends LanguagePackStringValue {

        public String value;

        public LanguagePackStringValueOrdinary() {}

        public LanguagePackStringValueOrdinary(String value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return -249256352; }

    }


    /**
     * A language pack string which has different forms based on the number of some object it mentions
     * See https://www.unicode.org/cldr/charts/latest/supplemental/language_plural_rules.html for more info
     *
     * @zeroValue - Value for zero objects
     * @oneValue - Value for one object
     * @twoValue - Value for two objects
     * @fewValue - Value for few objects
     * @manyValue - Value for many objects
     * @otherValue - Default value
     */
    public static class LanguagePackStringValuePluralized extends LanguagePackStringValue {

        public String zeroValue;
        public String oneValue;
        public String twoValue;
        public String fewValue;
        public String manyValue;
        public String otherValue;

        public LanguagePackStringValuePluralized() {}

        public LanguagePackStringValuePluralized(String zeroValue, String oneValue, String twoValue, String fewValue, String manyValue, String otherValue) {

            this.zeroValue = zeroValue;
            this.oneValue = oneValue;
            this.twoValue = twoValue;
            this.fewValue = fewValue;
            this.manyValue = manyValue;
            this.otherValue = otherValue;

        }

        @Override
        public int getConstructor() { return 1906840261; }

    }


    /**
     * A deleted language pack string, the value should be taken from the built-in english language pack
     */
    public static class LanguagePackStringValueDeleted extends LanguagePackStringValue {

        @Override
        public int getConstructor() { return 1834792698; }

    }


    /**
     * Represents one language pack string
     *
     * @key - String key
     * @value - String value
     */
    public static class LanguagePackString extends Object {

        public String key;
        public LanguagePackStringValue value;

        public LanguagePackString() {}

        public LanguagePackString(String key, LanguagePackStringValue value) {

            this.key = key;
            this.value = value;

        }

        @Override
        public int getConstructor() { return 1307632736; }

    }


    /**
     * Contains a list of language pack strings
     *
     * @strings - A list of language pack strings
     */
    public static class LanguagePackStrings extends Object {

        public LanguagePackString[] strings;

        public LanguagePackStrings() {}

        public LanguagePackStrings(LanguagePackString[] strings) {

            this.strings = strings;

        }

        @Override
        public int getConstructor() { return 1172082922; }

    }


    /**
     * Contains information about a language pack
     *
     * @id - Unique language pack identifier
     * @baseLanguagePackId - Identifier of a base language pack
     *                       If a string is missed in the language pack, then it should be fetched from base language pack
     *                       Unsupported in custom language packs
     * @name - Language name
     * @nativeName - Name of the language in that language
     * @pluralCode - A language code to be used to apply plural forms
     *               See https://www.unicode.org/cldr/charts/latest/supplemental/language_plural_rules.html for more info
     * @isOfficial - True, if the language pack is official
     * @isRtl - True, if the language pack strings are RTL
     * @isBeta - True, if the language pack is a beta language pack
     * @isInstalled - True, if the language pack is installed by the current user
     * @totalStringCount - Total number of non-deleted strings from the language pack
     * @translatedStringCount - Total number of translated strings from the language pack
     * @localStringCount - Total number of non-deleted strings from the language pack available locally
     * @translationUrl - Link to language translation interface
     *                   Empty for custom local language packs
     */
    public static class LanguagePackInfo extends Object {

        public String id;
        @Nullable public String baseLanguagePackId;
        public String name;
        public String nativeName;
        public String pluralCode;
        public boolean isOfficial;
        public boolean isRtl;
        public boolean isBeta;
        public boolean isInstalled;
        public int totalStringCount;
        public int translatedStringCount;
        public int localStringCount;
        public String translationUrl;

        public LanguagePackInfo() {}

        public LanguagePackInfo(String id, @Nullable String baseLanguagePackId, String name, String nativeName, String pluralCode, boolean isOfficial, boolean isRtl, boolean isBeta, boolean isInstalled, int totalStringCount, int translatedStringCount, int localStringCount, String translationUrl) {

            this.id = id;
            this.baseLanguagePackId = baseLanguagePackId;
            this.name = name;
            this.nativeName = nativeName;
            this.pluralCode = pluralCode;
            this.isOfficial = isOfficial;
            this.isRtl = isRtl;
            this.isBeta = isBeta;
            this.isInstalled = isInstalled;
            this.totalStringCount = totalStringCount;
            this.translatedStringCount = translatedStringCount;
            this.localStringCount = localStringCount;
            this.translationUrl = translationUrl;

        }

        @Override
        public int getConstructor() { return 542199642; }

    }


    /**
     * Contains information about the current localization target
     *
     * @languagePacks - List of available language packs for this application
     */
    public static class LocalizationTargetInfo extends Object {

        public LanguagePackInfo[] languagePacks;

        public LocalizationTargetInfo() {}

        public LocalizationTargetInfo(LanguagePackInfo[] languagePacks) {

            this.languagePacks = languagePacks;

        }

        @Override
        public int getConstructor() { return -2048670809; }

    }


    /**
     * Represents a data needed to subscribe for push notifications through registerDevice method
     * To use specific push notification service, the correct application platform must be specified and a valid server authentication data must be uploaded at https://my.telegram.org
     */
    public static abstract class DeviceToken extends Object {}

    /**
     * A token for Firebase Cloud Messaging
     *
     * @token - Device registration token
     *          May be empty to de-register a device
     * @encrypt - True, if push notifications should be additionally encrypted
     */
    public static class DeviceTokenFirebaseCloudMessaging extends DeviceToken {

        @Nullable public String token;
        public boolean encrypt;

        public DeviceTokenFirebaseCloudMessaging() {}

        public DeviceTokenFirebaseCloudMessaging(@Nullable String token, boolean encrypt) {

            this.token = token;
            this.encrypt = encrypt;

        }

        @Override
        public int getConstructor() { return -797881849; }

    }


    /**
     * A token for Apple Push Notification service
     *
     * @deviceToken - Device token
     *                May be empty to de-register a device
     * @isAppSandbox - True, if App Sandbox is enabled
     */
    public static class DeviceTokenApplePush extends DeviceToken {

        @Nullable public String deviceToken;
        public boolean isAppSandbox;

        public DeviceTokenApplePush() {}

        public DeviceTokenApplePush(@Nullable String deviceToken, boolean isAppSandbox) {

            this.deviceToken = deviceToken;
            this.isAppSandbox = isAppSandbox;

        }

        @Override
        public int getConstructor() { return 387541955; }

    }


    /**
     * A token for Apple Push Notification service VoIP notifications
     *
     * @deviceToken - Device token
     *                May be empty to de-register a device
     * @isAppSandbox - True, if App Sandbox is enabled
     * @encrypt - True, if push notifications should be additionally encrypted
     */
    public static class DeviceTokenApplePushVoIP extends DeviceToken {

        @Nullable public String deviceToken;
        public boolean isAppSandbox;
        public boolean encrypt;

        public DeviceTokenApplePushVoIP() {}

        public DeviceTokenApplePushVoIP(@Nullable String deviceToken, boolean isAppSandbox, boolean encrypt) {

            this.deviceToken = deviceToken;
            this.isAppSandbox = isAppSandbox;
            this.encrypt = encrypt;

        }

        @Override
        public int getConstructor() { return 804275689; }

    }


    /**
     * A token for Windows Push Notification Services
     *
     * @accessToken - The access token that will be used to send notifications
     *                May be empty to de-register a device
     */
    public static class DeviceTokenWindowsPush extends DeviceToken {

        @Nullable public String accessToken;

        public DeviceTokenWindowsPush() {}

        public DeviceTokenWindowsPush(@Nullable String accessToken) {

            this.accessToken = accessToken;

        }

        @Override
        public int getConstructor() { return -1410514289; }

    }


    /**
     * A token for Microsoft Push Notification Service
     *
     * @channelUri - Push notification channel URI
     *               May be empty to de-register a device
     */
    public static class DeviceTokenMicrosoftPush extends DeviceToken {

        @Nullable public String channelUri;

        public DeviceTokenMicrosoftPush() {}

        public DeviceTokenMicrosoftPush(@Nullable String channelUri) {

            this.channelUri = channelUri;

        }

        @Override
        public int getConstructor() { return 1224269900; }

    }


    /**
     * A token for Microsoft Push Notification Service VoIP channel
     *
     * @channelUri - Push notification channel URI
     *               May be empty to de-register a device
     */
    public static class DeviceTokenMicrosoftPushVoIP extends DeviceToken {

        @Nullable public String channelUri;

        public DeviceTokenMicrosoftPushVoIP() {}

        public DeviceTokenMicrosoftPushVoIP(@Nullable String channelUri) {

            this.channelUri = channelUri;

        }

        @Override
        public int getConstructor() { return -785603759; }

    }


    /**
     * A token for web Push API
     *
     * @endpoint - Absolute URL exposed by the push service where the application server can send push messages
     *             May be empty to de-register a device
     * @p256dhBase64url - Base64url-encoded P-256 elliptic curve Diffie-Hellman public key
     * @authBase64url - Base64url-encoded authentication secret
     */
    public static class DeviceTokenWebPush extends DeviceToken {

        @Nullable public String endpoint;
        public String p256dhBase64url;
        public String authBase64url;

        public DeviceTokenWebPush() {}

        public DeviceTokenWebPush(@Nullable String endpoint, String p256dhBase64url, String authBase64url) {

            this.endpoint = endpoint;
            this.p256dhBase64url = p256dhBase64url;
            this.authBase64url = authBase64url;

        }

        @Override
        public int getConstructor() { return -1694507273; }

    }


    /**
     * A token for Simple Push API for Firefox OS
     *
     * @endpoint - Absolute URL exposed by the push service where the application server can send push messages
     *             May be empty to de-register a device
     */
    public static class DeviceTokenSimplePush extends DeviceToken {

        @Nullable public String endpoint;

        public DeviceTokenSimplePush() {}

        public DeviceTokenSimplePush(@Nullable String endpoint) {

            this.endpoint = endpoint;

        }

        @Override
        public int getConstructor() { return 49584736; }

    }


    /**
     * A token for Ubuntu Push Client service
     *
     * @token - Token
     *          May be empty to de-register a device
     */
    public static class DeviceTokenUbuntuPush extends DeviceToken {

        @Nullable public String token;

        public DeviceTokenUbuntuPush() {}

        public DeviceTokenUbuntuPush(@Nullable String token) {

            this.token = token;

        }

        @Override
        public int getConstructor() { return 1782320422; }

    }


    /**
     * A token for BlackBerry Push Service
     *
     * @token - Token
     *          May be empty to de-register a device
     */
    public static class DeviceTokenBlackBerryPush extends DeviceToken {

        @Nullable public String token;

        public DeviceTokenBlackBerryPush() {}

        public DeviceTokenBlackBerryPush(@Nullable String token) {

            this.token = token;

        }

        @Override
        public int getConstructor() { return 1559167234; }

    }


    /**
     * A token for Tizen Push Service
     *
     * @regId - Push service registration identifier
     *          May be empty to de-register a device
     */
    public static class DeviceTokenTizenPush extends DeviceToken {

        @Nullable public String regId;

        public DeviceTokenTizenPush() {}

        public DeviceTokenTizenPush(@Nullable String regId) {

            this.regId = regId;

        }

        @Override
        public int getConstructor() { return -1359947213; }

    }


    /**
     * Contains a globally unique push receiver identifier, which can be used to identify which account has received a push notification
     *
     * @id - The globally unique identifier of push notification subscription
     */
    public static class PushReceiverId extends Object {

        public long id;

        public PushReceiverId() {}

        public PushReceiverId(long id) {

            this.id = id;

        }

        @Override
        public int getConstructor() { return 371056428; }

    }


    /**
     * Describes a fill of a background
     */
    public static abstract class BackgroundFill extends Object {}

    /**
     * Describes a solid fill of a background
     *
     * @color - A color of the background in the RGB24 format
     */
    public static class BackgroundFillSolid extends BackgroundFill {

        public int color;

        public BackgroundFillSolid() {}

        public BackgroundFillSolid(int color) {

            this.color = color;

        }

        @Override
        public int getConstructor() { return 1010678813; }

    }


    /**
     * Describes a gradient fill of a background
     *
     * @topColor - A top color of the background in the RGB24 format
     * @bottomColor - A bottom color of the background in the RGB24 format
     * @rotationAngle - Clockwise rotation angle of the gradient, in degrees
     *                  Should be always divisible by 45
     */
    public static class BackgroundFillGradient extends BackgroundFill {

        public int topColor;
        public int bottomColor;
        public int rotationAngle;

        public BackgroundFillGradient() {}

        public BackgroundFillGradient(int topColor, int bottomColor, int rotationAngle) {

            this.topColor = topColor;
            this.bottomColor = bottomColor;
            this.rotationAngle = rotationAngle;

        }

        @Override
        public int getConstructor() { return -1839206017; }

    }


    /**
     * Describes the type of a background
     */
    public static abstract class BackgroundType extends Object {}

    /**
     * A wallpaper in JPEG format
     *
     * @isBlurred - True, if the wallpaper must be downscaled to fit in 450x450 square and then box-blurred with radius 12
     * @isMoving - True, if the background needs to be slightly moved when device is tilted
     */
    public static class BackgroundTypeWallpaper extends BackgroundType {

        public boolean isBlurred;
        public boolean isMoving;

        public BackgroundTypeWallpaper() {}

        public BackgroundTypeWallpaper(boolean isBlurred, boolean isMoving) {

            this.isBlurred = isBlurred;
            this.isMoving = isMoving;

        }

        @Override
        public int getConstructor() { return 1972128891; }

    }


    /**
     * A PNG or TGV (gzipped subset of SVG with MIME type "application/x-tgwallpattern") pattern to be combined with the background fill chosen by the user
     *
     * @fill - Description of the background fill
     * @intensity - Intensity of the pattern when it is shown above the filled background, 0-100
     * @isMoving - True, if the background needs to be slightly moved when device is tilted
     */
    public static class BackgroundTypePattern extends BackgroundType {

        public BackgroundFill fill;
        public int intensity;
        public boolean isMoving;

        public BackgroundTypePattern() {}

        public BackgroundTypePattern(BackgroundFill fill, int intensity, boolean isMoving) {

            this.fill = fill;
            this.intensity = intensity;
            this.isMoving = isMoving;

        }

        @Override
        public int getConstructor() { return 649993914; }

    }


    /**
     * A filled background
     *
     * @fill - Description of the background fill
     */
    public static class BackgroundTypeFill extends BackgroundType {

        public BackgroundFill fill;

        public BackgroundTypeFill() {}

        public BackgroundTypeFill(BackgroundFill fill) {

            this.fill = fill;

        }

        @Override
        public int getConstructor() { return 993008684; }

    }


    /**
     * Describes a chat background
     *
     * @id - Unique background identifier
     * @isDefault - True, if this is one of default backgrounds
     * @isDark - True, if the background is dark and is recommended to be used with dark theme
     * @name - Unique background name
     * @document - Document with the background
     *             Null only for filled backgrounds
     * @type - Type of the background
     */
    public static class Background extends Object {

        public long id;
        public boolean isDefault;
        public boolean isDark;
        public String name;
        @Nullable public Document document;
        public BackgroundType type;

        public Background() {}

        public Background(long id, boolean isDefault, boolean isDark, String name, @Nullable Document document, BackgroundType type) {

            this.id = id;
            this.isDefault = isDefault;
            this.isDark = isDark;
            this.name = name;
            this.document = document;
            this.type = type;

        }

        @Override
        public int getConstructor() { return -429971172; }

    }


    /**
     * Contains a list of backgrounds
     *
     * @backgrounds - A list of backgrounds
     */
    public static class Backgrounds extends Object {

        public Background[] backgrounds;

        public Backgrounds() {}

        public Backgrounds(Background[] backgrounds) {

            this.backgrounds = backgrounds;

        }

        @Override
        public int getConstructor() { return 724728704; }

    }


    /**
     * Contains information about background to set
     */
    public static abstract class InputBackground extends Object {}

    /**
     * A background from a local file
     *
     * @background - Background file to use
     *               Only inputFileLocal and inputFileGenerated are supported
     *               The file must be in JPEG format for wallpapers and in PNG format for patterns
     */
    public static class InputBackgroundLocal extends InputBackground {

        public InputFile background;

        public InputBackgroundLocal() {}

        public InputBackgroundLocal(InputFile background) {

            this.background = background;

        }

        @Override
        public int getConstructor() { return -1747094364; }

    }


    /**
     * A background from the server
     *
     * @backgroundId - The background identifier
     */
    public static class InputBackgroundRemote extends InputBackground {

        public long backgroundId;

        public InputBackgroundRemote() {}

        public InputBackgroundRemote(long backgroundId) {

            this.backgroundId = backgroundId;

        }

        @Override
        public int getConstructor() { return -274976231; }

    }


    /**
     * Contains a list of hashtags
     *
     * @hashtags - A list of hashtags
     */
    public static class Hashtags extends Object {

        public String[] hashtags;

        public Hashtags() {}

        public Hashtags(String[] hashtags) {

            this.hashtags = hashtags;

        }

        @Override
        public int getConstructor() { return 676798885; }

    }


    /**
     * Represents result of checking whether the current session can be used to transfer a chat ownership to another user
     */
    public static abstract class CanTransferOwnershipResult extends Object {}

    /**
     * The session can be used
     */
    public static class CanTransferOwnershipResultOk extends CanTransferOwnershipResult {

        @Override
        public int getConstructor() { return -89881021; }

    }


    /**
     * The 2-step verification needs to be enabled first
     */
    public static class CanTransferOwnershipResultPasswordNeeded extends CanTransferOwnershipResult {

        @Override
        public int getConstructor() { return 1548372703; }

    }


    /**
     * The 2-step verification was enabled recently, user needs to wait
     *
     * @retryAfter - Time left before the session can be used to transfer ownership of a chat, in seconds
     */
    public static class CanTransferOwnershipResultPasswordTooFresh extends CanTransferOwnershipResult {

        public int retryAfter;

        public CanTransferOwnershipResultPasswordTooFresh() {}

        public CanTransferOwnershipResultPasswordTooFresh(int retryAfter) {

            this.retryAfter = retryAfter;

        }

        @Override
        public int getConstructor() { return 811440913; }

    }


    /**
     * The session was created recently, user needs to wait
     *
     * @retryAfter - Time left before the session can be used to transfer ownership of a chat, in seconds
     */
    public static class CanTransferOwnershipResultSessionTooFresh extends CanTransferOwnershipResult {

        public int retryAfter;

        public CanTransferOwnershipResultSessionTooFresh() {}

        public CanTransferOwnershipResultSessionTooFresh(int retryAfter) {

            this.retryAfter = retryAfter;

        }

        @Override
        public int getConstructor() { return 984664289; }

    }


    /**
     * Represents result of checking whether a username can be set for a chat
     */
    public static abstract class CheckChatUsernameResult extends Object {}

    /**
     * The username can be set
     */
    public static class CheckChatUsernameResultOk extends CheckChatUsernameResult {

        @Override
        public int getConstructor() { return -1498956964; }

    }


    /**
     * The username is invalid
     */
    public static class CheckChatUsernameResultUsernameInvalid extends CheckChatUsernameResult {

        @Override
        public int getConstructor() { return -636979370; }

    }


    /**
     * The username is occupied
     */
    public static class CheckChatUsernameResultUsernameOccupied extends CheckChatUsernameResult {

        @Override
        public int getConstructor() { return 1320892201; }

    }


    /**
     * The user has too much chats with username, one of them should be made private first
     */
    public static class CheckChatUsernameResultPublicChatsTooMuch extends CheckChatUsernameResult {

        @Override
        public int getConstructor() { return 858247741; }

    }


    /**
     * The user can't be a member of a public supergroup
     */
    public static class CheckChatUsernameResultPublicGroupsUnavailable extends CheckChatUsernameResult {

        @Override
        public int getConstructor() { return -51833641; }

    }


    /**
     * Contains content of a push message notification
     */
    public static abstract class PushMessageContent extends Object {}

    /**
     * A general message with hidden content
     *
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentHidden extends PushMessageContent {

        public boolean isPinned;

        public PushMessageContentHidden() {}

        public PushMessageContentHidden(boolean isPinned) {

            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return -316950436; }

    }


    /**
     * An animation message (GIF-style).
     *
     * @animation - Message content
     * @caption - Animation caption
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentAnimation extends PushMessageContent {

        @Nullable public Animation animation;
        public String caption;
        public boolean isPinned;

        public PushMessageContentAnimation() {}

        public PushMessageContentAnimation(@Nullable Animation animation, String caption, boolean isPinned) {

            this.animation = animation;
            this.caption = caption;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return 1034215396; }

    }


    /**
     * An audio message
     *
     * @audio - Message content
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentAudio extends PushMessageContent {

        @Nullable public Audio audio;
        public boolean isPinned;

        public PushMessageContentAudio() {}

        public PushMessageContentAudio(@Nullable Audio audio, boolean isPinned) {

            this.audio = audio;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return 381581426; }

    }


    /**
     * A message with a user contact
     *
     * @name - Contact's name
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentContact extends PushMessageContent {

        public String name;
        public boolean isPinned;

        public PushMessageContentContact() {}

        public PushMessageContentContact(String name, boolean isPinned) {

            this.name = name;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return -12219820; }

    }


    /**
     * A contact has registered with Telegram
     */
    public static class PushMessageContentContactRegistered extends PushMessageContent {

        @Override
        public int getConstructor() { return -303962720; }

    }


    /**
     * A document message (a general file)
     *
     * @document - Message content
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentDocument extends PushMessageContent {

        @Nullable public Document document;
        public boolean isPinned;

        public PushMessageContentDocument() {}

        public PushMessageContentDocument(@Nullable Document document, boolean isPinned) {

            this.document = document;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return -458379775; }

    }


    /**
     * A message with a game
     *
     * @title - Game title, empty for pinned game message
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentGame extends PushMessageContent {

        public String title;
        public boolean isPinned;

        public PushMessageContentGame() {}

        public PushMessageContentGame(String title, boolean isPinned) {

            this.title = title;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return -515131109; }

    }


    /**
     * A new high score was achieved in a game
     *
     * @title - Game title, empty for pinned message
     * @score - New score, 0 for pinned message
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentGameScore extends PushMessageContent {

        public String title;
        public int score;
        public boolean isPinned;

        public PushMessageContentGameScore() {}

        public PushMessageContentGameScore(String title, int score, boolean isPinned) {

            this.title = title;
            this.score = score;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return 901303688; }

    }


    /**
     * A message with an invoice from a bot
     *
     * @price - Product price
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentInvoice extends PushMessageContent {

        public String price;
        public boolean isPinned;

        public PushMessageContentInvoice() {}

        public PushMessageContentInvoice(String price, boolean isPinned) {

            this.price = price;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return -1731687492; }

    }


    /**
     * A message with a location
     *
     * @isLive - True, if the location is live
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentLocation extends PushMessageContent {

        public boolean isLive;
        public boolean isPinned;

        public PushMessageContentLocation() {}

        public PushMessageContentLocation(boolean isLive, boolean isPinned) {

            this.isLive = isLive;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return -1288005709; }

    }


    /**
     * A photo message
     *
     * @photo - Message content
     * @caption - Photo caption
     * @isSecret - True, if the photo is secret
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentPhoto extends PushMessageContent {

        @Nullable public Photo photo;
        public String caption;
        public boolean isSecret;
        public boolean isPinned;

        public PushMessageContentPhoto() {}

        public PushMessageContentPhoto(@Nullable Photo photo, String caption, boolean isSecret, boolean isPinned) {

            this.photo = photo;
            this.caption = caption;
            this.isSecret = isSecret;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return 140631122; }

    }


    /**
     * A message with a poll
     *
     * @question - Poll question
     * @isRegular - True, if the poll is regular and not in quiz mode
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentPoll extends PushMessageContent {

        public String question;
        public boolean isRegular;
        public boolean isPinned;

        public PushMessageContentPoll() {}

        public PushMessageContentPoll(String question, boolean isRegular, boolean isPinned) {

            this.question = question;
            this.isRegular = isRegular;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return -44403654; }

    }


    /**
     * A screenshot of a message in the chat has been taken
     */
    public static class PushMessageContentScreenshotTaken extends PushMessageContent {

        @Override
        public int getConstructor() { return 214245369; }

    }


    /**
     * A message with a sticker
     *
     * @sticker - Message content
     * @emoji - Emoji corresponding to the sticker
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentSticker extends PushMessageContent {

        @Nullable public Sticker sticker;
        @Nullable public String emoji;
        public boolean isPinned;

        public PushMessageContentSticker() {}

        public PushMessageContentSticker(@Nullable Sticker sticker, @Nullable String emoji, boolean isPinned) {

            this.sticker = sticker;
            this.emoji = emoji;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return 1553513939; }

    }


    /**
     * A text message
     *
     * @text - Message text
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentText extends PushMessageContent {

        public String text;
        public boolean isPinned;

        public PushMessageContentText() {}

        public PushMessageContentText(String text, boolean isPinned) {

            this.text = text;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return 274587305; }

    }


    /**
     * A video message
     *
     * @video - Message content
     * @caption - Video caption
     * @isSecret - True, if the video is secret
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentVideo extends PushMessageContent {

        @Nullable public Video video;
        public String caption;
        public boolean isSecret;
        public boolean isPinned;

        public PushMessageContentVideo() {}

        public PushMessageContentVideo(@Nullable Video video, String caption, boolean isSecret, boolean isPinned) {

            this.video = video;
            this.caption = caption;
            this.isSecret = isSecret;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return 310038831; }

    }


    /**
     * A video note message
     *
     * @videoNote - Message content
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentVideoNote extends PushMessageContent {

        @Nullable public VideoNote videoNote;
        public boolean isPinned;

        public PushMessageContentVideoNote() {}

        public PushMessageContentVideoNote(@Nullable VideoNote videoNote, boolean isPinned) {

            this.videoNote = videoNote;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return -1122764417; }

    }


    /**
     * A voice note message
     *
     * @voiceNote - Message content
     * @isPinned - True, if the message is a pinned message with the specified content
     */
    public static class PushMessageContentVoiceNote extends PushMessageContent {

        @Nullable public VoiceNote voiceNote;
        public boolean isPinned;

        public PushMessageContentVoiceNote() {}

        public PushMessageContentVoiceNote(@Nullable VoiceNote voiceNote, boolean isPinned) {

            this.voiceNote = voiceNote;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return 88910987; }

    }


    /**
     * A newly created basic group
     */
    public static class PushMessageContentBasicGroupChatCreate extends PushMessageContent {

        @Override
        public int getConstructor() { return -2114855172; }

    }


    /**
     * New chat members were invited to a group
     *
     * @memberName - Name of the added member
     * @isCurrentUser - True, if the current user was added to the group
     * @isReturned - True, if the user has returned to the group themself
     */
    public static class PushMessageContentChatAddMembers extends PushMessageContent {

        public String memberName;
        public boolean isCurrentUser;
        public boolean isReturned;

        public PushMessageContentChatAddMembers() {}

        public PushMessageContentChatAddMembers(String memberName, boolean isCurrentUser, boolean isReturned) {

            this.memberName = memberName;
            this.isCurrentUser = isCurrentUser;
            this.isReturned = isReturned;

        }

        @Override
        public int getConstructor() { return -1087145158; }

    }


    /**
     * A chat photo was edited
     */
    public static class PushMessageContentChatChangePhoto extends PushMessageContent {

        @Override
        public int getConstructor() { return -1114222051; }

    }


    /**
     * A chat title was edited
     *
     * @title - New chat title
     */
    public static class PushMessageContentChatChangeTitle extends PushMessageContent {

        public String title;

        public PushMessageContentChatChangeTitle() {}

        public PushMessageContentChatChangeTitle(String title) {

            this.title = title;

        }

        @Override
        public int getConstructor() { return -1964902749; }

    }


    /**
     * A chat member was deleted
     *
     * @memberName - Name of the deleted member
     * @isCurrentUser - True, if the current user was deleted from the group
     * @isLeft - True, if the user has left the group themself
     */
    public static class PushMessageContentChatDeleteMember extends PushMessageContent {

        public String memberName;
        public boolean isCurrentUser;
        public boolean isLeft;

        public PushMessageContentChatDeleteMember() {}

        public PushMessageContentChatDeleteMember(String memberName, boolean isCurrentUser, boolean isLeft) {

            this.memberName = memberName;
            this.isCurrentUser = isCurrentUser;
            this.isLeft = isLeft;

        }

        @Override
        public int getConstructor() { return 598714783; }

    }


    /**
     * A new member joined the chat by invite link
     */
    public static class PushMessageContentChatJoinByLink extends PushMessageContent {

        @Override
        public int getConstructor() { return 1553719113; }

    }


    /**
     * A forwarded messages
     *
     * @totalCount - Number of forwarded messages
     */
    public static class PushMessageContentMessageForwards extends PushMessageContent {

        public int totalCount;

        public PushMessageContentMessageForwards() {}

        public PushMessageContentMessageForwards(int totalCount) {

            this.totalCount = totalCount;

        }

        @Override
        public int getConstructor() { return -1913083876; }

    }


    /**
     * A media album
     *
     * @totalCount - Number of messages in the album
     * @hasPhotos - True, if the album has at least one photo
     * @hasVideos - True, if the album has at least one video
     */
    public static class PushMessageContentMediaAlbum extends PushMessageContent {

        public int totalCount;
        public boolean hasPhotos;
        public boolean hasVideos;

        public PushMessageContentMediaAlbum() {}

        public PushMessageContentMediaAlbum(int totalCount, boolean hasPhotos, boolean hasVideos) {

            this.totalCount = totalCount;
            this.hasPhotos = hasPhotos;
            this.hasVideos = hasVideos;

        }

        @Override
        public int getConstructor() { return -874278109; }

    }


    /**
     * Contains detailed information about a notification
     */
    public static abstract class NotificationType extends Object {}

    /**
     * New message was received
     *
     * @message - The message
     */
    public static class NotificationTypeNewMessage extends NotificationType {

        public Message message;

        public NotificationTypeNewMessage() {}

        public NotificationTypeNewMessage(Message message) {

            this.message = message;

        }

        @Override
        public int getConstructor() { return 1885935159; }

    }


    /**
     * New secret chat was created
     */
    public static class NotificationTypeNewSecretChat extends NotificationType {

        @Override
        public int getConstructor() { return 1198638768; }

    }


    /**
     * New call was received
     *
     * @callId - Call identifier
     */
    public static class NotificationTypeNewCall extends NotificationType {

        public int callId;

        public NotificationTypeNewCall() {}

        public NotificationTypeNewCall(int callId) {

            this.callId = callId;

        }

        @Override
        public int getConstructor() { return 1712734585; }

    }


    /**
     * New message was received through a push notification
     *
     * @messageId - The message identifier
     *              The message will not be available in the chat history, but the ID can be used in viewMessages, or as reply_to_message_id
     * @senderUserId - Sender of the message
     *                 0 if unknown
     *                 Corresponding user may be inaccessible
     * @senderChatId - Sender chat of the message
     *                 0 if none
     * @senderName - Name of the sender
     *               Can be different from the name of the sender user
     * @isOutgoing - True, if the message is outgoing
     * @content - Push message content
     */
    public static class NotificationTypeNewPushMessage extends NotificationType {

        public long messageId;
        public int senderUserId;
        public long senderChatId;
        public String senderName;
        public boolean isOutgoing;
        public PushMessageContent content;

        public NotificationTypeNewPushMessage() {}

        public NotificationTypeNewPushMessage(long messageId, int senderUserId, long senderChatId, String senderName, boolean isOutgoing, PushMessageContent content) {

            this.messageId = messageId;
            this.senderUserId = senderUserId;
            this.senderChatId = senderChatId;
            this.senderName = senderName;
            this.isOutgoing = isOutgoing;
            this.content = content;

        }

        @Override
        public int getConstructor() { return -728846585; }

    }


    /**
     * Describes the type of notifications in a notification group
     */
    public static abstract class NotificationGroupType extends Object {}

    /**
     * A group containing notifications of type notificationTypeNewMessage and notificationTypeNewPushMessage with ordinary unread messages
     */
    public static class NotificationGroupTypeMessages extends NotificationGroupType {

        @Override
        public int getConstructor() { return -1702481123; }

    }


    /**
     * A group containing notifications of type notificationTypeNewMessage and notificationTypeNewPushMessage with unread mentions of the current user, replies to their messages, or a pinned message
     */
    public static class NotificationGroupTypeMentions extends NotificationGroupType {

        @Override
        public int getConstructor() { return -2050324051; }

    }


    /**
     * A group containing a notification of type notificationTypeNewSecretChat
     */
    public static class NotificationGroupTypeSecretChat extends NotificationGroupType {

        @Override
        public int getConstructor() { return 1390759476; }

    }


    /**
     * A group containing notifications of type notificationTypeNewCall
     */
    public static class NotificationGroupTypeCalls extends NotificationGroupType {

        @Override
        public int getConstructor() { return 1379123538; }

    }


    /**
     * Contains information about a notification
     *
     * @id - Unique persistent identifier of this notification
     * @date - Notification date
     * @isSilent - True, if the notification was initially silent
     * @type - Notification type
     */
    public static class Notification extends Object {

        public int id;
        public int date;
        public boolean isSilent;
        public NotificationType type;

        public Notification() {}

        public Notification(int id, int date, boolean isSilent, NotificationType type) {

            this.id = id;
            this.date = date;
            this.isSilent = isSilent;
            this.type = type;

        }

        @Override
        public int getConstructor() { return 788743120; }

    }


    /**
     * Describes a group of notifications
     *
     * @id - Unique persistent auto-incremented from 1 identifier of the notification group
     * @type - Type of the group
     * @chatId - Identifier of a chat to which all notifications in the group belong
     * @totalCount - Total number of active notifications in the group
     * @notifications - The list of active notifications
     */
    public static class NotificationGroup extends Object {

        public int id;
        public NotificationGroupType type;
        public long chatId;
        public int totalCount;
        public Notification[] notifications;

        public NotificationGroup() {}

        public NotificationGroup(int id, NotificationGroupType type, long chatId, int totalCount, Notification[] notifications) {

            this.id = id;
            this.type = type;
            this.chatId = chatId;
            this.totalCount = totalCount;
            this.notifications = notifications;

        }

        @Override
        public int getConstructor() { return 780691541; }

    }


    /**
     * Represents the value of an option
     */
    public static abstract class OptionValue extends Object {}

    /**
     * Represents a boolean option
     *
     * @value - The value of the option
     */
    public static class OptionValueBoolean extends OptionValue {

        public boolean value;

        public OptionValueBoolean() {}

        public OptionValueBoolean(boolean value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return 63135518; }

    }


    /**
     * Represents an unknown option or an option which has a default value
     */
    public static class OptionValueEmpty extends OptionValue {

        @Override
        public int getConstructor() { return 918955155; }

    }


    /**
     * Represents an integer option
     *
     * @value - The value of the option
     */
    public static class OptionValueInteger extends OptionValue {

        public long value;

        public OptionValueInteger() {}

        public OptionValueInteger(long value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return -186858780; }

    }


    /**
     * Represents a string option
     *
     * @value - The value of the option
     */
    public static class OptionValueString extends OptionValue {

        public String value;

        public OptionValueString() {}

        public OptionValueString(String value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return 756248212; }

    }


    /**
     * Represents one member of a JSON object
     *
     * @key - Member's key
     * @value - Member's value
     */
    public static class JsonObjectMember extends Object {

        public String key;
        public JsonValue value;

        public JsonObjectMember() {}

        public JsonObjectMember(String key, JsonValue value) {

            this.key = key;
            this.value = value;

        }

        @Override
        public int getConstructor() { return -1803309418; }

    }


    /**
     * Represents a JSON value
     */
    public static abstract class JsonValue extends Object {}

    /**
     * Represents a null JSON value
     */
    public static class JsonValueNull extends JsonValue {

        @Override
        public int getConstructor() { return -92872499; }

    }


    /**
     * Represents a boolean JSON value
     *
     * @value - The value
     */
    public static class JsonValueBoolean extends JsonValue {

        public boolean value;

        public JsonValueBoolean() {}

        public JsonValueBoolean(boolean value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return -2142186576; }

    }


    /**
     * Represents a numeric JSON value
     *
     * @value - The value
     */
    public static class JsonValueNumber extends JsonValue {

        public double value;

        public JsonValueNumber() {}

        public JsonValueNumber(double value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return -1010822033; }

    }


    /**
     * Represents a string JSON value
     *
     * @value - The value
     */
    public static class JsonValueString extends JsonValue {

        public String value;

        public JsonValueString() {}

        public JsonValueString(String value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return 1597947313; }

    }


    /**
     * Represents a JSON array
     *
     * @values - The list of array elements
     */
    public static class JsonValueArray extends JsonValue {

        public JsonValue[] values;

        public JsonValueArray() {}

        public JsonValueArray(JsonValue[] values) {

            this.values = values;

        }

        @Override
        public int getConstructor() { return -183913546; }

    }


    /**
     * Represents a JSON object
     *
     * @members - The list of object members
     */
    public static class JsonValueObject extends JsonValue {

        public JsonObjectMember[] members;

        public JsonValueObject() {}

        public JsonValueObject(JsonObjectMember[] members) {

            this.members = members;

        }

        @Override
        public int getConstructor() { return 520252026; }

    }


    /**
     * Represents a single rule for managing privacy settings
     */
    public static abstract class UserPrivacySettingRule extends Object {}

    /**
     * A rule to allow all users to do something
     */
    public static class UserPrivacySettingRuleAllowAll extends UserPrivacySettingRule {

        @Override
        public int getConstructor() { return -1967186881; }

    }


    /**
     * A rule to allow all of a user's contacts to do something
     */
    public static class UserPrivacySettingRuleAllowContacts extends UserPrivacySettingRule {

        @Override
        public int getConstructor() { return -1892733680; }

    }


    /**
     * A rule to allow certain specified users to do something
     *
     * @userIds - The user identifiers, total number of users in all rules must not exceed 1000
     */
    public static class UserPrivacySettingRuleAllowUsers extends UserPrivacySettingRule {

        public int[] userIds;

        public UserPrivacySettingRuleAllowUsers() {}

        public UserPrivacySettingRuleAllowUsers(int[] userIds) {

            this.userIds = userIds;

        }

        @Override
        public int getConstructor() { return 427601278; }

    }


    /**
     * A rule to allow all members of certain specified basic groups and supergroups to doing something
     *
     * @chatIds - The chat identifiers, total number of chats in all rules must not exceed 20
     */
    public static class UserPrivacySettingRuleAllowChatMembers extends UserPrivacySettingRule {

        public long[] chatIds;

        public UserPrivacySettingRuleAllowChatMembers() {}

        public UserPrivacySettingRuleAllowChatMembers(long[] chatIds) {

            this.chatIds = chatIds;

        }

        @Override
        public int getConstructor() { return -2048749863; }

    }


    /**
     * A rule to restrict all users from doing something
     */
    public static class UserPrivacySettingRuleRestrictAll extends UserPrivacySettingRule {

        @Override
        public int getConstructor() { return -1406495408; }

    }


    /**
     * A rule to restrict all contacts of a user from doing something
     */
    public static class UserPrivacySettingRuleRestrictContacts extends UserPrivacySettingRule {

        @Override
        public int getConstructor() { return 1008389378; }

    }


    /**
     * A rule to restrict all specified users from doing something
     *
     * @userIds - The user identifiers, total number of users in all rules must not exceed 1000
     */
    public static class UserPrivacySettingRuleRestrictUsers extends UserPrivacySettingRule {

        public int[] userIds;

        public UserPrivacySettingRuleRestrictUsers() {}

        public UserPrivacySettingRuleRestrictUsers(int[] userIds) {

            this.userIds = userIds;

        }

        @Override
        public int getConstructor() { return 2119951802; }

    }


    /**
     * A rule to restrict all members of specified basic groups and supergroups from doing something
     *
     * @chatIds - The chat identifiers, total number of chats in all rules must not exceed 20
     */
    public static class UserPrivacySettingRuleRestrictChatMembers extends UserPrivacySettingRule {

        public long[] chatIds;

        public UserPrivacySettingRuleRestrictChatMembers() {}

        public UserPrivacySettingRuleRestrictChatMembers(long[] chatIds) {

            this.chatIds = chatIds;

        }

        @Override
        public int getConstructor() { return 392530897; }

    }


    /**
     * A list of privacy rules
     * Rules are matched in the specified order
     * The first matched rule defines the privacy setting for a given user
     * If no rule matches, the action is not allowed
     *
     * @rules - A list of rules
     */
    public static class UserPrivacySettingRules extends Object {

        public UserPrivacySettingRule[] rules;

        public UserPrivacySettingRules() {}

        public UserPrivacySettingRules(UserPrivacySettingRule[] rules) {

            this.rules = rules;

        }

        @Override
        public int getConstructor() { return 322477541; }

    }


    /**
     * Describes available user privacy settings
     */
    public static abstract class UserPrivacySetting extends Object {}

    /**
     * A privacy setting for managing whether the user's online status is visible
     */
    public static class UserPrivacySettingShowStatus extends UserPrivacySetting {

        @Override
        public int getConstructor() { return 1862829310; }

    }


    /**
     * A privacy setting for managing whether the user's profile photo is visible
     */
    public static class UserPrivacySettingShowProfilePhoto extends UserPrivacySetting {

        @Override
        public int getConstructor() { return 1408485877; }

    }


    /**
     * A privacy setting for managing whether a link to the user's account is included in forwarded messages
     */
    public static class UserPrivacySettingShowLinkInForwardedMessages extends UserPrivacySetting {

        @Override
        public int getConstructor() { return 592688870; }

    }


    /**
     * A privacy setting for managing whether the user's phone number is visible
     */
    public static class UserPrivacySettingShowPhoneNumber extends UserPrivacySetting {

        @Override
        public int getConstructor() { return -791567831; }

    }


    /**
     * A privacy setting for managing whether the user can be invited to chats
     */
    public static class UserPrivacySettingAllowChatInvites extends UserPrivacySetting {

        @Override
        public int getConstructor() { return 1271668007; }

    }


    /**
     * A privacy setting for managing whether the user can be called
     */
    public static class UserPrivacySettingAllowCalls extends UserPrivacySetting {

        @Override
        public int getConstructor() { return -906967291; }

    }


    /**
     * A privacy setting for managing whether peer-to-peer connections can be used for calls
     */
    public static class UserPrivacySettingAllowPeerToPeerCalls extends UserPrivacySetting {

        @Override
        public int getConstructor() { return 352500032; }

    }


    /**
     * A privacy setting for managing whether the user can be found by their phone number
     * Checked only if the phone number is not known to the other user
     * Can be set only to "Allow contacts" or "Allow all"
     */
    public static class UserPrivacySettingAllowFindingByPhoneNumber extends UserPrivacySetting {

        @Override
        public int getConstructor() { return -1846645423; }

    }


    /**
     * Contains information about the period of inactivity after which the current user's account will automatically be deleted
     *
     * @days - Number of days of inactivity before the account will be flagged for deletion
     *         Should range from 30-366 days
     */
    public static class AccountTtl extends Object {

        public int days;

        public AccountTtl() {}

        public AccountTtl(int days) {

            this.days = days;

        }

        @Override
        public int getConstructor() { return 1324495492; }

    }


    /**
     * Contains information about one session in a Telegram application used by the current user
     * Sessions should be shown to the user in the returned order
     *
     * @id - Session identifier
     * @isCurrent - True, if this session is the current session
     * @isPasswordPending - True, if a password is needed to complete authorization of the session
     * @apiId - Telegram API identifier, as provided by the application
     * @applicationName - Name of the application, as provided by the application
     * @applicationVersion - The version of the application, as provided by the application
     * @isOfficialApplication - True, if the application is an official application or uses the api_id of an official application
     * @deviceModel - Model of the device the application has been run or is running on, as provided by the application
     * @platform - Operating system the application has been run or is running on, as provided by the application
     * @systemVersion - Version of the operating system the application has been run or is running on, as provided by the application
     * @logInDate - Point in time (Unix timestamp) when the user has logged in
     * @lastActiveDate - Point in time (Unix timestamp) when the session was last used
     * @ip - IP address from which the session was created, in human-readable format
     * @country - A two-letter country code for the country from which the session was created, based on the IP address
     * @region - Region code from which the session was created, based on the IP address
     */
    public static class Session extends Object {

        public long id;
        public boolean isCurrent;
        public boolean isPasswordPending;
        public int apiId;
        public String applicationName;
        public String applicationVersion;
        public boolean isOfficialApplication;
        public String deviceModel;
        public String platform;
        public String systemVersion;
        public int logInDate;
        public int lastActiveDate;
        public String ip;
        public String country;
        public String region;

        public Session() {}

        public Session(long id, boolean isCurrent, boolean isPasswordPending, int apiId, String applicationName, String applicationVersion, boolean isOfficialApplication, String deviceModel, String platform, String systemVersion, int logInDate, int lastActiveDate, String ip, String country, String region) {

            this.id = id;
            this.isCurrent = isCurrent;
            this.isPasswordPending = isPasswordPending;
            this.apiId = apiId;
            this.applicationName = applicationName;
            this.applicationVersion = applicationVersion;
            this.isOfficialApplication = isOfficialApplication;
            this.deviceModel = deviceModel;
            this.platform = platform;
            this.systemVersion = systemVersion;
            this.logInDate = logInDate;
            this.lastActiveDate = lastActiveDate;
            this.ip = ip;
            this.country = country;
            this.region = region;

        }

        @Override
        public int getConstructor() { return 1920553176; }

    }


    /**
     * Contains a list of sessions
     *
     * @sessions - List of sessions
     */
    public static class Sessions extends Object {

        public Session[] sessions;

        public Sessions() {}

        public Sessions(Session[] sessions) {

            this.sessions = sessions;

        }

        @Override
        public int getConstructor() { return -463118121; }

    }


    /**
     * Contains information about one website the current user is logged in with Telegram
     *
     * @id - Website identifier
     * @domainName - The domain name of the website
     * @botUserId - User identifier of a bot linked with the website
     * @browser - The version of a browser used to log in
     * @platform - Operating system the browser is running on
     * @logInDate - Point in time (Unix timestamp) when the user was logged in
     * @lastActiveDate - Point in time (Unix timestamp) when obtained authorization was last used
     * @ip - IP address from which the user was logged in, in human-readable format
     * @location - Human-readable description of a country and a region, from which the user was logged in, based on the IP address
     */
    public static class ConnectedWebsite extends Object {

        public long id;
        public String domainName;
        public int botUserId;
        public String browser;
        public String platform;
        public int logInDate;
        public int lastActiveDate;
        public String ip;
        public String location;

        public ConnectedWebsite() {}

        public ConnectedWebsite(long id, String domainName, int botUserId, String browser, String platform, int logInDate, int lastActiveDate, String ip, String location) {

            this.id = id;
            this.domainName = domainName;
            this.botUserId = botUserId;
            this.browser = browser;
            this.platform = platform;
            this.logInDate = logInDate;
            this.lastActiveDate = lastActiveDate;
            this.ip = ip;
            this.location = location;

        }

        @Override
        public int getConstructor() { return -1538986855; }

    }


    /**
     * Contains a list of websites the current user is logged in with Telegram
     *
     * @websites - List of connected websites
     */
    public static class ConnectedWebsites extends Object {

        public ConnectedWebsite[] websites;

        public ConnectedWebsites() {}

        public ConnectedWebsites(ConnectedWebsite[] websites) {

            this.websites = websites;

        }

        @Override
        public int getConstructor() { return -1727949694; }

    }


    /**
     * Describes the reason why a chat is reported
     */
    public static abstract class ChatReportReason extends Object {}

    /**
     * The chat contains spam messages
     */
    public static class ChatReportReasonSpam extends ChatReportReason {

        @Override
        public int getConstructor() { return -510848863; }

    }


    /**
     * The chat promotes violence
     */
    public static class ChatReportReasonViolence extends ChatReportReason {

        @Override
        public int getConstructor() { return -1330235395; }

    }


    /**
     * The chat contains pornographic messages
     */
    public static class ChatReportReasonPornography extends ChatReportReason {

        @Override
        public int getConstructor() { return 722614385; }

    }


    /**
     * The chat has child abuse related content
     */
    public static class ChatReportReasonChildAbuse extends ChatReportReason {

        @Override
        public int getConstructor() { return -1070686531; }

    }


    /**
     * The chat contains copyrighted content
     */
    public static class ChatReportReasonCopyright extends ChatReportReason {

        @Override
        public int getConstructor() { return 986898080; }

    }


    /**
     * The location-based chat is unrelated to its stated location
     */
    public static class ChatReportReasonUnrelatedLocation extends ChatReportReason {

        @Override
        public int getConstructor() { return 2632403; }

    }


    /**
     * A custom reason provided by the user
     *
     * @text - Report text
     */
    public static class ChatReportReasonCustom extends ChatReportReason {

        public String text;

        public ChatReportReasonCustom() {}

        public ChatReportReasonCustom(String text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 544575454; }

    }


    /**
     * Contains an HTTPS link to a message in a supergroup or channel
     *
     * @link - Message link
     * @isPublic - True, if the link will work for non-members of the chat
     */
    public static class MessageLink extends Object {

        public String link;
        public boolean isPublic;

        public MessageLink() {}

        public MessageLink(String link, boolean isPublic) {

            this.link = link;
            this.isPublic = isPublic;

        }

        @Override
        public int getConstructor() { return -1354089818; }

    }


    /**
     * Contains information about a link to a message in a chat
     *
     * @isPublic - True, if the link is a public link for a message in a chat
     * @chatId - If found, identifier of the chat to which the message belongs, 0 otherwise
     * @message - If found, the linked message
     * @forAlbum - True, if the whole media album to which the message belongs is linked
     * @forComment - True, if the message is linked as a channel post comment or from a message thread
     */
    public static class MessageLinkInfo extends Object {

        public boolean isPublic;
        public long chatId;
        @Nullable public Message message;
        public boolean forAlbum;
        public boolean forComment;

        public MessageLinkInfo() {}

        public MessageLinkInfo(boolean isPublic, long chatId, @Nullable Message message, boolean forAlbum, boolean forComment) {

            this.isPublic = isPublic;
            this.chatId = chatId;
            this.message = message;
            this.forAlbum = forAlbum;
            this.forComment = forComment;

        }

        @Override
        public int getConstructor() { return -1002342529; }

    }


    /**
     * Contains a part of a file
     *
     * @data - File bytes
     */
    public static class FilePart extends Object {

        public byte[] data;

        public FilePart() {}

        public FilePart(byte[] data) {

            this.data = data;

        }

        @Override
        public int getConstructor() { return 911821878; }

    }


    /**
     * Represents the type of a file
     */
    public static abstract class FileType extends Object {}

    /**
     * The data is not a file
     */
    public static class FileTypeNone extends FileType {

        @Override
        public int getConstructor() { return 2003009189; }

    }


    /**
     * The file is an animation
     */
    public static class FileTypeAnimation extends FileType {

        @Override
        public int getConstructor() { return -290816582; }

    }


    /**
     * The file is an audio file
     */
    public static class FileTypeAudio extends FileType {

        @Override
        public int getConstructor() { return -709112160; }

    }


    /**
     * The file is a document
     */
    public static class FileTypeDocument extends FileType {

        @Override
        public int getConstructor() { return -564722929; }

    }


    /**
     * The file is a photo
     */
    public static class FileTypePhoto extends FileType {

        @Override
        public int getConstructor() { return -1718914651; }

    }


    /**
     * The file is a profile photo
     */
    public static class FileTypeProfilePhoto extends FileType {

        @Override
        public int getConstructor() { return 1795089315; }

    }


    /**
     * The file was sent to a secret chat (the file type is not known to the server)
     */
    public static class FileTypeSecret extends FileType {

        @Override
        public int getConstructor() { return -1871899401; }

    }


    /**
     * The file is a thumbnail of a file from a secret chat
     */
    public static class FileTypeSecretThumbnail extends FileType {

        @Override
        public int getConstructor() { return -1401326026; }

    }


    /**
     * The file is a file from Secure storage used for storing Telegram Passport files
     */
    public static class FileTypeSecure extends FileType {

        @Override
        public int getConstructor() { return -1419133146; }

    }


    /**
     * The file is a sticker
     */
    public static class FileTypeSticker extends FileType {

        @Override
        public int getConstructor() { return 475233385; }

    }


    /**
     * The file is a thumbnail of another file
     */
    public static class FileTypeThumbnail extends FileType {

        @Override
        public int getConstructor() { return -12443298; }

    }


    /**
     * The file type is not yet known
     */
    public static class FileTypeUnknown extends FileType {

        @Override
        public int getConstructor() { return -2011566768; }

    }


    /**
     * The file is a video
     */
    public static class FileTypeVideo extends FileType {

        @Override
        public int getConstructor() { return 1430816539; }

    }


    /**
     * The file is a video note
     */
    public static class FileTypeVideoNote extends FileType {

        @Override
        public int getConstructor() { return -518412385; }

    }


    /**
     * The file is a voice note
     */
    public static class FileTypeVoiceNote extends FileType {

        @Override
        public int getConstructor() { return -588681661; }

    }


    /**
     * The file is a wallpaper or a background pattern
     */
    public static class FileTypeWallpaper extends FileType {

        @Override
        public int getConstructor() { return 1854930076; }

    }


    /**
     * Contains the storage usage statistics for a specific file type
     *
     * @fileType - File type
     * @size - Total size of the files
     * @count - Total number of files
     */
    public static class StorageStatisticsByFileType extends Object {

        public FileType fileType;
        public long size;
        public int count;

        public StorageStatisticsByFileType() {}

        public StorageStatisticsByFileType(FileType fileType, long size, int count) {

            this.fileType = fileType;
            this.size = size;
            this.count = count;

        }

        @Override
        public int getConstructor() { return 714012840; }

    }


    /**
     * Contains the storage usage statistics for a specific chat
     *
     * @chatId - Chat identifier
     *           0 if none
     * @size - Total size of the files in the chat
     * @count - Total number of files in the chat
     * @byFileType - Statistics split by file types
     */
    public static class StorageStatisticsByChat extends Object {

        public long chatId;
        public long size;
        public int count;
        public StorageStatisticsByFileType[] byFileType;

        public StorageStatisticsByChat() {}

        public StorageStatisticsByChat(long chatId, long size, int count, StorageStatisticsByFileType[] byFileType) {

            this.chatId = chatId;
            this.size = size;
            this.count = count;
            this.byFileType = byFileType;

        }

        @Override
        public int getConstructor() { return 635434531; }

    }


    /**
     * Contains the exact storage usage statistics split by chats and file type
     *
     * @size - Total size of files
     * @count - Total number of files
     * @byChat - Statistics split by chats
     */
    public static class StorageStatistics extends Object {

        public long size;
        public int count;
        public StorageStatisticsByChat[] byChat;

        public StorageStatistics() {}

        public StorageStatistics(long size, int count, StorageStatisticsByChat[] byChat) {

            this.size = size;
            this.count = count;
            this.byChat = byChat;

        }

        @Override
        public int getConstructor() { return 217237013; }

    }


    /**
     * Contains approximate storage usage statistics, excluding files of unknown file type
     *
     * @filesSize - Approximate total size of files
     * @fileCount - Approximate number of files
     * @databaseSize - Size of the database
     * @languagePackDatabaseSize - Size of the language pack database
     * @logSize - Size of the TDLib internal log
     */
    public static class StorageStatisticsFast extends Object {

        public long filesSize;
        public int fileCount;
        public long databaseSize;
        public long languagePackDatabaseSize;
        public long logSize;

        public StorageStatisticsFast() {}

        public StorageStatisticsFast(long filesSize, int fileCount, long databaseSize, long languagePackDatabaseSize, long logSize) {

            this.filesSize = filesSize;
            this.fileCount = fileCount;
            this.databaseSize = databaseSize;
            this.languagePackDatabaseSize = languagePackDatabaseSize;
            this.logSize = logSize;

        }

        @Override
        public int getConstructor() { return -884922271; }

    }


    /**
     * Contains database statistics
     *
     * @statistics - Database statistics in an unspecified human-readable format
     */
    public static class DatabaseStatistics extends Object {

        public String statistics;

        public DatabaseStatistics() {}

        public DatabaseStatistics(String statistics) {

            this.statistics = statistics;

        }

        @Override
        public int getConstructor() { return -1123912880; }

    }


    /**
     * Represents the type of a network
     */
    public static abstract class NetworkType extends Object {}

    /**
     * The network is not available
     */
    public static class NetworkTypeNone extends NetworkType {

        @Override
        public int getConstructor() { return -1971691759; }

    }


    /**
     * A mobile network
     */
    public static class NetworkTypeMobile extends NetworkType {

        @Override
        public int getConstructor() { return 819228239; }

    }


    /**
     * A mobile roaming network
     */
    public static class NetworkTypeMobileRoaming extends NetworkType {

        @Override
        public int getConstructor() { return -1435199760; }

    }


    /**
     * A Wi-Fi network
     */
    public static class NetworkTypeWiFi extends NetworkType {

        @Override
        public int getConstructor() { return -633872070; }

    }


    /**
     * A different network type (e.g., Ethernet network)
     */
    public static class NetworkTypeOther extends NetworkType {

        @Override
        public int getConstructor() { return 1942128539; }

    }


    /**
     * Contains statistics about network usage
     */
    public static abstract class NetworkStatisticsEntry extends Object {}

    /**
     * Contains information about the total amount of data that was used to send and receive files
     *
     * @fileType - Type of the file the data is part of
     * @networkType - Type of the network the data was sent through
     *                Call setNetworkType to maintain the actual network type
     * @sentBytes - Total number of bytes sent
     * @receivedBytes - Total number of bytes received
     */
    public static class NetworkStatisticsEntryFile extends NetworkStatisticsEntry {

        public FileType fileType;
        public NetworkType networkType;
        public long sentBytes;
        public long receivedBytes;

        public NetworkStatisticsEntryFile() {}

        public NetworkStatisticsEntryFile(FileType fileType, NetworkType networkType, long sentBytes, long receivedBytes) {

            this.fileType = fileType;
            this.networkType = networkType;
            this.sentBytes = sentBytes;
            this.receivedBytes = receivedBytes;

        }

        @Override
        public int getConstructor() { return 188452706; }

    }


    /**
     * Contains information about the total amount of data that was used for calls
     *
     * @networkType - Type of the network the data was sent through
     *                Call setNetworkType to maintain the actual network type
     * @sentBytes - Total number of bytes sent
     * @receivedBytes - Total number of bytes received
     * @duration - Total call duration, in seconds
     */
    public static class NetworkStatisticsEntryCall extends NetworkStatisticsEntry {

        public NetworkType networkType;
        public long sentBytes;
        public long receivedBytes;
        public double duration;

        public NetworkStatisticsEntryCall() {}

        public NetworkStatisticsEntryCall(NetworkType networkType, long sentBytes, long receivedBytes, double duration) {

            this.networkType = networkType;
            this.sentBytes = sentBytes;
            this.receivedBytes = receivedBytes;
            this.duration = duration;

        }

        @Override
        public int getConstructor() { return 737000365; }

    }


    /**
     * A full list of available network statistic entries
     *
     * @sinceDate - Point in time (Unix timestamp) from which the statistics are collected
     * @entries - Network statistics entries
     */
    public static class NetworkStatistics extends Object {

        public int sinceDate;
        public NetworkStatisticsEntry[] entries;

        public NetworkStatistics() {}

        public NetworkStatistics(int sinceDate, NetworkStatisticsEntry[] entries) {

            this.sinceDate = sinceDate;
            this.entries = entries;

        }

        @Override
        public int getConstructor() { return 1615554212; }

    }


    /**
     * Contains auto-download settings
     *
     * @isAutoDownloadEnabled - True, if the auto-download is enabled
     * @maxPhotoFileSize - The maximum size of a photo file to be auto-downloaded
     * @maxVideoFileSize - The maximum size of a video file to be auto-downloaded
     * @maxOtherFileSize - The maximum size of other file types to be auto-downloaded
     * @videoUploadBitrate - The maximum suggested bitrate for uploaded videos
     * @preloadLargeVideos - True, if the beginning of video files needs to be preloaded for instant playback
     * @preloadNextAudio - True, if the next audio track needs to be preloaded while the user is listening to an audio file
     * @useLessDataForCalls - True, if "use less data for calls" option needs to be enabled
     */
    public static class AutoDownloadSettings extends Object {

        public boolean isAutoDownloadEnabled;
        public int maxPhotoFileSize;
        public int maxVideoFileSize;
        public int maxOtherFileSize;
        public int videoUploadBitrate;
        public boolean preloadLargeVideos;
        public boolean preloadNextAudio;
        public boolean useLessDataForCalls;

        public AutoDownloadSettings() {}

        public AutoDownloadSettings(boolean isAutoDownloadEnabled, int maxPhotoFileSize, int maxVideoFileSize, int maxOtherFileSize, int videoUploadBitrate, boolean preloadLargeVideos, boolean preloadNextAudio, boolean useLessDataForCalls) {

            this.isAutoDownloadEnabled = isAutoDownloadEnabled;
            this.maxPhotoFileSize = maxPhotoFileSize;
            this.maxVideoFileSize = maxVideoFileSize;
            this.maxOtherFileSize = maxOtherFileSize;
            this.videoUploadBitrate = videoUploadBitrate;
            this.preloadLargeVideos = preloadLargeVideos;
            this.preloadNextAudio = preloadNextAudio;
            this.useLessDataForCalls = useLessDataForCalls;

        }

        @Override
        public int getConstructor() { return -2144418333; }

    }


    /**
     * Contains auto-download settings presets for the user
     *
     * @low - Preset with lowest settings
     *        Supposed to be used by default when roaming
     * @medium - Preset with medium settings
     *           Supposed to be used by default when using mobile data
     * @high - Preset with highest settings
     *         Supposed to be used by default when connected on Wi-Fi
     */
    public static class AutoDownloadSettingsPresets extends Object {

        public AutoDownloadSettings low;
        public AutoDownloadSettings medium;
        public AutoDownloadSettings high;

        public AutoDownloadSettingsPresets() {}

        public AutoDownloadSettingsPresets(AutoDownloadSettings low, AutoDownloadSettings medium, AutoDownloadSettings high) {

            this.low = low;
            this.medium = medium;
            this.high = high;

        }

        @Override
        public int getConstructor() { return -782099166; }

    }


    /**
     * Describes the current state of the connection to Telegram servers
     */
    public static abstract class ConnectionState extends Object {}

    /**
     * Currently waiting for the network to become available
     * Use setNetworkType to change the available network type
     */
    public static class ConnectionStateWaitingForNetwork extends ConnectionState {

        @Override
        public int getConstructor() { return 1695405912; }

    }


    /**
     * Currently establishing a connection with a proxy server
     */
    public static class ConnectionStateConnectingToProxy extends ConnectionState {

        @Override
        public int getConstructor() { return -93187239; }

    }


    /**
     * Currently establishing a connection to the Telegram servers
     */
    public static class ConnectionStateConnecting extends ConnectionState {

        @Override
        public int getConstructor() { return -1298400670; }

    }


    /**
     * Downloading data received while the application was offline
     */
    public static class ConnectionStateUpdating extends ConnectionState {

        @Override
        public int getConstructor() { return -188104009; }

    }


    /**
     * There is a working connection to the Telegram servers
     */
    public static class ConnectionStateReady extends ConnectionState {

        @Override
        public int getConstructor() { return 48608492; }

    }


    /**
     * Represents the categories of chats for which a list of frequently used chats can be retrieved
     */
    public static abstract class TopChatCategory extends Object {}

    /**
     * A category containing frequently used private chats with non-bot users
     */
    public static class TopChatCategoryUsers extends TopChatCategory {

        @Override
        public int getConstructor() { return 1026706816; }

    }


    /**
     * A category containing frequently used private chats with bot users
     */
    public static class TopChatCategoryBots extends TopChatCategory {

        @Override
        public int getConstructor() { return -1577129195; }

    }


    /**
     * A category containing frequently used basic groups and supergroups
     */
    public static class TopChatCategoryGroups extends TopChatCategory {

        @Override
        public int getConstructor() { return 1530056846; }

    }


    /**
     * A category containing frequently used channels
     */
    public static class TopChatCategoryChannels extends TopChatCategory {

        @Override
        public int getConstructor() { return -500825885; }

    }


    /**
     * A category containing frequently used chats with inline bots sorted by their usage in inline mode
     */
    public static class TopChatCategoryInlineBots extends TopChatCategory {

        @Override
        public int getConstructor() { return 377023356; }

    }


    /**
     * A category containing frequently used chats used for calls
     */
    public static class TopChatCategoryCalls extends TopChatCategory {

        @Override
        public int getConstructor() { return 356208861; }

    }


    /**
     * A category containing frequently used chats used to forward messages
     */
    public static class TopChatCategoryForwardChats extends TopChatCategory {

        @Override
        public int getConstructor() { return 1695922133; }

    }


    /**
     * Describes the type of a URL linking to an internal Telegram entity
     */
    public static abstract class TMeUrlType extends Object {}

    /**
     * A URL linking to a user
     *
     * @userId - Identifier of the user
     */
    public static class TMeUrlTypeUser extends TMeUrlType {

        public int userId;

        public TMeUrlTypeUser() {}

        public TMeUrlTypeUser(int userId) {

            this.userId = userId;

        }

        @Override
        public int getConstructor() { return -1198700130; }

    }


    /**
     * A URL linking to a public supergroup or channel
     *
     * @supergroupId - Identifier of the supergroup or channel
     */
    public static class TMeUrlTypeSupergroup extends TMeUrlType {

        public long supergroupId;

        public TMeUrlTypeSupergroup() {}

        public TMeUrlTypeSupergroup(long supergroupId) {

            this.supergroupId = supergroupId;

        }

        @Override
        public int getConstructor() { return -1353369944; }

    }


    /**
     * A chat invite link
     *
     * @info - Chat invite link info
     */
    public static class TMeUrlTypeChatInvite extends TMeUrlType {

        public ChatInviteLinkInfo info;

        public TMeUrlTypeChatInvite() {}

        public TMeUrlTypeChatInvite(ChatInviteLinkInfo info) {

            this.info = info;

        }

        @Override
        public int getConstructor() { return 313907785; }

    }


    /**
     * A URL linking to a sticker set
     *
     * @stickerSetId - Identifier of the sticker set
     */
    public static class TMeUrlTypeStickerSet extends TMeUrlType {

        public long stickerSetId;

        public TMeUrlTypeStickerSet() {}

        public TMeUrlTypeStickerSet(long stickerSetId) {

            this.stickerSetId = stickerSetId;

        }

        @Override
        public int getConstructor() { return 1602473196; }

    }


    /**
     * Represents a URL linking to an internal Telegram entity
     *
     * @url - URL
     * @type - Type of the URL
     */
    public static class TMeUrl extends Object {

        public String url;
        public TMeUrlType type;

        public TMeUrl() {}

        public TMeUrl(String url, TMeUrlType type) {

            this.url = url;
            this.type = type;

        }

        @Override
        public int getConstructor() { return -1140786622; }

    }


    /**
     * Contains a list of t.me URLs
     *
     * @urls - List of URLs
     */
    public static class TMeUrls extends Object {

        public TMeUrl[] urls;

        public TMeUrls() {}

        public TMeUrls(TMeUrl[] urls) {

            this.urls = urls;

        }

        @Override
        public int getConstructor() { return -1130595098; }

    }


    /**
     * Describes an action suggested to the current user
     */
    public static abstract class SuggestedAction extends Object {}

    /**
     * Suggests the user to enable "archive_and_mute_new_chats_from_unknown_users" option
     */
    public static class SuggestedActionEnableArchiveAndMuteNewChats extends SuggestedAction {

        @Override
        public int getConstructor() { return 2017586255; }

    }


    /**
     * Suggests the user to check authorization phone number and change the phone number if it is inaccessible
     */
    public static class SuggestedActionCheckPhoneNumber extends SuggestedAction {

        @Override
        public int getConstructor() { return 648771563; }

    }


    /**
     * Contains a counter
     *
     * @count - Count
     */
    public static class Count extends Object {

        public int count;

        public Count() {}

        public Count(int count) {

            this.count = count;

        }

        @Override
        public int getConstructor() { return 1295577348; }

    }


    /**
     * Contains some text
     *
     * @text - Text
     */
    public static class Text extends Object {

        public String text;

        public Text() {}

        public Text(String text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 578181272; }

    }


    /**
     * Contains a value representing a number of seconds
     *
     * @seconds - Number of seconds
     */
    public static class Seconds extends Object {

        public double seconds;

        public Seconds() {}

        public Seconds(double seconds) {

            this.seconds = seconds;

        }

        @Override
        public int getConstructor() { return 959899022; }

    }


    /**
     * Contains information about a tg:// deep link
     *
     * @text - Text to be shown to the user
     * @needUpdateApplication - True, if user should be asked to update the application
     */
    public static class DeepLinkInfo extends Object {

        public FormattedText text;
        public boolean needUpdateApplication;

        public DeepLinkInfo() {}

        public DeepLinkInfo(FormattedText text, boolean needUpdateApplication) {

            this.text = text;
            this.needUpdateApplication = needUpdateApplication;

        }

        @Override
        public int getConstructor() { return 1864081662; }

    }


    /**
     * Describes the way the text should be parsed for TextEntities
     */
    public static abstract class TextParseMode extends Object {}

    /**
     * The text uses Markdown-style formatting
     *
     * @version - Version of the parser: 0 or 1 - Telegram Bot API "Markdown" parse mode, 2 - Telegram Bot API "MarkdownV2" parse mode
     */
    public static class TextParseModeMarkdown extends TextParseMode {

        public int version;

        public TextParseModeMarkdown() {}

        public TextParseModeMarkdown(int version) {

            this.version = version;

        }

        @Override
        public int getConstructor() { return 360073407; }

    }


    /**
     * The text uses HTML-style formatting
     * The same as Telegram Bot API "HTML" parse mode
     */
    public static class TextParseModeHTML extends TextParseMode {

        @Override
        public int getConstructor() { return 1660208627; }

    }


    /**
     * Describes the type of a proxy server
     */
    public static abstract class ProxyType extends Object {}

    /**
     * A SOCKS5 proxy server
     *
     * @username - Username for logging in
     * @password - Password for logging in
     */
    public static class ProxyTypeSocks5 extends ProxyType {

        @Nullable public String username;
        @Nullable public String password;

        public ProxyTypeSocks5() {}

        public ProxyTypeSocks5(@Nullable String username, @Nullable String password) {

            this.username = username;
            this.password = password;

        }

        @Override
        public int getConstructor() { return -890027341; }

    }


    /**
     * A HTTP transparent proxy server
     *
     * @username - Username for logging in
     * @password - Password for logging in
     * @httpOnly - Pass true if the proxy supports only HTTP requests and doesn't support transparent TCP connections via HTTP CONNECT method
     */
    public static class ProxyTypeHttp extends ProxyType {

        @Nullable public String username;
        @Nullable public String password;
        public boolean httpOnly;

        public ProxyTypeHttp() {}

        public ProxyTypeHttp(@Nullable String username, @Nullable String password, boolean httpOnly) {

            this.username = username;
            this.password = password;
            this.httpOnly = httpOnly;

        }

        @Override
        public int getConstructor() { return -1547188361; }

    }


    /**
     * An MTProto proxy server
     *
     * @secret - The proxy's secret in hexadecimal encoding
     */
    public static class ProxyTypeMtproto extends ProxyType {

        public String secret;

        public ProxyTypeMtproto() {}

        public ProxyTypeMtproto(String secret) {

            this.secret = secret;

        }

        @Override
        public int getConstructor() { return -1964826627; }

    }


    /**
     * Contains information about a proxy server
     *
     * @id - Unique identifier of the proxy
     * @server - Proxy server IP address
     * @port - Proxy server port
     * @lastUsedDate - Point in time (Unix timestamp) when the proxy was last used
     *                 0 if never
     * @isEnabled - True, if the proxy is enabled now
     * @type - Type of the proxy
     */
    public static class Proxy extends Object {

        public int id;
        public String server;
        public int port;
        public int lastUsedDate;
        public boolean isEnabled;
        public ProxyType type;

        public Proxy() {}

        public Proxy(int id, String server, int port, int lastUsedDate, boolean isEnabled, ProxyType type) {

            this.id = id;
            this.server = server;
            this.port = port;
            this.lastUsedDate = lastUsedDate;
            this.isEnabled = isEnabled;
            this.type = type;

        }

        @Override
        public int getConstructor() { return 196049779; }

    }


    /**
     * Represents a list of proxy servers
     *
     * @proxies - List of proxy servers
     */
    public static class Proxies extends Object {

        public Proxy[] proxies;

        public Proxies() {}

        public Proxies(Proxy[] proxies) {

            this.proxies = proxies;

        }

        @Override
        public int getConstructor() { return 1200447205; }

    }


    /**
     * Describes a sticker that needs to be added to a sticker set
     */
    public static abstract class InputSticker extends Object {}

    /**
     * A static sticker in PNG format, which will be converted to WEBP server-side
     *
     * @sticker - PNG image with the sticker
     *            Must be up to 512 KB in size and fit in a 512x512 square
     * @emojis - Emojis corresponding to the sticker
     * @maskPosition - For masks, position where the mask should be placed
     */
    public static class InputStickerStatic extends InputSticker {

        public InputFile sticker;
        public String emojis;
        @Nullable public MaskPosition maskPosition;

        public InputStickerStatic() {}

        public InputStickerStatic(InputFile sticker, String emojis, @Nullable MaskPosition maskPosition) {

            this.sticker = sticker;
            this.emojis = emojis;
            this.maskPosition = maskPosition;

        }

        @Override
        public int getConstructor() { return 1409680603; }

    }


    /**
     * An animated sticker in TGS format
     *
     * @sticker - File with the animated sticker
     *            Only local or uploaded within a week files are supported
     *            See https://core.telegram.org/animated_stickers#technical-requirements for technical requirements
     * @emojis - Emojis corresponding to the sticker
     */
    public static class InputStickerAnimated extends InputSticker {

        public InputFile sticker;
        public String emojis;

        public InputStickerAnimated() {}

        public InputStickerAnimated(InputFile sticker, String emojis) {

            this.sticker = sticker;
            this.emojis = emojis;

        }

        @Override
        public int getConstructor() { return -1127265952; }

    }


    /**
     * Represents a date range
     *
     * @startDate - Point in time (Unix timestamp) at which the date range begins
     * @endDate - Point in time (Unix timestamp) at which the date range ends
     */
    public static class DateRange extends Object {

        public int startDate;
        public int endDate;

        public DateRange() {}

        public DateRange(int startDate, int endDate) {

            this.startDate = startDate;
            this.endDate = endDate;

        }

        @Override
        public int getConstructor() { return 1360333926; }

    }


    /**
     * A statistics value
     *
     * @value - The value
     * @previousValue - The value for the previous day
     * @growthRatePercentage - The growth rate of the value, as a percentage
     */
    public static class StatisticsValue extends Object {

        public double value;
        public double previousValue;
        public double growthRatePercentage;

        public StatisticsValue() {}

        public StatisticsValue(double value, double previousValue, double growthRatePercentage) {

            this.value = value;
            this.previousValue = previousValue;
            this.growthRatePercentage = growthRatePercentage;

        }

        @Override
        public int getConstructor() { return 1147508964; }

    }


    /**
     * Describes a statistics graph
     */
    public static abstract class StatisticsGraph extends Object {}

    /**
     * A graph data
     *
     * @jsonData - Graph data in JSON format
     * @zoomToken - If non-empty, a token which can be used to receive a zoomed in graph
     */
    public static class StatisticsGraphData extends StatisticsGraph {

        public String jsonData;
        public String zoomToken;

        public StatisticsGraphData() {}

        public StatisticsGraphData(String jsonData, String zoomToken) {

            this.jsonData = jsonData;
            this.zoomToken = zoomToken;

        }

        @Override
        public int getConstructor() { return -1756117226; }

    }


    /**
     * The graph data to be asynchronously loaded through getStatisticsGraph
     *
     * @token - The token to use for data loading
     */
    public static class StatisticsGraphAsync extends StatisticsGraph {

        public String token;

        public StatisticsGraphAsync() {}

        public StatisticsGraphAsync(String token) {

            this.token = token;

        }

        @Override
        public int getConstructor() { return 1064479337; }

    }


    /**
     * An error message to be shown to the user instead of the graph
     *
     * @errorMessage - The error message
     */
    public static class StatisticsGraphError extends StatisticsGraph {

        public String errorMessage;

        public StatisticsGraphError() {}

        public StatisticsGraphError(String errorMessage) {

            this.errorMessage = errorMessage;

        }

        @Override
        public int getConstructor() { return -61804431; }

    }


    /**
     * Contains statistics about interactions with a message
     *
     * @messageId - Message identifier
     * @viewCount - Number of times the message was viewed
     * @forwardCount - Number of times the message was forwarded
     */
    public static class ChatStatisticsMessageInteractionInfo extends Object {

        public long messageId;
        public int viewCount;
        public int forwardCount;

        public ChatStatisticsMessageInteractionInfo() {}

        public ChatStatisticsMessageInteractionInfo(long messageId, int viewCount, int forwardCount) {

            this.messageId = messageId;
            this.viewCount = viewCount;
            this.forwardCount = forwardCount;

        }

        @Override
        public int getConstructor() { return -765580756; }

    }


    /**
     * Contains statistics about messages sent by a user
     *
     * @userId - User identifier
     * @sentMessageCount - Number of sent messages
     * @averageCharacterCount - Average number of characters in sent messages
     */
    public static class ChatStatisticsMessageSenderInfo extends Object {

        public int userId;
        public int sentMessageCount;
        public int averageCharacterCount;

        public ChatStatisticsMessageSenderInfo() {}

        public ChatStatisticsMessageSenderInfo(int userId, int sentMessageCount, int averageCharacterCount) {

            this.userId = userId;
            this.sentMessageCount = sentMessageCount;
            this.averageCharacterCount = averageCharacterCount;

        }

        @Override
        public int getConstructor() { return 1716075179; }

    }


    /**
     * Contains statistics about administrator actions done by a user
     *
     * @userId - Administrator user identifier
     * @deletedMessageCount - Number of messages deleted by the administrator
     * @bannedUserCount - Number of users banned by the administrator
     * @restrictedUserCount - Number of users restricted by the administrator
     */
    public static class ChatStatisticsAdministratorActionsInfo extends Object {

        public int userId;
        public int deletedMessageCount;
        public int bannedUserCount;
        public int restrictedUserCount;

        public ChatStatisticsAdministratorActionsInfo() {}

        public ChatStatisticsAdministratorActionsInfo(int userId, int deletedMessageCount, int bannedUserCount, int restrictedUserCount) {

            this.userId = userId;
            this.deletedMessageCount = deletedMessageCount;
            this.bannedUserCount = bannedUserCount;
            this.restrictedUserCount = restrictedUserCount;

        }

        @Override
        public int getConstructor() { return 1988384904; }

    }


    /**
     * Contains statistics about number of new members invited by a user
     *
     * @userId - User identifier
     * @addedMemberCount - Number of new members invited by the user
     */
    public static class ChatStatisticsInviterInfo extends Object {

        public int userId;
        public int addedMemberCount;

        public ChatStatisticsInviterInfo() {}

        public ChatStatisticsInviterInfo(int userId, int addedMemberCount) {

            this.userId = userId;
            this.addedMemberCount = addedMemberCount;

        }

        @Override
        public int getConstructor() { return -399517859; }

    }


    /**
     * Contains a detailed statistics about a chat
     */
    public static abstract class ChatStatistics extends Object {}

    /**
     * A detailed statistics about a supergroup chat
     *
     * @period - A period to which the statistics applies
     * @memberCount - Number of members in the chat
     * @messageCount - Number of messages sent to the chat
     * @viewerCount - Number of users who viewed messages in the chat
     * @senderCount - Number of users who sent messages to the chat
     * @memberCountGraph - A graph containing number of members in the chat
     * @joinGraph - A graph containing number of members joined and left the chat
     * @joinBySourceGraph - A graph containing number of new member joins per source
     * @languageGraph - A graph containing distribution of active users per language
     * @messageContentGraph - A graph containing distribution of sent messages by content type
     * @actionGraph - A graph containing number of different actions in the chat
     * @dayGraph - A graph containing distribution of message views per hour
     * @weekGraph - A graph containing distribution of message views per day of week
     * @topSenders - List of users sent most messages in the last week
     * @topAdministrators - List of most active administrators in the last week
     * @topInviters - List of most active inviters of new members in the last week
     */
    public static class ChatStatisticsSupergroup extends ChatStatistics {

        public DateRange period;
        public StatisticsValue memberCount;
        public StatisticsValue messageCount;
        public StatisticsValue viewerCount;
        public StatisticsValue senderCount;
        public StatisticsGraph memberCountGraph;
        public StatisticsGraph joinGraph;
        public StatisticsGraph joinBySourceGraph;
        public StatisticsGraph languageGraph;
        public StatisticsGraph messageContentGraph;
        public StatisticsGraph actionGraph;
        public StatisticsGraph dayGraph;
        public StatisticsGraph weekGraph;
        public ChatStatisticsMessageSenderInfo[] topSenders;
        public ChatStatisticsAdministratorActionsInfo[] topAdministrators;
        public ChatStatisticsInviterInfo[] topInviters;

        public ChatStatisticsSupergroup() {}

        public ChatStatisticsSupergroup(DateRange period, StatisticsValue memberCount, StatisticsValue messageCount, StatisticsValue viewerCount, StatisticsValue senderCount, StatisticsGraph memberCountGraph, StatisticsGraph joinGraph, StatisticsGraph joinBySourceGraph, StatisticsGraph languageGraph, StatisticsGraph messageContentGraph, StatisticsGraph actionGraph, StatisticsGraph dayGraph, StatisticsGraph weekGraph, ChatStatisticsMessageSenderInfo[] topSenders, ChatStatisticsAdministratorActionsInfo[] topAdministrators, ChatStatisticsInviterInfo[] topInviters) {

            this.period = period;
            this.memberCount = memberCount;
            this.messageCount = messageCount;
            this.viewerCount = viewerCount;
            this.senderCount = senderCount;
            this.memberCountGraph = memberCountGraph;
            this.joinGraph = joinGraph;
            this.joinBySourceGraph = joinBySourceGraph;
            this.languageGraph = languageGraph;
            this.messageContentGraph = messageContentGraph;
            this.actionGraph = actionGraph;
            this.dayGraph = dayGraph;
            this.weekGraph = weekGraph;
            this.topSenders = topSenders;
            this.topAdministrators = topAdministrators;
            this.topInviters = topInviters;

        }

        @Override
        public int getConstructor() { return 1234327223; }

    }


    /**
     * A detailed statistics about a channel chat
     *
     * @period - A period to which the statistics applies
     * @memberCount - Number of members in the chat
     * @meanViewCount - Mean number of times the recently sent messages was viewed
     * @meanShareCount - Mean number of times the recently sent messages was shared
     * @enabledNotificationsPercentage - A percentage of users with enabled notifications for the chat
     * @memberCountGraph - A graph containing number of members in the chat
     * @joinGraph - A graph containing number of members joined and left the chat
     * @muteGraph - A graph containing number of members muted and unmuted the chat
     * @viewCountByHourGraph - A graph containing number of message views in a given hour in the last two weeks
     * @viewCountBySourceGraph - A graph containing number of message views per source
     * @joinBySourceGraph - A graph containing number of new member joins per source
     * @languageGraph - A graph containing number of users viewed chat messages per language
     * @messageInteractionGraph - A graph containing number of chat message views and shares
     * @instantViewInteractionGraph - A graph containing number of views of associated with the chat instant views
     * @recentMessageInteractions - Detailed statistics about number of views and shares of recently sent messages
     */
    public static class ChatStatisticsChannel extends ChatStatistics {

        public DateRange period;
        public StatisticsValue memberCount;
        public StatisticsValue meanViewCount;
        public StatisticsValue meanShareCount;
        public double enabledNotificationsPercentage;
        public StatisticsGraph memberCountGraph;
        public StatisticsGraph joinGraph;
        public StatisticsGraph muteGraph;
        public StatisticsGraph viewCountByHourGraph;
        public StatisticsGraph viewCountBySourceGraph;
        public StatisticsGraph joinBySourceGraph;
        public StatisticsGraph languageGraph;
        public StatisticsGraph messageInteractionGraph;
        public StatisticsGraph instantViewInteractionGraph;
        public ChatStatisticsMessageInteractionInfo[] recentMessageInteractions;

        public ChatStatisticsChannel() {}

        public ChatStatisticsChannel(DateRange period, StatisticsValue memberCount, StatisticsValue meanViewCount, StatisticsValue meanShareCount, double enabledNotificationsPercentage, StatisticsGraph memberCountGraph, StatisticsGraph joinGraph, StatisticsGraph muteGraph, StatisticsGraph viewCountByHourGraph, StatisticsGraph viewCountBySourceGraph, StatisticsGraph joinBySourceGraph, StatisticsGraph languageGraph, StatisticsGraph messageInteractionGraph, StatisticsGraph instantViewInteractionGraph, ChatStatisticsMessageInteractionInfo[] recentMessageInteractions) {

            this.period = period;
            this.memberCount = memberCount;
            this.meanViewCount = meanViewCount;
            this.meanShareCount = meanShareCount;
            this.enabledNotificationsPercentage = enabledNotificationsPercentage;
            this.memberCountGraph = memberCountGraph;
            this.joinGraph = joinGraph;
            this.muteGraph = muteGraph;
            this.viewCountByHourGraph = viewCountByHourGraph;
            this.viewCountBySourceGraph = viewCountBySourceGraph;
            this.joinBySourceGraph = joinBySourceGraph;
            this.languageGraph = languageGraph;
            this.messageInteractionGraph = messageInteractionGraph;
            this.instantViewInteractionGraph = instantViewInteractionGraph;
            this.recentMessageInteractions = recentMessageInteractions;

        }

        @Override
        public int getConstructor() { return -326982581; }

    }


    /**
     * A detailed statistics about a message
     *
     * @messageInteractionGraph - A graph containing number of message views and shares
     */
    public static class MessageStatistics extends Object {

        public StatisticsGraph messageInteractionGraph;

        public MessageStatistics() {}

        public MessageStatistics(StatisticsGraph messageInteractionGraph) {

            this.messageInteractionGraph = messageInteractionGraph;

        }

        @Override
        public int getConstructor() { return 1148915634; }

    }


    /**
     * Contains notifications about data changes
     */
    public static abstract class Update extends Object {}

    /**
     * The user authorization state has changed
     *
     * @authorizationState - New authorization state
     */
    public static class UpdateAuthorizationState extends Update {

        public AuthorizationState authorizationState;

        public UpdateAuthorizationState() {}

        public UpdateAuthorizationState(AuthorizationState authorizationState) {

            this.authorizationState = authorizationState;

        }

        @Override
        public int getConstructor() { return 1622347490; }

    }


    /**
     * A new message was received
     * Can also be an outgoing message
     *
     * @message - The new message
     */
    public static class UpdateNewMessage extends Update {

        public Message message;

        public UpdateNewMessage() {}

        public UpdateNewMessage(Message message) {

            this.message = message;

        }

        @Override
        public int getConstructor() { return -563105266; }

    }


    /**
     * A request to send a message has reached the Telegram server
     * This doesn't mean that the message will be sent successfully or even that the send message request will be processed
     * This update will be sent only if the option "use_quick_ack" is set to true
     * This update may be sent multiple times for the same message
     *
     * @chatId - The chat identifier of the sent message
     * @messageId - A temporary message identifier
     */
    public static class UpdateMessageSendAcknowledged extends Update {

        public long chatId;
        public long messageId;

        public UpdateMessageSendAcknowledged() {}

        public UpdateMessageSendAcknowledged(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return 1302843961; }

    }


    /**
     * A message has been successfully sent
     *
     * @message - Information about the sent message
     *            Usually only the message identifier, date, and content are changed, but almost all other fields can also change
     * @oldMessageId - The previous temporary message identifier
     */
    public static class UpdateMessageSendSucceeded extends Update {

        public Message message;
        public long oldMessageId;

        public UpdateMessageSendSucceeded() {}

        public UpdateMessageSendSucceeded(Message message, long oldMessageId) {

            this.message = message;
            this.oldMessageId = oldMessageId;

        }

        @Override
        public int getConstructor() { return 1815715197; }

    }


    /**
     * A message failed to send
     * Be aware that some messages being sent can be irrecoverably deleted, in which case updateDeleteMessages will be received instead of this update
     *
     * @message - Contains information about the message which failed to send
     * @oldMessageId - The previous temporary message identifier
     * @errorCode - An error code
     * @errorMessage - Error message
     */
    public static class UpdateMessageSendFailed extends Update {

        public Message message;
        public long oldMessageId;
        public int errorCode;
        public String errorMessage;

        public UpdateMessageSendFailed() {}

        public UpdateMessageSendFailed(Message message, long oldMessageId, int errorCode, String errorMessage) {

            this.message = message;
            this.oldMessageId = oldMessageId;
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;

        }

        @Override
        public int getConstructor() { return -1032335779; }

    }


    /**
     * The message content has changed
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @newContent - New message content
     */
    public static class UpdateMessageContent extends Update {

        public long chatId;
        public long messageId;
        public MessageContent newContent;

        public UpdateMessageContent() {}

        public UpdateMessageContent(long chatId, long messageId, MessageContent newContent) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.newContent = newContent;

        }

        @Override
        public int getConstructor() { return 506903332; }

    }


    /**
     * A message was edited
     * Changes in the message content will come in a separate updateMessageContent
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @editDate - Point in time (Unix timestamp) when the message was edited
     * @replyMarkup - New message reply markup
     */
    public static class UpdateMessageEdited extends Update {

        public long chatId;
        public long messageId;
        public int editDate;
        @Nullable public ReplyMarkup replyMarkup;

        public UpdateMessageEdited() {}

        public UpdateMessageEdited(long chatId, long messageId, int editDate, @Nullable ReplyMarkup replyMarkup) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.editDate = editDate;
            this.replyMarkup = replyMarkup;

        }

        @Override
        public int getConstructor() { return -559545626; }

    }


    /**
     * The information about interactions with a message has changed
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @interactionInfo - New information about interactions with the message
     */
    public static class UpdateMessageInteractionInfo extends Update {

        public long chatId;
        public long messageId;
        @Nullable public MessageInteractionInfo interactionInfo;

        public UpdateMessageInteractionInfo() {}

        public UpdateMessageInteractionInfo(long chatId, long messageId, @Nullable MessageInteractionInfo interactionInfo) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.interactionInfo = interactionInfo;

        }

        @Override
        public int getConstructor() { return -1417659394; }

    }


    /**
     * The message content was opened
     * Updates voice note messages to "listened", video note messages to "viewed" and starts the TTL timer for self-destructing messages
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     */
    public static class UpdateMessageContentOpened extends Update {

        public long chatId;
        public long messageId;

        public UpdateMessageContentOpened() {}

        public UpdateMessageContentOpened(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return -1520523131; }

    }


    /**
     * A message with an unread mention was read
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @unreadMentionCount - The new number of unread mention messages left in the chat
     */
    public static class UpdateMessageMentionRead extends Update {

        public long chatId;
        public long messageId;
        public int unreadMentionCount;

        public UpdateMessageMentionRead() {}

        public UpdateMessageMentionRead(long chatId, long messageId, int unreadMentionCount) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.unreadMentionCount = unreadMentionCount;

        }

        @Override
        public int getConstructor() { return -252228282; }

    }


    /**
     * A message with a live location was viewed
     * When the update is received, the application is supposed to update the live location
     *
     * @chatId - Identifier of the chat with the live location message
     * @messageId - Identifier of the message with live location
     */
    public static class UpdateMessageLiveLocationViewed extends Update {

        public long chatId;
        public long messageId;

        public UpdateMessageLiveLocationViewed() {}

        public UpdateMessageLiveLocationViewed(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return -1308260971; }

    }


    /**
     * A new chat has been loaded/created
     * This update is guaranteed to come before the chat identifier is returned to the application
     * The chat field changes will be reported through separate updates
     *
     * @chat - The chat
     */
    public static class UpdateNewChat extends Update {

        public Chat chat;

        public UpdateNewChat() {}

        public UpdateNewChat(Chat chat) {

            this.chat = chat;

        }

        @Override
        public int getConstructor() { return 2075757773; }

    }


    /**
     * The title of a chat was changed
     *
     * @chatId - Chat identifier
     * @title - The new chat title
     */
    public static class UpdateChatTitle extends Update {

        public long chatId;
        public String title;

        public UpdateChatTitle() {}

        public UpdateChatTitle(long chatId, String title) {

            this.chatId = chatId;
            this.title = title;

        }

        @Override
        public int getConstructor() { return -175405660; }

    }


    /**
     * A chat photo was changed
     *
     * @chatId - Chat identifier
     * @photo - The new chat photo
     */
    public static class UpdateChatPhoto extends Update {

        public long chatId;
        @Nullable public ChatPhotoInfo photo;

        public UpdateChatPhoto() {}

        public UpdateChatPhoto(long chatId, @Nullable ChatPhotoInfo photo) {

            this.chatId = chatId;
            this.photo = photo;

        }

        @Override
        public int getConstructor() { return -324713921; }

    }


    /**
     * Chat permissions was changed
     *
     * @chatId - Chat identifier
     * @permissions - The new chat permissions
     */
    public static class UpdateChatPermissions extends Update {

        public long chatId;
        public ChatPermissions permissions;

        public UpdateChatPermissions() {}

        public UpdateChatPermissions(long chatId, ChatPermissions permissions) {

            this.chatId = chatId;
            this.permissions = permissions;

        }

        @Override
        public int getConstructor() { return -1622010003; }

    }


    /**
     * The last message of a chat was changed
     * If last_message is null, then the last message in the chat became unknown
     * Some new unknown messages might be added to the chat in this case
     *
     * @chatId - Chat identifier
     * @lastMessage - The new last message in the chat
     * @positions - The new chat positions in the chat lists
     */
    public static class UpdateChatLastMessage extends Update {

        public long chatId;
        @Nullable public Message lastMessage;
        public ChatPosition[] positions;

        public UpdateChatLastMessage() {}

        public UpdateChatLastMessage(long chatId, @Nullable Message lastMessage, ChatPosition[] positions) {

            this.chatId = chatId;
            this.lastMessage = lastMessage;
            this.positions = positions;

        }

        @Override
        public int getConstructor() { return -923244537; }

    }


    /**
     * The position of a chat in a chat list has changed
     * Instead of this update updateChatLastMessage or updateChatDraftMessage might be sent
     *
     * @chatId - Chat identifier
     * @position - New chat position
     *             If new order is 0, then the chat needs to be removed from the list
     */
    public static class UpdateChatPosition extends Update {

        public long chatId;
        public ChatPosition position;

        public UpdateChatPosition() {}

        public UpdateChatPosition(long chatId, ChatPosition position) {

            this.chatId = chatId;
            this.position = position;

        }

        @Override
        public int getConstructor() { return -8979849; }

    }


    /**
     * A chat was marked as unread or was read
     *
     * @chatId - Chat identifier
     * @isMarkedAsUnread - New value of is_marked_as_unread
     */
    public static class UpdateChatIsMarkedAsUnread extends Update {

        public long chatId;
        public boolean isMarkedAsUnread;

        public UpdateChatIsMarkedAsUnread() {}

        public UpdateChatIsMarkedAsUnread(long chatId, boolean isMarkedAsUnread) {

            this.chatId = chatId;
            this.isMarkedAsUnread = isMarkedAsUnread;

        }

        @Override
        public int getConstructor() { return 1468347188; }

    }


    /**
     * A chat was blocked or unblocked
     *
     * @chatId - Chat identifier
     * @isBlocked - New value of is_blocked
     */
    public static class UpdateChatIsBlocked extends Update {

        public long chatId;
        public boolean isBlocked;

        public UpdateChatIsBlocked() {}

        public UpdateChatIsBlocked(long chatId, boolean isBlocked) {

            this.chatId = chatId;
            this.isBlocked = isBlocked;

        }

        @Override
        public int getConstructor() { return -1998946752; }

    }


    /**
     * A chat's has_scheduled_messages field has changed
     *
     * @chatId - Chat identifier
     * @hasScheduledMessages - New value of has_scheduled_messages
     */
    public static class UpdateChatHasScheduledMessages extends Update {

        public long chatId;
        public boolean hasScheduledMessages;

        public UpdateChatHasScheduledMessages() {}

        public UpdateChatHasScheduledMessages(long chatId, boolean hasScheduledMessages) {

            this.chatId = chatId;
            this.hasScheduledMessages = hasScheduledMessages;

        }

        @Override
        public int getConstructor() { return 2064958167; }

    }


    /**
     * The value of the default disable_notification parameter, used when a message is sent to the chat, was changed
     *
     * @chatId - Chat identifier
     * @defaultDisableNotification - The new default_disable_notification value
     */
    public static class UpdateChatDefaultDisableNotification extends Update {

        public long chatId;
        public boolean defaultDisableNotification;

        public UpdateChatDefaultDisableNotification() {}

        public UpdateChatDefaultDisableNotification(long chatId, boolean defaultDisableNotification) {

            this.chatId = chatId;
            this.defaultDisableNotification = defaultDisableNotification;

        }

        @Override
        public int getConstructor() { return 464087707; }

    }


    /**
     * Incoming messages were read or number of unread messages has been changed
     *
     * @chatId - Chat identifier
     * @lastReadInboxMessageId - Identifier of the last read incoming message
     * @unreadCount - The number of unread messages left in the chat
     */
    public static class UpdateChatReadInbox extends Update {

        public long chatId;
        public long lastReadInboxMessageId;
        public int unreadCount;

        public UpdateChatReadInbox() {}

        public UpdateChatReadInbox(long chatId, long lastReadInboxMessageId, int unreadCount) {

            this.chatId = chatId;
            this.lastReadInboxMessageId = lastReadInboxMessageId;
            this.unreadCount = unreadCount;

        }

        @Override
        public int getConstructor() { return -797952281; }

    }


    /**
     * Outgoing messages were read
     *
     * @chatId - Chat identifier
     * @lastReadOutboxMessageId - Identifier of last read outgoing message
     */
    public static class UpdateChatReadOutbox extends Update {

        public long chatId;
        public long lastReadOutboxMessageId;

        public UpdateChatReadOutbox() {}

        public UpdateChatReadOutbox(long chatId, long lastReadOutboxMessageId) {

            this.chatId = chatId;
            this.lastReadOutboxMessageId = lastReadOutboxMessageId;

        }

        @Override
        public int getConstructor() { return 708334213; }

    }


    /**
     * The chat unread_mention_count has changed
     *
     * @chatId - Chat identifier
     * @unreadMentionCount - The number of unread mention messages left in the chat
     */
    public static class UpdateChatUnreadMentionCount extends Update {

        public long chatId;
        public int unreadMentionCount;

        public UpdateChatUnreadMentionCount() {}

        public UpdateChatUnreadMentionCount(long chatId, int unreadMentionCount) {

            this.chatId = chatId;
            this.unreadMentionCount = unreadMentionCount;

        }

        @Override
        public int getConstructor() { return -2131461348; }

    }


    /**
     * Notification settings for a chat were changed
     *
     * @chatId - Chat identifier
     * @notificationSettings - The new notification settings
     */
    public static class UpdateChatNotificationSettings extends Update {

        public long chatId;
        public ChatNotificationSettings notificationSettings;

        public UpdateChatNotificationSettings() {}

        public UpdateChatNotificationSettings(long chatId, ChatNotificationSettings notificationSettings) {

            this.chatId = chatId;
            this.notificationSettings = notificationSettings;

        }

        @Override
        public int getConstructor() { return -803163050; }

    }


    /**
     * Notification settings for some type of chats were updated
     *
     * @scope - Types of chats for which notification settings were updated
     * @notificationSettings - The new notification settings
     */
    public static class UpdateScopeNotificationSettings extends Update {

        public NotificationSettingsScope scope;
        public ScopeNotificationSettings notificationSettings;

        public UpdateScopeNotificationSettings() {}

        public UpdateScopeNotificationSettings(NotificationSettingsScope scope, ScopeNotificationSettings notificationSettings) {

            this.scope = scope;
            this.notificationSettings = notificationSettings;

        }

        @Override
        public int getConstructor() { return -1203975309; }

    }


    /**
     * The chat action bar was changed
     *
     * @chatId - Chat identifier
     * @actionBar - The new value of the action bar
     */
    public static class UpdateChatActionBar extends Update {

        public long chatId;
        @Nullable public ChatActionBar actionBar;

        public UpdateChatActionBar() {}

        public UpdateChatActionBar(long chatId, @Nullable ChatActionBar actionBar) {

            this.chatId = chatId;
            this.actionBar = actionBar;

        }

        @Override
        public int getConstructor() { return -643671870; }

    }


    /**
     * The chat pinned message was changed
     *
     * @chatId - Chat identifier
     * @pinnedMessageId - The new identifier of the pinned message
     *                    0 if there is no pinned message in the chat
     */
    public static class UpdateChatPinnedMessage extends Update {

        public long chatId;
        public long pinnedMessageId;

        public UpdateChatPinnedMessage() {}

        public UpdateChatPinnedMessage(long chatId, long pinnedMessageId) {

            this.chatId = chatId;
            this.pinnedMessageId = pinnedMessageId;

        }

        @Override
        public int getConstructor() { return 802160507; }

    }


    /**
     * The default chat reply markup was changed
     * Can occur because new messages with reply markup were received or because an old reply markup was hidden by the user
     *
     * @chatId - Chat identifier
     * @replyMarkupMessageId - Identifier of the message from which reply markup needs to be used
     *                         0 if there is no default custom reply markup in the chat
     */
    public static class UpdateChatReplyMarkup extends Update {

        public long chatId;
        public long replyMarkupMessageId;

        public UpdateChatReplyMarkup() {}

        public UpdateChatReplyMarkup(long chatId, long replyMarkupMessageId) {

            this.chatId = chatId;
            this.replyMarkupMessageId = replyMarkupMessageId;

        }

        @Override
        public int getConstructor() { return 1309386144; }

    }


    /**
     * A chat draft has changed
     * Be aware that the update may come in the currently opened chat but with old content of the draft
     * If the user has changed the content of the draft, this update shouldn't be applied
     *
     * @chatId - Chat identifier
     * @draftMessage - The new draft message
     * @positions - The new chat positions in the chat lists
     */
    public static class UpdateChatDraftMessage extends Update {

        public long chatId;
        @Nullable public DraftMessage draftMessage;
        public ChatPosition[] positions;

        public UpdateChatDraftMessage() {}

        public UpdateChatDraftMessage(long chatId, @Nullable DraftMessage draftMessage, ChatPosition[] positions) {

            this.chatId = chatId;
            this.draftMessage = draftMessage;
            this.positions = positions;

        }

        @Override
        public int getConstructor() { return 1455190380; }

    }


    /**
     * The list of chat filters or a chat filter has changed
     *
     * @chatFilters - The new list of chat filters
     */
    public static class UpdateChatFilters extends Update {

        public ChatFilterInfo[] chatFilters;

        public UpdateChatFilters() {}

        public UpdateChatFilters(ChatFilterInfo[] chatFilters) {

            this.chatFilters = chatFilters;

        }

        @Override
        public int getConstructor() { return -961518713; }

    }


    /**
     * The number of online group members has changed
     * This update with non-zero count is sent only for currently opened chats
     * There is no guarantee that it will be sent just after the count has changed
     *
     * @chatId - Identifier of the chat
     * @onlineMemberCount - New number of online members in the chat, or 0 if unknown
     */
    public static class UpdateChatOnlineMemberCount extends Update {

        public long chatId;
        public int onlineMemberCount;

        public UpdateChatOnlineMemberCount() {}

        public UpdateChatOnlineMemberCount(long chatId, int onlineMemberCount) {

            this.chatId = chatId;
            this.onlineMemberCount = onlineMemberCount;

        }

        @Override
        public int getConstructor() { return 487369373; }

    }


    /**
     * A notification was changed
     *
     * @notificationGroupId - Unique notification group identifier
     * @notification - Changed notification
     */
    public static class UpdateNotification extends Update {

        public int notificationGroupId;
        public Notification notification;

        public UpdateNotification() {}

        public UpdateNotification(int notificationGroupId, Notification notification) {

            this.notificationGroupId = notificationGroupId;
            this.notification = notification;

        }

        @Override
        public int getConstructor() { return -1897496876; }

    }


    /**
     * A list of active notifications in a notification group has changed
     *
     * @notificationGroupId - Unique notification group identifier
     * @type - New type of the notification group
     * @chatId - Identifier of a chat to which all notifications in the group belong
     * @notificationSettingsChatId - Chat identifier, which notification settings must be applied to the added notifications
     * @isSilent - True, if the notifications should be shown without sound
     * @totalCount - Total number of unread notifications in the group, can be bigger than number of active notifications
     * @addedNotifications - List of added group notifications, sorted by notification ID
     * @removedNotificationIds - Identifiers of removed group notifications, sorted by notification ID
     */
    public static class UpdateNotificationGroup extends Update {

        public int notificationGroupId;
        public NotificationGroupType type;
        public long chatId;
        public long notificationSettingsChatId;
        public boolean isSilent;
        public int totalCount;
        public Notification[] addedNotifications;
        public int[] removedNotificationIds;

        public UpdateNotificationGroup() {}

        public UpdateNotificationGroup(int notificationGroupId, NotificationGroupType type, long chatId, long notificationSettingsChatId, boolean isSilent, int totalCount, Notification[] addedNotifications, int[] removedNotificationIds) {

            this.notificationGroupId = notificationGroupId;
            this.type = type;
            this.chatId = chatId;
            this.notificationSettingsChatId = notificationSettingsChatId;
            this.isSilent = isSilent;
            this.totalCount = totalCount;
            this.addedNotifications = addedNotifications;
            this.removedNotificationIds = removedNotificationIds;

        }

        @Override
        public int getConstructor() { return -2049005665; }

    }


    /**
     * Contains active notifications that was shown on previous application launches
     * This update is sent only if the message database is used
     * In that case it comes once before any updateNotification and updateNotificationGroup update
     *
     * @groups - Lists of active notification groups
     */
    public static class UpdateActiveNotifications extends Update {

        public NotificationGroup[] groups;

        public UpdateActiveNotifications() {}

        public UpdateActiveNotifications(NotificationGroup[] groups) {

            this.groups = groups;

        }

        @Override
        public int getConstructor() { return -1306672221; }

    }


    /**
     * Describes whether there are some pending notification updates
     * Can be used to prevent application from killing, while there are some pending notifications
     *
     * @haveDelayedNotifications - True, if there are some delayed notification updates, which will be sent soon
     * @haveUnreceivedNotifications - True, if there can be some yet unreceived notifications, which are being fetched from the server
     */
    public static class UpdateHavePendingNotifications extends Update {

        public boolean haveDelayedNotifications;
        public boolean haveUnreceivedNotifications;

        public UpdateHavePendingNotifications() {}

        public UpdateHavePendingNotifications(boolean haveDelayedNotifications, boolean haveUnreceivedNotifications) {

            this.haveDelayedNotifications = haveDelayedNotifications;
            this.haveUnreceivedNotifications = haveUnreceivedNotifications;

        }

        @Override
        public int getConstructor() { return 179233243; }

    }


    /**
     * Some messages were deleted
     *
     * @chatId - Chat identifier
     * @messageIds - Identifiers of the deleted messages
     * @isPermanent - True, if the messages are permanently deleted by a user (as opposed to just becoming inaccessible)
     * @fromCache - True, if the messages are deleted only from the cache and can possibly be retrieved again in the future
     */
    public static class UpdateDeleteMessages extends Update {

        public long chatId;
        public long[] messageIds;
        public boolean isPermanent;
        public boolean fromCache;

        public UpdateDeleteMessages() {}

        public UpdateDeleteMessages(long chatId, long[] messageIds, boolean isPermanent, boolean fromCache) {

            this.chatId = chatId;
            this.messageIds = messageIds;
            this.isPermanent = isPermanent;
            this.fromCache = fromCache;

        }

        @Override
        public int getConstructor() { return 1669252686; }

    }


    /**
     * User activity in the chat has changed
     *
     * @chatId - Chat identifier
     * @messageThreadId - If not 0, a message thread identifier in which the action was performed
     * @userId - Identifier of a user performing an action
     * @action - The action description
     */
    public static class UpdateUserChatAction extends Update {

        public long chatId;
        public long messageThreadId;
        public int userId;
        public ChatAction action;

        public UpdateUserChatAction() {}

        public UpdateUserChatAction(long chatId, long messageThreadId, int userId, ChatAction action) {

            this.chatId = chatId;
            this.messageThreadId = messageThreadId;
            this.userId = userId;
            this.action = action;

        }

        @Override
        public int getConstructor() { return 2066409603; }

    }


    /**
     * The user went online or offline
     *
     * @userId - User identifier
     * @status - New status of the user
     */
    public static class UpdateUserStatus extends Update {

        public int userId;
        public UserStatus status;

        public UpdateUserStatus() {}

        public UpdateUserStatus(int userId, UserStatus status) {

            this.userId = userId;
            this.status = status;

        }

        @Override
        public int getConstructor() { return -1443545195; }

    }


    /**
     * Some data of a user has changed
     * This update is guaranteed to come before the user identifier is returned to the application
     *
     * @user - New data about the user
     */
    public static class UpdateUser extends Update {

        public User user;

        public UpdateUser() {}

        public UpdateUser(User user) {

            this.user = user;

        }

        @Override
        public int getConstructor() { return 1183394041; }

    }


    /**
     * Some data of a basic group has changed
     * This update is guaranteed to come before the basic group identifier is returned to the application
     *
     * @basicGroup - New data about the group
     */
    public static class UpdateBasicGroup extends Update {

        public BasicGroup basicGroup;

        public UpdateBasicGroup() {}

        public UpdateBasicGroup(BasicGroup basicGroup) {

            this.basicGroup = basicGroup;

        }

        @Override
        public int getConstructor() { return -1003239581; }

    }


    /**
     * Some data of a supergroup or a channel has changed
     * This update is guaranteed to come before the supergroup identifier is returned to the application
     *
     * @supergroup - New data about the supergroup
     */
    public static class UpdateSupergroup extends Update {

        public Supergroup supergroup;

        public UpdateSupergroup() {}

        public UpdateSupergroup(Supergroup supergroup) {

            this.supergroup = supergroup;

        }

        @Override
        public int getConstructor() { return -76782300; }

    }


    /**
     * Some data of a secret chat has changed
     * This update is guaranteed to come before the secret chat identifier is returned to the application
     *
     * @secretChat - New data about the secret chat
     */
    public static class UpdateSecretChat extends Update {

        public SecretChat secretChat;

        public UpdateSecretChat() {}

        public UpdateSecretChat(SecretChat secretChat) {

            this.secretChat = secretChat;

        }

        @Override
        public int getConstructor() { return -1666903253; }

    }


    /**
     * Some data from userFullInfo has been changed
     *
     * @userId - User identifier
     * @userFullInfo - New full information about the user
     */
    public static class UpdateUserFullInfo extends Update {

        public int userId;
        public UserFullInfo userFullInfo;

        public UpdateUserFullInfo() {}

        public UpdateUserFullInfo(int userId, UserFullInfo userFullInfo) {

            this.userId = userId;
            this.userFullInfo = userFullInfo;

        }

        @Override
        public int getConstructor() { return 222103874; }

    }


    /**
     * Some data from basicGroupFullInfo has been changed
     *
     * @basicGroupId - Identifier of a basic group
     * @basicGroupFullInfo - New full information about the group
     */
    public static class UpdateBasicGroupFullInfo extends Update {

        public int basicGroupId;
        public BasicGroupFullInfo basicGroupFullInfo;

        public UpdateBasicGroupFullInfo() {}

        public UpdateBasicGroupFullInfo(int basicGroupId, BasicGroupFullInfo basicGroupFullInfo) {

            this.basicGroupId = basicGroupId;
            this.basicGroupFullInfo = basicGroupFullInfo;

        }

        @Override
        public int getConstructor() { return 924030531; }

    }


    /**
     * Some data from supergroupFullInfo has been changed
     *
     * @supergroupId - Identifier of the supergroup or channel
     * @supergroupFullInfo - New full information about the supergroup
     */
    public static class UpdateSupergroupFullInfo extends Update {

        public int supergroupId;
        public SupergroupFullInfo supergroupFullInfo;

        public UpdateSupergroupFullInfo() {}

        public UpdateSupergroupFullInfo(int supergroupId, SupergroupFullInfo supergroupFullInfo) {

            this.supergroupId = supergroupId;
            this.supergroupFullInfo = supergroupFullInfo;

        }

        @Override
        public int getConstructor() { return 1288828758; }

    }


    /**
     * Service notification from the server
     * Upon receiving this the application must show a popup with the content of the notification
     *
     * @type - Notification type
     *         If type begins with "AUTH_KEY_DROP_", then two buttons "Cancel" and "Log out" should be shown under notification
     *         If user presses the second, all local data should be destroyed using Destroy method
     * @content - Notification content
     */
    public static class UpdateServiceNotification extends Update {

        public String type;
        public MessageContent content;

        public UpdateServiceNotification() {}

        public UpdateServiceNotification(String type, MessageContent content) {

            this.type = type;
            this.content = content;

        }

        @Override
        public int getConstructor() { return 1318622637; }

    }


    /**
     * Information about a file was updated
     *
     * @file - New data about the file
     */
    public static class UpdateFile extends Update {

        public File file;

        public UpdateFile() {}

        public UpdateFile(File file) {

            this.file = file;

        }

        @Override
        public int getConstructor() { return 114132831; }

    }


    /**
     * The file generation process needs to be started by the application
     *
     * @generationId - Unique identifier for the generation process
     * @originalPath - The path to a file from which a new file is generated
     * @destinationPath - The path to a file that should be created and where the new file should be generated
     * @conversion - String specifying the conversion applied to the original file
     *               If conversion is "#url#" than original_path contains an HTTP/HTTPS URL of a file, which should be downloaded by the application
     */
    public static class UpdateFileGenerationStart extends Update {

        public long generationId;
        @Nullable public String originalPath;
        public String destinationPath;
        public String conversion;

        public UpdateFileGenerationStart() {}

        public UpdateFileGenerationStart(long generationId, @Nullable String originalPath, String destinationPath, String conversion) {

            this.generationId = generationId;
            this.originalPath = originalPath;
            this.destinationPath = destinationPath;
            this.conversion = conversion;

        }

        @Override
        public int getConstructor() { return 216817388; }

    }


    /**
     * File generation is no longer needed
     *
     * @generationId - Unique identifier for the generation process
     */
    public static class UpdateFileGenerationStop extends Update {

        public long generationId;

        public UpdateFileGenerationStop() {}

        public UpdateFileGenerationStop(long generationId) {

            this.generationId = generationId;

        }

        @Override
        public int getConstructor() { return -1894449685; }

    }


    /**
     * New call was created or information about a call was updated
     *
     * @call - New data about a call
     */
    public static class UpdateCall extends Update {

        public Call call;

        public UpdateCall() {}

        public UpdateCall(Call call) {

            this.call = call;

        }

        @Override
        public int getConstructor() { return 1337184477; }

    }


    /**
     * New call signaling data arrived
     *
     * @callId - The call identifier
     * @data - The data
     */
    public static class UpdateNewCallSignalingData extends Update {

        public int callId;
        public byte[] data;

        public UpdateNewCallSignalingData() {}

        public UpdateNewCallSignalingData(int callId, byte[] data) {

            this.callId = callId;
            this.data = data;

        }

        @Override
        public int getConstructor() { return 583634317; }

    }


    /**
     * Some privacy setting rules have been changed
     *
     * @setting - The privacy setting
     * @rules - New privacy rules
     */
    public static class UpdateUserPrivacySettingRules extends Update {

        public UserPrivacySetting setting;
        public UserPrivacySettingRules rules;

        public UpdateUserPrivacySettingRules() {}

        public UpdateUserPrivacySettingRules(UserPrivacySetting setting, UserPrivacySettingRules rules) {

            this.setting = setting;
            this.rules = rules;

        }

        @Override
        public int getConstructor() { return -912960778; }

    }


    /**
     * Number of unread messages in a chat list has changed
     * This update is sent only if the message database is used
     *
     * @chatList - The chat list with changed number of unread messages
     * @unreadCount - Total number of unread messages
     * @unreadUnmutedCount - Total number of unread messages in unmuted chats
     */
    public static class UpdateUnreadMessageCount extends Update {

        public ChatList chatList;
        public int unreadCount;
        public int unreadUnmutedCount;

        public UpdateUnreadMessageCount() {}

        public UpdateUnreadMessageCount(ChatList chatList, int unreadCount, int unreadUnmutedCount) {

            this.chatList = chatList;
            this.unreadCount = unreadCount;
            this.unreadUnmutedCount = unreadUnmutedCount;

        }

        @Override
        public int getConstructor() { return 78987721; }

    }


    /**
     * Number of unread chats, i.e
     * With unread messages or marked as unread, has changed
     * This update is sent only if the message database is used
     *
     * @chatList - The chat list with changed number of unread messages
     * @totalCount - Approximate total number of chats in the chat list
     * @unreadCount - Total number of unread chats
     * @unreadUnmutedCount - Total number of unread unmuted chats
     * @markedAsUnreadCount - Total number of chats marked as unread
     * @markedAsUnreadUnmutedCount - Total number of unmuted chats marked as unread
     */
    public static class UpdateUnreadChatCount extends Update {

        public ChatList chatList;
        public int totalCount;
        public int unreadCount;
        public int unreadUnmutedCount;
        public int markedAsUnreadCount;
        public int markedAsUnreadUnmutedCount;

        public UpdateUnreadChatCount() {}

        public UpdateUnreadChatCount(ChatList chatList, int totalCount, int unreadCount, int unreadUnmutedCount, int markedAsUnreadCount, int markedAsUnreadUnmutedCount) {

            this.chatList = chatList;
            this.totalCount = totalCount;
            this.unreadCount = unreadCount;
            this.unreadUnmutedCount = unreadUnmutedCount;
            this.markedAsUnreadCount = markedAsUnreadCount;
            this.markedAsUnreadUnmutedCount = markedAsUnreadUnmutedCount;

        }

        @Override
        public int getConstructor() { return 1994494530; }

    }


    /**
     * An option changed its value
     *
     * @name - The option name
     * @value - The new option value
     */
    public static class UpdateOption extends Update {

        public String name;
        public OptionValue value;

        public UpdateOption() {}

        public UpdateOption(String name, OptionValue value) {

            this.name = name;
            this.value = value;

        }

        @Override
        public int getConstructor() { return 900822020; }

    }


    /**
     * A sticker set has changed
     *
     * @stickerSet - The sticker set
     */
    public static class UpdateStickerSet extends Update {

        public StickerSet stickerSet;

        public UpdateStickerSet() {}

        public UpdateStickerSet(StickerSet stickerSet) {

            this.stickerSet = stickerSet;

        }

        @Override
        public int getConstructor() { return 1879268812; }

    }


    /**
     * The list of installed sticker sets was updated
     *
     * @isMasks - True, if the list of installed mask sticker sets was updated
     * @stickerSetIds - The new list of installed ordinary sticker sets
     */
    public static class UpdateInstalledStickerSets extends Update {

        public boolean isMasks;
        public long[] stickerSetIds;

        public UpdateInstalledStickerSets() {}

        public UpdateInstalledStickerSets(boolean isMasks, long[] stickerSetIds) {

            this.isMasks = isMasks;
            this.stickerSetIds = stickerSetIds;

        }

        @Override
        public int getConstructor() { return 1125575977; }

    }


    /**
     * The list of trending sticker sets was updated or some of them were viewed
     *
     * @stickerSets - The prefix of the list of trending sticker sets with the newest trending sticker sets
     */
    public static class UpdateTrendingStickerSets extends Update {

        public StickerSets stickerSets;

        public UpdateTrendingStickerSets() {}

        public UpdateTrendingStickerSets(StickerSets stickerSets) {

            this.stickerSets = stickerSets;

        }

        @Override
        public int getConstructor() { return 450714593; }

    }


    /**
     * The list of recently used stickers was updated
     *
     * @isAttached - True, if the list of stickers attached to photo or video files was updated, otherwise the list of sent stickers is updated
     * @stickerIds - The new list of file identifiers of recently used stickers
     */
    public static class UpdateRecentStickers extends Update {

        public boolean isAttached;
        public int[] stickerIds;

        public UpdateRecentStickers() {}

        public UpdateRecentStickers(boolean isAttached, int[] stickerIds) {

            this.isAttached = isAttached;
            this.stickerIds = stickerIds;

        }

        @Override
        public int getConstructor() { return 1906403540; }

    }


    /**
     * The list of favorite stickers was updated
     *
     * @stickerIds - The new list of file identifiers of favorite stickers
     */
    public static class UpdateFavoriteStickers extends Update {

        public int[] stickerIds;

        public UpdateFavoriteStickers() {}

        public UpdateFavoriteStickers(int[] stickerIds) {

            this.stickerIds = stickerIds;

        }

        @Override
        public int getConstructor() { return 1662240999; }

    }


    /**
     * The list of saved animations was updated
     *
     * @animationIds - The new list of file identifiers of saved animations
     */
    public static class UpdateSavedAnimations extends Update {

        public int[] animationIds;

        public UpdateSavedAnimations() {}

        public UpdateSavedAnimations(int[] animationIds) {

            this.animationIds = animationIds;

        }

        @Override
        public int getConstructor() { return 65563814; }

    }


    /**
     * The selected background has changed
     *
     * @forDarkTheme - True, if background for dark theme has changed
     * @background - The new selected background
     */
    public static class UpdateSelectedBackground extends Update {

        public boolean forDarkTheme;
        @Nullable public Background background;

        public UpdateSelectedBackground() {}

        public UpdateSelectedBackground(boolean forDarkTheme, @Nullable Background background) {

            this.forDarkTheme = forDarkTheme;
            this.background = background;

        }

        @Override
        public int getConstructor() { return -1715658659; }

    }


    /**
     * Some language pack strings have been updated
     *
     * @localizationTarget - Localization target to which the language pack belongs
     * @languagePackId - Identifier of the updated language pack
     * @strings - List of changed language pack strings
     */
    public static class UpdateLanguagePackStrings extends Update {

        public String localizationTarget;
        public String languagePackId;
        public LanguagePackString[] strings;

        public UpdateLanguagePackStrings() {}

        public UpdateLanguagePackStrings(String localizationTarget, String languagePackId, LanguagePackString[] strings) {

            this.localizationTarget = localizationTarget;
            this.languagePackId = languagePackId;
            this.strings = strings;

        }

        @Override
        public int getConstructor() { return -1056319886; }

    }


    /**
     * The connection state has changed
     * This update must be used only to show the user a human-readable description of the connection state
     *
     * @state - The new connection state
     */
    public static class UpdateConnectionState extends Update {

        public ConnectionState state;

        public UpdateConnectionState() {}

        public UpdateConnectionState(ConnectionState state) {

            this.state = state;

        }

        @Override
        public int getConstructor() { return 1469292078; }

    }


    /**
     * New terms of service must be accepted by the user
     * If the terms of service are declined, then the deleteAccount method should be called with the reason "Decline ToS update"
     *
     * @termsOfServiceId - Identifier of the terms of service
     * @termsOfService - The new terms of service
     */
    public static class UpdateTermsOfService extends Update {

        public String termsOfServiceId;
        public TermsOfService termsOfService;

        public UpdateTermsOfService() {}

        public UpdateTermsOfService(String termsOfServiceId, TermsOfService termsOfService) {

            this.termsOfServiceId = termsOfServiceId;
            this.termsOfService = termsOfService;

        }

        @Override
        public int getConstructor() { return -1304640162; }

    }


    /**
     * The list of users nearby has changed
     * The update is guaranteed to be sent only 60 seconds after a successful searchChatsNearby request
     *
     * @usersNearby - The new list of users nearby
     */
    public static class UpdateUsersNearby extends Update {

        public ChatNearby[] usersNearby;

        public UpdateUsersNearby() {}

        public UpdateUsersNearby(ChatNearby[] usersNearby) {

            this.usersNearby = usersNearby;

        }

        @Override
        public int getConstructor() { return -1517109163; }

    }


    /**
     * The list of supported dice emojis has changed
     *
     * @emojis - The new list of supported dice emojis
     */
    public static class UpdateDiceEmojis extends Update {

        public String[] emojis;

        public UpdateDiceEmojis() {}

        public UpdateDiceEmojis(String[] emojis) {

            this.emojis = emojis;

        }

        @Override
        public int getConstructor() { return -1069066940; }

    }


    /**
     * The parameters of animation search through GetOption("animation_search_bot_username") bot has changed
     *
     * @provider - Name of the animation search provider
     * @emojis - The new list of emojis suggested for searching
     */
    public static class UpdateAnimationSearchParameters extends Update {

        public String provider;
        public String[] emojis;

        public UpdateAnimationSearchParameters() {}

        public UpdateAnimationSearchParameters(String provider, String[] emojis) {

            this.provider = provider;
            this.emojis = emojis;

        }

        @Override
        public int getConstructor() { return -1144983202; }

    }


    /**
     * The list of suggested to the user actions has changed
     *
     * @addedActions - Added suggested actions
     * @removedActions - Removed suggested actions
     */
    public static class UpdateSuggestedActions extends Update {

        public SuggestedAction[] addedActions;
        public SuggestedAction[] removedActions;

        public UpdateSuggestedActions() {}

        public UpdateSuggestedActions(SuggestedAction[] addedActions, SuggestedAction[] removedActions) {

            this.addedActions = addedActions;
            this.removedActions = removedActions;

        }

        @Override
        public int getConstructor() { return 1459452346; }

    }


    /**
     * A new incoming inline query
     * For bots only
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @userLocation - User location
     * @query - Text of the query
     * @offset - Offset of the first entry to return
     */
    public static class UpdateNewInlineQuery extends Update {

        public long id;
        public int senderUserId;
        @Nullable public Location userLocation;
        public String query;
        public String offset;

        public UpdateNewInlineQuery() {}

        public UpdateNewInlineQuery(long id, int senderUserId, @Nullable Location userLocation, String query, String offset) {

            this.id = id;
            this.senderUserId = senderUserId;
            this.userLocation = userLocation;
            this.query = query;
            this.offset = offset;

        }

        @Override
        public int getConstructor() { return 2064730634; }

    }


    /**
     * The user has chosen a result of an inline query
     * For bots only
     *
     * @senderUserId - Identifier of the user who sent the query
     * @userLocation - User location
     * @query - Text of the query
     * @resultId - Identifier of the chosen result
     * @inlineMessageId - Identifier of the sent inline message, if known
     */
    public static class UpdateNewChosenInlineResult extends Update {

        public int senderUserId;
        @Nullable public Location userLocation;
        public String query;
        public String resultId;
        public String inlineMessageId;

        public UpdateNewChosenInlineResult() {}

        public UpdateNewChosenInlineResult(int senderUserId, @Nullable Location userLocation, String query, String resultId, String inlineMessageId) {

            this.senderUserId = senderUserId;
            this.userLocation = userLocation;
            this.query = query;
            this.resultId = resultId;
            this.inlineMessageId = inlineMessageId;

        }

        @Override
        public int getConstructor() { return 527526965; }

    }


    /**
     * A new incoming callback query
     * For bots only
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @chatId - Identifier of the chat where the query was sent
     * @messageId - Identifier of the message, from which the query originated
     * @chatInstance - Identifier that uniquely corresponds to the chat to which the message was sent
     * @payload - Query payload
     */
    public static class UpdateNewCallbackQuery extends Update {

        public long id;
        public int senderUserId;
        public long chatId;
        public long messageId;
        public long chatInstance;
        public CallbackQueryPayload payload;

        public UpdateNewCallbackQuery() {}

        public UpdateNewCallbackQuery(long id, int senderUserId, long chatId, long messageId, long chatInstance, CallbackQueryPayload payload) {

            this.id = id;
            this.senderUserId = senderUserId;
            this.chatId = chatId;
            this.messageId = messageId;
            this.chatInstance = chatInstance;
            this.payload = payload;

        }

        @Override
        public int getConstructor() { return -2044226370; }

    }


    /**
     * A new incoming callback query from a message sent via a bot
     * For bots only
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @inlineMessageId - Identifier of the inline message, from which the query originated
     * @chatInstance - An identifier uniquely corresponding to the chat a message was sent to
     * @payload - Query payload
     */
    public static class UpdateNewInlineCallbackQuery extends Update {

        public long id;
        public int senderUserId;
        public String inlineMessageId;
        public long chatInstance;
        public CallbackQueryPayload payload;

        public UpdateNewInlineCallbackQuery() {}

        public UpdateNewInlineCallbackQuery(long id, int senderUserId, String inlineMessageId, long chatInstance, CallbackQueryPayload payload) {

            this.id = id;
            this.senderUserId = senderUserId;
            this.inlineMessageId = inlineMessageId;
            this.chatInstance = chatInstance;
            this.payload = payload;

        }

        @Override
        public int getConstructor() { return -1879154829; }

    }


    /**
     * A new incoming shipping query
     * For bots only
     * Only for invoices with flexible price
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @invoicePayload - Invoice payload
     * @shippingAddress - User shipping address
     */
    public static class UpdateNewShippingQuery extends Update {

        public long id;
        public int senderUserId;
        public String invoicePayload;
        public Address shippingAddress;

        public UpdateNewShippingQuery() {}

        public UpdateNewShippingQuery(long id, int senderUserId, String invoicePayload, Address shippingAddress) {

            this.id = id;
            this.senderUserId = senderUserId;
            this.invoicePayload = invoicePayload;
            this.shippingAddress = shippingAddress;

        }

        @Override
        public int getConstructor() { return -817474682; }

    }


    /**
     * A new incoming pre-checkout query
     * For bots only
     * Contains full information about a checkout
     *
     * @id - Unique query identifier
     * @senderUserId - Identifier of the user who sent the query
     * @currency - Currency for the product price
     * @totalAmount - Total price for the product, in the minimal quantity of the currency
     * @invoicePayload - Invoice payload
     * @shippingOptionId - Identifier of a shipping option chosen by the user
     *                     May be empty if not applicable
     * @orderInfo - Information about the order
     */
    public static class UpdateNewPreCheckoutQuery extends Update {

        public long id;
        public int senderUserId;
        public String currency;
        public long totalAmount;
        public byte[] invoicePayload;
        @Nullable public String shippingOptionId;
        @Nullable public OrderInfo orderInfo;

        public UpdateNewPreCheckoutQuery() {}

        public UpdateNewPreCheckoutQuery(long id, int senderUserId, String currency, long totalAmount, byte[] invoicePayload, @Nullable String shippingOptionId, @Nullable OrderInfo orderInfo) {

            this.id = id;
            this.senderUserId = senderUserId;
            this.currency = currency;
            this.totalAmount = totalAmount;
            this.invoicePayload = invoicePayload;
            this.shippingOptionId = shippingOptionId;
            this.orderInfo = orderInfo;

        }

        @Override
        public int getConstructor() { return 87964006; }

    }


    /**
     * A new incoming event
     * For bots only
     *
     * @event - A JSON-serialized event
     */
    public static class UpdateNewCustomEvent extends Update {

        public String event;

        public UpdateNewCustomEvent() {}

        public UpdateNewCustomEvent(String event) {

            this.event = event;

        }

        @Override
        public int getConstructor() { return 1994222092; }

    }


    /**
     * A new incoming query
     * For bots only
     *
     * @id - The query identifier
     * @data - JSON-serialized query data
     * @timeout - Query timeout
     */
    public static class UpdateNewCustomQuery extends Update {

        public long id;
        public String data;
        public int timeout;

        public UpdateNewCustomQuery() {}

        public UpdateNewCustomQuery(long id, String data, int timeout) {

            this.id = id;
            this.data = data;
            this.timeout = timeout;

        }

        @Override
        public int getConstructor() { return -687670874; }

    }


    /**
     * A poll was updated
     * For bots only
     *
     * @poll - New data about the poll
     */
    public static class UpdatePoll extends Update {

        public Poll poll;

        public UpdatePoll() {}

        public UpdatePoll(Poll poll) {

            this.poll = poll;

        }

        @Override
        public int getConstructor() { return -1771342902; }

    }


    /**
     * A user changed the answer to a poll
     * For bots only
     *
     * @pollId - Unique poll identifier
     * @userId - The user, who changed the answer to the poll
     * @optionIds - 0-based identifiers of answer options, chosen by the user
     */
    public static class UpdatePollAnswer extends Update {

        public long pollId;
        public int userId;
        public int[] optionIds;

        public UpdatePollAnswer() {}

        public UpdatePollAnswer(long pollId, int userId, int[] optionIds) {

            this.pollId = pollId;
            this.userId = userId;
            this.optionIds = optionIds;

        }

        @Override
        public int getConstructor() { return 1606139344; }

    }


    /**
     * Contains a list of updates
     *
     * @updates - List of updates
     */
    public static class Updates extends Object {

        public Update[] updates;

        public Updates() {}

        public Updates(Update[] updates) {

            this.updates = updates;

        }

        @Override
        public int getConstructor() { return 475842347; }

    }


    /**
     * Describes a stream to which TDLib internal log is written
     */
    public static abstract class LogStream extends Object {}

    /**
     * The log is written to stderr or an OS specific log
     */
    public static class LogStreamDefault extends LogStream {

        @Override
        public int getConstructor() { return 1390581436; }

    }


    /**
     * The log is written to a file
     *
     * @path - Path to the file to where the internal TDLib log will be written
     * @maxFileSize - The maximum size of the file to where the internal TDLib log is written before the file will be auto-rotated
     * @redirectStderr - Pass true to additionally redirect stderr to the log file
     *                   Ignored on Windows
     */
    public static class LogStreamFile extends LogStream {

        public String path;
        public long maxFileSize;
        public boolean redirectStderr;

        public LogStreamFile() {}

        public LogStreamFile(String path, long maxFileSize, boolean redirectStderr) {

            this.path = path;
            this.maxFileSize = maxFileSize;
            this.redirectStderr = redirectStderr;

        }

        @Override
        public int getConstructor() { return 1532136933; }

    }


    /**
     * The log is written nowhere
     */
    public static class LogStreamEmpty extends LogStream {

        @Override
        public int getConstructor() { return -499912244; }

    }


    /**
     * Contains a TDLib internal log verbosity level
     *
     * @verbosityLevel - Log verbosity level
     */
    public static class LogVerbosityLevel extends Object {

        public int verbosityLevel;

        public LogVerbosityLevel() {}

        public LogVerbosityLevel(int verbosityLevel) {

            this.verbosityLevel = verbosityLevel;

        }

        @Override
        public int getConstructor() { return 1734624234; }

    }


    /**
     * Contains a list of available TDLib internal log tags
     *
     * @tags - List of log tags
     */
    public static class LogTags extends Object {

        public String[] tags;

        public LogTags() {}

        public LogTags(String[] tags) {

            this.tags = tags;

        }

        @Override
        public int getConstructor() { return -1604930601; }

    }


    /**
     * A simple object containing a number
     * For testing only
     *
     * @value - Number
     */
    public static class TestInt extends Object {

        public int value;

        public TestInt() {}

        public TestInt(int value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return -574804983; }

    }


    /**
     * A simple object containing a string
     * For testing only
     *
     * @value - String
     */
    public static class TestString extends Object {

        public String value;

        public TestString() {}

        public TestString(String value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return -27891572; }

    }


    /**
     * A simple object containing a sequence of bytes
     * For testing only
     *
     * @value - Bytes
     */
    public static class TestBytes extends Object {

        public byte[] value;

        public TestBytes() {}

        public TestBytes(byte[] value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return -1541225250; }

    }


    /**
     * A simple object containing a vector of numbers
     * For testing only
     *
     * @value - Vector of numbers
     */
    public static class TestVectorInt extends Object {

        public int[] value;

        public TestVectorInt() {}

        public TestVectorInt(int[] value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return 593682027; }

    }


    /**
     * A simple object containing a vector of objects that hold a number
     * For testing only
     *
     * @value - Vector of objects
     */
    public static class TestVectorIntObject extends Object {

        public TestInt[] value;

        public TestVectorIntObject() {}

        public TestVectorIntObject(TestInt[] value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return 125891546; }

    }


    /**
     * A simple object containing a vector of strings
     * For testing only
     *
     * @value - Vector of strings
     */
    public static class TestVectorString extends Object {

        public String[] value;

        public TestVectorString() {}

        public TestVectorString(String[] value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return 79339995; }

    }


    /**
     * A simple object containing a vector of objects that hold a string
     * For testing only
     *
     * @value - Vector of objects
     */
    public static class TestVectorStringObject extends Object {

        public TestString[] value;

        public TestVectorStringObject() {}

        public TestVectorStringObject(TestString[] value) {

            this.value = value;

        }

        @Override
        public int getConstructor() { return 80780537; }

    }


    /**
     * Returns the current authorization state
     * This is an offline request
     * For informational purposes only
     * Use updateAuthorizationState instead to maintain the current authorization state
     * Can be called before initialization
     */
    public static class GetAuthorizationState extends Function {

        @Override
        public int getConstructor() { return 1949154877; }

    }


    /**
     * Sets the parameters for TDLib initialization
     * Works only when the current authorization state is authorizationStateWaitTdlibParameters
     *
     * @parameters - Parameters
     */
    public static class SetTdlibParameters extends Function {

        public TdlibParameters parameters;

        public SetTdlibParameters() {}

        public SetTdlibParameters(TdlibParameters parameters) {

            this.parameters = parameters;

        }

        @Override
        public int getConstructor() { return -1912557997; }

    }


    /**
     * Checks the database encryption key for correctness
     * Works only when the current authorization state is authorizationStateWaitEncryptionKey
     *
     * @encryptionKey - Encryption key to check or set up
     */
    public static class CheckDatabaseEncryptionKey extends Function {

        public byte[] encryptionKey;

        public CheckDatabaseEncryptionKey() {}

        public CheckDatabaseEncryptionKey(byte[] encryptionKey) {

            this.encryptionKey = encryptionKey;

        }

        @Override
        public int getConstructor() { return 1018769307; }

    }


    /**
     * Sets the phone number of the user and sends an authentication code to the user
     * Works only when the current authorization state is authorizationStateWaitPhoneNumber, or if there is no pending authentication query and the current authorization state is authorizationStateWaitCode, authorizationStateWaitRegistration, or authorizationStateWaitPassword
     *
     * @phoneNumber - The phone number of the user, in international format
     * @settings - Settings for the authentication of the user's phone number
     */
    public static class SetAuthenticationPhoneNumber extends Function {

        public String phoneNumber;
        public PhoneNumberAuthenticationSettings settings;

        public SetAuthenticationPhoneNumber() {}

        public SetAuthenticationPhoneNumber(String phoneNumber, PhoneNumberAuthenticationSettings settings) {

            this.phoneNumber = phoneNumber;
            this.settings = settings;

        }

        @Override
        public int getConstructor() { return 868276259; }

    }


    /**
     * Re-sends an authentication code to the user
     * Works only when the current authorization state is authorizationStateWaitCode and the next_code_type of the result is not null
     */
    public static class ResendAuthenticationCode extends Function {

        @Override
        public int getConstructor() { return -814377191; }

    }


    /**
     * Checks the authentication code
     * Works only when the current authorization state is authorizationStateWaitCode
     *
     * @code - The verification code received via SMS, Telegram message, phone call, or flash call
     */
    public static class CheckAuthenticationCode extends Function {

        public String code;

        public CheckAuthenticationCode() {}

        public CheckAuthenticationCode(String code) {

            this.code = code;

        }

        @Override
        public int getConstructor() { return -302103382; }

    }


    /**
     * Requests QR code authentication by scanning a QR code on another logged in device
     * Works only when the current authorization state is authorizationStateWaitPhoneNumber, or if there is no pending authentication query and the current authorization state is authorizationStateWaitCode, authorizationStateWaitRegistration, or authorizationStateWaitPassword
     *
     * @otherUserIds - List of user identifiers of other users currently using the application
     */
    public static class RequestQrCodeAuthentication extends Function {

        public int[] otherUserIds;

        public RequestQrCodeAuthentication() {}

        public RequestQrCodeAuthentication(int[] otherUserIds) {

            this.otherUserIds = otherUserIds;

        }

        @Override
        public int getConstructor() { return -104224560; }

    }


    /**
     * Finishes user registration
     * Works only when the current authorization state is authorizationStateWaitRegistration
     *
     * @firstName - The first name of the user
     * @lastName - The last name of the user
     */
    public static class RegisterUser extends Function {

        public String firstName;
        public String lastName;

        public RegisterUser() {}

        public RegisterUser(String firstName, String lastName) {

            this.firstName = firstName;
            this.lastName = lastName;

        }

        @Override
        public int getConstructor() { return -109994467; }

    }


    /**
     * Checks the authentication password for correctness
     * Works only when the current authorization state is authorizationStateWaitPassword
     *
     * @password - The password to check
     */
    public static class CheckAuthenticationPassword extends Function {

        public String password;

        public CheckAuthenticationPassword() {}

        public CheckAuthenticationPassword(String password) {

            this.password = password;

        }

        @Override
        public int getConstructor() { return -2025698400; }

    }


    /**
     * Requests to send a password recovery code to an email address that was previously set up
     * Works only when the current authorization state is authorizationStateWaitPassword
     */
    public static class RequestAuthenticationPasswordRecovery extends Function {

        @Override
        public int getConstructor() { return 1393896118; }

    }


    /**
     * Recovers the password with a password recovery code sent to an email address that was previously set up
     * Works only when the current authorization state is authorizationStateWaitPassword
     *
     * @recoveryCode - Recovery code to check
     */
    public static class RecoverAuthenticationPassword extends Function {

        public String recoveryCode;

        public RecoverAuthenticationPassword() {}

        public RecoverAuthenticationPassword(String recoveryCode) {

            this.recoveryCode = recoveryCode;

        }

        @Override
        public int getConstructor() { return 787436412; }

    }


    /**
     * Checks the authentication token of a bot
     * To log in as a bot
     * Works only when the current authorization state is authorizationStateWaitPhoneNumber
     * Can be used instead of setAuthenticationPhoneNumber and checkAuthenticationCode to log in
     *
     * @token - The bot token
     */
    public static class CheckAuthenticationBotToken extends Function {

        public String token;

        public CheckAuthenticationBotToken() {}

        public CheckAuthenticationBotToken(String token) {

            this.token = token;

        }

        @Override
        public int getConstructor() { return 639321206; }

    }


    /**
     * Closes the TDLib instance after a proper logout
     * Requires an available network connection
     * All local data will be destroyed
     * After the logout completes, updateAuthorizationState with authorizationStateClosed will be sent
     */
    public static class LogOut extends Function {

        @Override
        public int getConstructor() { return -1581923301; }

    }


    /**
     * Closes the TDLib instance
     * All databases will be flushed to disk and properly closed
     * After the close completes, updateAuthorizationState with authorizationStateClosed will be sent
     * Can be called before initialization
     */
    public static class Close extends Function {

        @Override
        public int getConstructor() { return -1187782273; }

    }


    /**
     * Closes the TDLib instance, destroying all local data without a proper logout
     * The current user session will remain in the list of all active sessions
     * All local data will be destroyed
     * After the destruction completes updateAuthorizationState with authorizationStateClosed will be sent
     * Can be called before authorization
     */
    public static class Destroy extends Function {

        @Override
        public int getConstructor() { return 685331274; }

    }


    /**
     * Confirms QR code authentication on another device
     * Returns created session on success
     *
     * @link - A link from a QR code
     *         The link must be scanned by the in-app camera
     */
    public static class ConfirmQrCodeAuthentication extends Function {

        public String link;

        public ConfirmQrCodeAuthentication() {}

        public ConfirmQrCodeAuthentication(String link) {

            this.link = link;

        }

        @Override
        public int getConstructor() { return -376199379; }

    }


    /**
     * Returns all updates needed to restore current TDLib state, i.e
     * All actual UpdateAuthorizationState/UpdateUser/UpdateNewChat and others
     * This is especially useful if TDLib is run in a separate process
     * Can be called before initialization
     */
    public static class GetCurrentState extends Function {

        @Override
        public int getConstructor() { return -1191417719; }

    }


    /**
     * Changes the database encryption key
     * Usually the encryption key is never changed and is stored in some OS keychain
     *
     * @newEncryptionKey - New encryption key
     */
    public static class SetDatabaseEncryptionKey extends Function {

        public byte[] newEncryptionKey;

        public SetDatabaseEncryptionKey() {}

        public SetDatabaseEncryptionKey(byte[] newEncryptionKey) {

            this.newEncryptionKey = newEncryptionKey;

        }

        @Override
        public int getConstructor() { return -1204599371; }

    }


    /**
     * Returns the current state of 2-step verification
     */
    public static class GetPasswordState extends Function {

        @Override
        public int getConstructor() { return -174752904; }

    }


    /**
     * Changes the password for the user
     * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
     *
     * @oldPassword - Previous password of the user
     * @newPassword - New password of the user
     *                May be empty to remove the password
     * @newHint - New password hint
     * @setRecoveryEmailAddress - Pass true if the recovery email address should be changed
     * @newRecoveryEmailAddress - New recovery email address
     */
    public static class SetPassword extends Function {

        public String oldPassword;
        public String newPassword;
        @Nullable public String newHint;
        public boolean setRecoveryEmailAddress;
        @Nullable public String newRecoveryEmailAddress;

        public SetPassword() {}

        public SetPassword(String oldPassword, String newPassword, @Nullable String newHint, boolean setRecoveryEmailAddress, @Nullable String newRecoveryEmailAddress) {

            this.oldPassword = oldPassword;
            this.newPassword = newPassword;
            this.newHint = newHint;
            this.setRecoveryEmailAddress = setRecoveryEmailAddress;
            this.newRecoveryEmailAddress = newRecoveryEmailAddress;

        }

        @Override
        public int getConstructor() { return -1193589027; }

    }


    /**
     * Returns a 2-step verification recovery email address that was previously set up
     * This method can be used to verify a password provided by the user
     *
     * @password - The password for the current user
     */
    public static class GetRecoveryEmailAddress extends Function {

        public String password;

        public GetRecoveryEmailAddress() {}

        public GetRecoveryEmailAddress(String password) {

            this.password = password;

        }

        @Override
        public int getConstructor() { return -1594770947; }

    }


    /**
     * Changes the 2-step verification recovery email address of the user
     * If a new recovery email address is specified, then the change will not be applied until the new recovery email address is confirmed
     * If new_recovery_email_address is the same as the email address that is currently set up, this call succeeds immediately and aborts all other requests waiting for an email confirmation
     *
     * @password - Password of the current user
     * @newRecoveryEmailAddress - New recovery email address
     */
    public static class SetRecoveryEmailAddress extends Function {

        public String password;
        public String newRecoveryEmailAddress;

        public SetRecoveryEmailAddress() {}

        public SetRecoveryEmailAddress(String password, String newRecoveryEmailAddress) {

            this.password = password;
            this.newRecoveryEmailAddress = newRecoveryEmailAddress;

        }

        @Override
        public int getConstructor() { return -1981836385; }

    }


    /**
     * Checks the 2-step verification recovery email address verification code
     *
     * @code - Verification code
     */
    public static class CheckRecoveryEmailAddressCode extends Function {

        public String code;

        public CheckRecoveryEmailAddressCode() {}

        public CheckRecoveryEmailAddressCode(String code) {

            this.code = code;

        }

        @Override
        public int getConstructor() { return -1997039589; }

    }


    /**
     * Resends the 2-step verification recovery email address verification code
     */
    public static class ResendRecoveryEmailAddressCode extends Function {

        @Override
        public int getConstructor() { return 433483548; }

    }


    /**
     * Requests to send a password recovery code to an email address that was previously set up
     */
    public static class RequestPasswordRecovery extends Function {

        @Override
        public int getConstructor() { return -13777582; }

    }


    /**
     * Recovers the password using a recovery code sent to an email address that was previously set up
     *
     * @recoveryCode - Recovery code to check
     */
    public static class RecoverPassword extends Function {

        public String recoveryCode;

        public RecoverPassword() {}

        public RecoverPassword(String recoveryCode) {

            this.recoveryCode = recoveryCode;

        }

        @Override
        public int getConstructor() { return 1660185903; }

    }


    /**
     * Creates a new temporary password for processing payments
     *
     * @password - Persistent user password
     * @validFor - Time during which the temporary password will be valid, in seconds
     *             Should be between 60 and 86400
     */
    public static class CreateTemporaryPassword extends Function {

        public String password;
        public int validFor;

        public CreateTemporaryPassword() {}

        public CreateTemporaryPassword(String password, int validFor) {

            this.password = password;
            this.validFor = validFor;

        }

        @Override
        public int getConstructor() { return -1626509434; }

    }


    /**
     * Returns information about the current temporary password
     */
    public static class GetTemporaryPasswordState extends Function {

        @Override
        public int getConstructor() { return -12670830; }

    }


    /**
     * Returns the current user
     */
    public static class GetMe extends Function {

        @Override
        public int getConstructor() { return -191516033; }

    }


    /**
     * Returns information about a user by their identifier
     * This is an offline request if the current user is not a bot
     *
     * @userId - User identifier
     */
    public static class GetUser extends Function {

        public int userId;

        public GetUser() {}

        public GetUser(int userId) {

            this.userId = userId;

        }

        @Override
        public int getConstructor() { return -47586017; }

    }


    /**
     * Returns full information about a user by their identifier
     *
     * @userId - User identifier
     */
    public static class GetUserFullInfo extends Function {

        public int userId;

        public GetUserFullInfo() {}

        public GetUserFullInfo(int userId) {

            this.userId = userId;

        }

        @Override
        public int getConstructor() { return -655443263; }

    }


    /**
     * Returns information about a basic group by its identifier
     * This is an offline request if the current user is not a bot
     *
     * @basicGroupId - Basic group identifier
     */
    public static class GetBasicGroup extends Function {

        public int basicGroupId;

        public GetBasicGroup() {}

        public GetBasicGroup(int basicGroupId) {

            this.basicGroupId = basicGroupId;

        }

        @Override
        public int getConstructor() { return 561775568; }

    }


    /**
     * Returns full information about a basic group by its identifier
     *
     * @basicGroupId - Basic group identifier
     */
    public static class GetBasicGroupFullInfo extends Function {

        public int basicGroupId;

        public GetBasicGroupFullInfo() {}

        public GetBasicGroupFullInfo(int basicGroupId) {

            this.basicGroupId = basicGroupId;

        }

        @Override
        public int getConstructor() { return 1770517905; }

    }


    /**
     * Returns information about a supergroup or a channel by its identifier
     * This is an offline request if the current user is not a bot
     *
     * @supergroupId - Supergroup or channel identifier
     */
    public static class GetSupergroup extends Function {

        public int supergroupId;

        public GetSupergroup() {}

        public GetSupergroup(int supergroupId) {

            this.supergroupId = supergroupId;

        }

        @Override
        public int getConstructor() { return -2063063706; }

    }


    /**
     * Returns full information about a supergroup or a channel by its identifier, cached for up to 1 minute
     *
     * @supergroupId - Supergroup or channel identifier
     */
    public static class GetSupergroupFullInfo extends Function {

        public int supergroupId;

        public GetSupergroupFullInfo() {}

        public GetSupergroupFullInfo(int supergroupId) {

            this.supergroupId = supergroupId;

        }

        @Override
        public int getConstructor() { return -1150331262; }

    }


    /**
     * Returns information about a secret chat by its identifier
     * This is an offline request
     *
     * @secretChatId - Secret chat identifier
     */
    public static class GetSecretChat extends Function {

        public int secretChatId;

        public GetSecretChat() {}

        public GetSecretChat(int secretChatId) {

            this.secretChatId = secretChatId;

        }

        @Override
        public int getConstructor() { return 40599169; }

    }


    /**
     * Returns information about a chat by its identifier, this is an offline request if the current user is not a bot
     *
     * @chatId - Chat identifier
     */
    public static class GetChat extends Function {

        public long chatId;

        public GetChat() {}

        public GetChat(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 1866601536; }

    }


    /**
     * Returns information about a message
     *
     * @chatId - Identifier of the chat the message belongs to
     * @messageId - Identifier of the message to get
     */
    public static class GetMessage extends Function {

        public long chatId;
        public long messageId;

        public GetMessage() {}

        public GetMessage(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return -1821196160; }

    }


    /**
     * Returns information about a message, if it is available locally without sending network request
     * This is an offline request
     *
     * @chatId - Identifier of the chat the message belongs to
     * @messageId - Identifier of the message to get
     */
    public static class GetMessageLocally extends Function {

        public long chatId;
        public long messageId;

        public GetMessageLocally() {}

        public GetMessageLocally(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return -603575444; }

    }


    /**
     * Returns information about a message that is replied by a given message
     * Also returns the pinned message, the game message, and the invoice message for messages of the types messagePinMessage, messageGameScore, and messagePaymentSuccessful respectively
     *
     * @chatId - Identifier of the chat the message belongs to
     * @messageId - Identifier of the message reply to which to get
     */
    public static class GetRepliedMessage extends Function {

        public long chatId;
        public long messageId;

        public GetRepliedMessage() {}

        public GetRepliedMessage(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return -641918531; }

    }


    /**
     * Returns information about a pinned chat message
     *
     * @chatId - Identifier of the chat the message belongs to
     */
    public static class GetChatPinnedMessage extends Function {

        public long chatId;

        public GetChatPinnedMessage() {}

        public GetChatPinnedMessage(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 359865008; }

    }


    /**
     * Returns information about messages
     * If a message is not found, returns null on the corresponding position of the result
     *
     * @chatId - Identifier of the chat the messages belong to
     * @messageIds - Identifiers of the messages to get
     */
    public static class GetMessages extends Function {

        public long chatId;
        public long[] messageIds;

        public GetMessages() {}

        public GetMessages(long chatId, long[] messageIds) {

            this.chatId = chatId;
            this.messageIds = messageIds;

        }

        @Override
        public int getConstructor() { return 425299338; }

    }


    /**
     * Returns information about a message thread
     * Can be used only if message.can_get_message_thread == true
     *
     * @chatId - Chat identifier
     * @messageId - Identifier of the message
     */
    public static class GetMessageThread extends Function {

        public long chatId;
        public long messageId;

        public GetMessageThread() {}

        public GetMessageThread(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return 2062695998; }

    }


    /**
     * Returns information about a file
     * This is an offline request
     *
     * @fileId - Identifier of the file to get
     */
    public static class GetFile extends Function {

        public int fileId;

        public GetFile() {}

        public GetFile(int fileId) {

            this.fileId = fileId;

        }

        @Override
        public int getConstructor() { return 1553923406; }

    }


    /**
     * Returns information about a file by its remote ID
     * This is an offline request
     * Can be used to register a URL as a file for further uploading, or sending as a message
     * Even the request succeeds, the file can be used only if it is still accessible to the user
     * For example, if the file is from a message, then the message must be not deleted and accessible to the user
     * If the file database is disabled, then the corresponding object with the file must be preloaded by the application
     *
     * @remoteFileId - Remote identifier of the file to get
     * @fileType - File type, if known
     */
    public static class GetRemoteFile extends Function {

        public String remoteFileId;
        public FileType fileType;

        public GetRemoteFile() {}

        public GetRemoteFile(String remoteFileId, FileType fileType) {

            this.remoteFileId = remoteFileId;
            this.fileType = fileType;

        }

        @Override
        public int getConstructor() { return 2137204530; }

    }


    /**
     * Returns an ordered list of chats in a chat list
     * Chats are sorted by the pair (chat.position.order, chat.id) in descending order
     * (For example, to get a list of chats from the beginning, the offset_order should be equal to a biggest signed 64-bit number 9223372036854775807 == 2^63 - 1)
     * For optimal performance the number of returned chats is chosen by the library
     *
     * @chatList - The chat list in which to return chats
     * @offsetOrder - Chat order to return chats from
     * @offsetChatId - Chat identifier to return chats from
     * @limit - The maximum number of chats to be returned
     *          It is possible that fewer chats than the limit are returned even if the end of the list is not reached
     */
    public static class GetChats extends Function {

        public ChatList chatList;
        public long offsetOrder;
        public long offsetChatId;
        public int limit;

        public GetChats() {}

        public GetChats(ChatList chatList, long offsetOrder, long offsetChatId, int limit) {

            this.chatList = chatList;
            this.offsetOrder = offsetOrder;
            this.offsetChatId = offsetChatId;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return 1847129537; }

    }


    /**
     * Searches a public chat by its username
     * Currently only private chats, supergroups and channels can be public
     * Returns the chat if found
     * Otherwise an error is returned
     *
     * @username - Username to be resolved
     */
    public static class SearchPublicChat extends Function {

        public String username;

        public SearchPublicChat() {}

        public SearchPublicChat(String username) {

            this.username = username;

        }

        @Override
        public int getConstructor() { return 857135533; }

    }


    /**
     * Searches public chats by looking for specified query in their username and title
     * Currently only private chats, supergroups and channels can be public
     * Returns a meaningful number of results
     * Returns nothing if the length of the searched username prefix is less than 5
     * Excludes private chats with contacts and chats from the chat list from the results
     *
     * @query - Query to search for
     */
    public static class SearchPublicChats extends Function {

        public String query;

        public SearchPublicChats() {}

        public SearchPublicChats(String query) {

            this.query = query;

        }

        @Override
        public int getConstructor() { return 970385337; }

    }


    /**
     * Searches for the specified query in the title and username of already known chats, this is an offline request
     * Returns chats in the order seen in the main chat list
     *
     * @query - Query to search for
     *          If the query is empty, returns up to 20 recently found chats
     * @limit - The maximum number of chats to be returned
     */
    public static class SearchChats extends Function {

        public String query;
        public int limit;

        public SearchChats() {}

        public SearchChats(String query, int limit) {

            this.query = query;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -1879787060; }

    }


    /**
     * Searches for the specified query in the title and username of already known chats via request to the server
     * Returns chats in the order seen in the main chat list
     *
     * @query - Query to search for
     * @limit - The maximum number of chats to be returned
     */
    public static class SearchChatsOnServer extends Function {

        public String query;
        public int limit;

        public SearchChatsOnServer() {}

        public SearchChatsOnServer(String query, int limit) {

            this.query = query;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -1158402188; }

    }


    /**
     * Returns a list of users and location-based supergroups nearby
     * The list of users nearby will be updated for 60 seconds after the request by the updates updateUsersNearby
     * The request should be sent again every 25 seconds with adjusted location to not miss new chats
     *
     * @location - Current user location
     */
    public static class SearchChatsNearby extends Function {

        public Location location;

        public SearchChatsNearby() {}

        public SearchChatsNearby(Location location) {

            this.location = location;

        }

        @Override
        public int getConstructor() { return -196753377; }

    }


    /**
     * Returns a list of frequently used chats
     * Supported only if the chat info database is enabled
     *
     * @category - Category of chats to be returned
     * @limit - The maximum number of chats to be returned
     *          Up to 30
     */
    public static class GetTopChats extends Function {

        public TopChatCategory category;
        public int limit;

        public GetTopChats() {}

        public GetTopChats(TopChatCategory category, int limit) {

            this.category = category;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -388410847; }

    }


    /**
     * Removes a chat from the list of frequently used chats
     * Supported only if the chat info database is enabled
     *
     * @category - Category of frequently used chats
     * @chatId - Chat identifier
     */
    public static class RemoveTopChat extends Function {

        public TopChatCategory category;
        public long chatId;

        public RemoveTopChat() {}

        public RemoveTopChat(TopChatCategory category, long chatId) {

            this.category = category;
            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return -1907876267; }

    }


    /**
     * Adds a chat to the list of recently found chats
     * The chat is added to the beginning of the list
     * If the chat is already in the list, it will be removed from the list first
     *
     * @chatId - Identifier of the chat to add
     */
    public static class AddRecentlyFoundChat extends Function {

        public long chatId;

        public AddRecentlyFoundChat() {}

        public AddRecentlyFoundChat(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return -1746396787; }

    }


    /**
     * Removes a chat from the list of recently found chats
     *
     * @chatId - Identifier of the chat to be removed
     */
    public static class RemoveRecentlyFoundChat extends Function {

        public long chatId;

        public RemoveRecentlyFoundChat() {}

        public RemoveRecentlyFoundChat(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 717340444; }

    }


    /**
     * Clears the list of recently found chats
     */
    public static class ClearRecentlyFoundChats extends Function {

        @Override
        public int getConstructor() { return -285582542; }

    }


    /**
     * Checks whether a username can be set for a chat
     *
     * @chatId - Chat identifier
     *           Should be identifier of a supergroup chat, or a channel chat, or a private chat with self, or zero if chat is being created
     * @username - Username to be checked
     */
    public static class CheckChatUsername extends Function {

        public long chatId;
        public String username;

        public CheckChatUsername() {}

        public CheckChatUsername(long chatId, String username) {

            this.chatId = chatId;
            this.username = username;

        }

        @Override
        public int getConstructor() { return -119119344; }

    }


    /**
     * Returns a list of public chats of the specified type, owned by the user
     *
     * @type - Type of the public chats to return
     */
    public static class GetCreatedPublicChats extends Function {

        public PublicChatType type;

        public GetCreatedPublicChats() {}

        public GetCreatedPublicChats(PublicChatType type) {

            this.type = type;

        }

        @Override
        public int getConstructor() { return 710354415; }

    }


    /**
     * Checks whether the maximum number of owned public chats has been reached
     * Returns corresponding error if the limit was reached
     *
     * @type - Type of the public chats, for which to check the limit
     */
    public static class CheckCreatedPublicChatsLimit extends Function {

        public PublicChatType type;

        public CheckCreatedPublicChatsLimit() {}

        public CheckCreatedPublicChatsLimit(PublicChatType type) {

            this.type = type;

        }

        @Override
        public int getConstructor() { return -445546591; }

    }


    /**
     * Returns a list of basic group and supergroup chats, which can be used as a discussion group for a channel
     * Basic group chats need to be first upgraded to supergroups before they can be set as a discussion group
     */
    public static class GetSuitableDiscussionChats extends Function {

        @Override
        public int getConstructor() { return 49044982; }

    }


    /**
     * Returns a list of recently inactive supergroups and channels
     * Can be used when user reaches limit on the number of joined supergroups and channels and receives CHANNELS_TOO_MUCH error
     */
    public static class GetInactiveSupergroupChats extends Function {

        @Override
        public int getConstructor() { return -657720907; }

    }


    /**
     * Returns a list of common group chats with a given user
     * Chats are sorted by their type and creation date
     *
     * @userId - User identifier
     * @offsetChatId - Chat identifier starting from which to return chats
     *                 Use 0 for the first request
     * @limit - The maximum number of chats to be returned
     */
    public static class GetGroupsInCommon extends Function {

        public int userId;
        public long offsetChatId;
        public int limit;

        public GetGroupsInCommon() {}

        public GetGroupsInCommon(int userId, long offsetChatId, int limit) {

            this.userId = userId;
            this.offsetChatId = offsetChatId;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -23238689; }

    }


    /**
     * Returns messages in a chat
     * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
     * For optimal performance the number of returned messages is chosen by the library
     * This is an offline request if only_local is true
     *
     * @chatId - Chat identifier
     * @fromMessageId - Identifier of the message starting from which history must be fetched
     *                  Use 0 to get results from the last message
     * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset up to 99 to get additionally some newer messages
     * @limit - The maximum number of messages to be returned
     *          Must be positive and can't be greater than 100
     *          If the offset is negative, the limit must be greater than or equal to -offset
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     * @onlyLocal - If true, returns only messages that are available locally without sending network requests
     */
    public static class GetChatHistory extends Function {

        public long chatId;
        public long fromMessageId;
        public int offset;
        public int limit;
        public boolean onlyLocal;

        public GetChatHistory() {}

        public GetChatHistory(long chatId, long fromMessageId, int offset, int limit, boolean onlyLocal) {

            this.chatId = chatId;
            this.fromMessageId = fromMessageId;
            this.offset = offset;
            this.limit = limit;
            this.onlyLocal = onlyLocal;

        }

        @Override
        public int getConstructor() { return -799960451; }

    }


    /**
     * Returns messages in a message thread of a message
     * Can be used only if message.can_get_message_thread == true
     * Message thread of a channel message is in the channel's linked supergroup
     * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
     * For optimal performance the number of returned messages is chosen by the library
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier, which thread history needs to be returned
     * @fromMessageId - Identifier of the message starting from which history must be fetched
     *                  Use 0 to get results from the last message
     * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset up to 99 to get additionally some newer messages
     * @limit - The maximum number of messages to be returned
     *          Must be positive and can't be greater than 100
     *          If the offset is negative, the limit must be greater than or equal to -offset
     *          Fewer messages may be returned than specified by the limit, even if the end of the message thread history has not been reached
     */
    public static class GetMessageThreadHistory extends Function {

        public long chatId;
        public long messageId;
        public long fromMessageId;
        public int offset;
        public int limit;

        public GetMessageThreadHistory() {}

        public GetMessageThreadHistory(long chatId, long messageId, long fromMessageId, int offset, int limit) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.fromMessageId = fromMessageId;
            this.offset = offset;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -1808411608; }

    }


    /**
     * Deletes all messages in the chat
     * Use Chat.can_be_deleted_only_for_self and Chat.can_be_deleted_for_all_users fields to find whether and how the method can be applied to the chat
     *
     * @chatId - Chat identifier
     * @removeFromChatList - Pass true if the chat should be removed from the chat list
     * @revoke - Pass true to try to delete chat history for all users
     */
    public static class DeleteChatHistory extends Function {

        public long chatId;
        public boolean removeFromChatList;
        public boolean revoke;

        public DeleteChatHistory() {}

        public DeleteChatHistory(long chatId, boolean removeFromChatList, boolean revoke) {

            this.chatId = chatId;
            this.removeFromChatList = removeFromChatList;
            this.revoke = revoke;

        }

        @Override
        public int getConstructor() { return -1472081761; }

    }


    /**
     * Searches for messages with given words in the chat
     * Returns the results in reverse chronological order, i.e
     * In order of decreasing message_id
     * Cannot be used in secret chats with a non-empty query (searchSecretMessages should be used instead), or without an enabled message database
     * For optimal performance the number of returned messages is chosen by the library
     *
     * @chatId - Identifier of the chat in which to search messages
     * @query - Query to search for
     * @senderUserId - If not 0, only messages sent by the specified user will be returned
     *                 Not supported in secret chats
     * @fromMessageId - Identifier of the message starting from which history must be fetched
     *                  Use 0 to get results from the last message
     * @offset - Specify 0 to get results from exactly the from_message_id or a negative offset to get the specified message and some newer messages
     * @limit - The maximum number of messages to be returned
     *          Must be positive and can't be greater than 100
     *          If the offset is negative, the limit must be greater than -offset
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     * @filter - Filter for message content in the search results
     * @messageThreadId - If not 0, only messages in the specified thread will be returned
     *                    Supergroups only
     */
    public static class SearchChatMessages extends Function {

        public long chatId;
        public String query;
        public int senderUserId;
        public long fromMessageId;
        public int offset;
        public int limit;
        public SearchMessagesFilter filter;
        public long messageThreadId;

        public SearchChatMessages() {}

        public SearchChatMessages(long chatId, String query, int senderUserId, long fromMessageId, int offset, int limit, SearchMessagesFilter filter, long messageThreadId) {

            this.chatId = chatId;
            this.query = query;
            this.senderUserId = senderUserId;
            this.fromMessageId = fromMessageId;
            this.offset = offset;
            this.limit = limit;
            this.filter = filter;
            this.messageThreadId = messageThreadId;

        }

        @Override
        public int getConstructor() { return 1445943052; }

    }


    /**
     * Searches for messages in all chats except secret chats
     * Returns the results in reverse chronological order (i.e., in order of decreasing (date, chat_id, message_id))
     * For optimal performance the number of returned messages is chosen by the library
     *
     * @chatList - Chat list in which to search messages
     *             Pass null to search in all chats regardless of their chat list
     * @query - Query to search for
     * @offsetDate - The date of the message starting from which the results should be fetched
     *               Use 0 or any date in the future to get results from the last message
     * @offsetChatId - The chat identifier of the last found message, or 0 for the first request
     * @offsetMessageId - The message identifier of the last found message, or 0 for the first request
     * @limit - The maximum number of messages to be returned
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     * @filter - Filter for message content in the search results
     *           SearchMessagesFilterCall, searchMessagesFilterMissedCall, searchMessagesFilterMention, searchMessagesFilterUnreadMention and searchMessagesFilterFailedToSend are unsupported in this function
     * @minDate - If not 0, the minimum date of the messages to return
     * @maxDate - If not 0, the maximum date of the messages to return
     */
    public static class SearchMessages extends Function {

        public ChatList chatList;
        public String query;
        public int offsetDate;
        public long offsetChatId;
        public long offsetMessageId;
        public int limit;
        public SearchMessagesFilter filter;
        public int minDate;
        public int maxDate;

        public SearchMessages() {}

        public SearchMessages(ChatList chatList, String query, int offsetDate, long offsetChatId, long offsetMessageId, int limit, SearchMessagesFilter filter, int minDate, int maxDate) {

            this.chatList = chatList;
            this.query = query;
            this.offsetDate = offsetDate;
            this.offsetChatId = offsetChatId;
            this.offsetMessageId = offsetMessageId;
            this.limit = limit;
            this.filter = filter;
            this.minDate = minDate;
            this.maxDate = maxDate;

        }

        @Override
        public int getConstructor() { return -225214062; }

    }


    /**
     * Searches for messages in secret chats
     * Returns the results in reverse chronological order
     * For optimal performance the number of returned messages is chosen by the library
     *
     * @chatId - Identifier of the chat in which to search
     *           Specify 0 to search in all secret chats
     * @query - Query to search for
     *          If empty, searchChatMessages should be used instead
     * @offset - Offset of the first entry to return as received from the previous request
     *           Use empty string to get first chunk of results
     * @limit - The maximum number of messages to be returned
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     * @filter - A filter for message content in the search results
     */
    public static class SearchSecretMessages extends Function {

        public long chatId;
        public String query;
        public String offset;
        public int limit;
        public SearchMessagesFilter filter;

        public SearchSecretMessages() {}

        public SearchSecretMessages(long chatId, String query, String offset, int limit, SearchMessagesFilter filter) {

            this.chatId = chatId;
            this.query = query;
            this.offset = offset;
            this.limit = limit;
            this.filter = filter;

        }

        @Override
        public int getConstructor() { return -852865892; }

    }


    /**
     * Searches for call messages
     * Returns the results in reverse chronological order (i
     * E., in order of decreasing message_id)
     * For optimal performance the number of returned messages is chosen by the library
     *
     * @fromMessageId - Identifier of the message from which to search
     *                  Use 0 to get results from the last message
     * @limit - The maximum number of messages to be returned
     *          Fewer messages may be returned than specified by the limit, even if the end of the message history has not been reached
     * @onlyMissed - If true, returns only messages with missed calls
     */
    public static class SearchCallMessages extends Function {

        public long fromMessageId;
        public int limit;
        public boolean onlyMissed;

        public SearchCallMessages() {}

        public SearchCallMessages(long fromMessageId, int limit, boolean onlyMissed) {

            this.fromMessageId = fromMessageId;
            this.limit = limit;
            this.onlyMissed = onlyMissed;

        }

        @Override
        public int getConstructor() { return -1077230820; }

    }


    /**
     * Returns information about the recent locations of chat members that were sent to the chat
     * Returns up to 1 location message per user
     *
     * @chatId - Chat identifier
     * @limit - The maximum number of messages to be returned
     */
    public static class SearchChatRecentLocationMessages extends Function {

        public long chatId;
        public int limit;

        public SearchChatRecentLocationMessages() {}

        public SearchChatRecentLocationMessages(long chatId, int limit) {

            this.chatId = chatId;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return 950238950; }

    }


    /**
     * Returns all active live locations that should be updated by the application
     * The list is persistent across application restarts only if the message database is used
     */
    public static class GetActiveLiveLocationMessages extends Function {

        @Override
        public int getConstructor() { return -1425459567; }

    }


    /**
     * Returns the last message sent in a chat no later than the specified date
     *
     * @chatId - Chat identifier
     * @date - Point in time (Unix timestamp) relative to which to search for messages
     */
    public static class GetChatMessageByDate extends Function {

        public long chatId;
        public int date;

        public GetChatMessageByDate() {}

        public GetChatMessageByDate(long chatId, int date) {

            this.chatId = chatId;
            this.date = date;

        }

        @Override
        public int getConstructor() { return 1062564150; }

    }


    /**
     * Returns approximate number of messages of the specified type in the chat
     *
     * @chatId - Identifier of the chat in which to count messages
     * @filter - Filter for message content
     *           SearchMessagesFilterEmpty is unsupported in this function
     * @returnLocal - If true, returns count that is available locally without sending network requests, returning -1 if the number of messages is unknown
     */
    public static class GetChatMessageCount extends Function {

        public long chatId;
        public SearchMessagesFilter filter;
        public boolean returnLocal;

        public GetChatMessageCount() {}

        public GetChatMessageCount(long chatId, SearchMessagesFilter filter, boolean returnLocal) {

            this.chatId = chatId;
            this.filter = filter;
            this.returnLocal = returnLocal;

        }

        @Override
        public int getConstructor() { return 205435308; }

    }


    /**
     * Returns all scheduled messages in a chat
     * The messages are returned in a reverse chronological order (i.e., in order of decreasing message_id)
     *
     * @chatId - Chat identifier
     */
    public static class GetChatScheduledMessages extends Function {

        public long chatId;

        public GetChatScheduledMessages() {}

        public GetChatScheduledMessages(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return -549638149; }

    }


    /**
     * Returns forwarded copies of a channel message to another public channels
     * For optimal performance the number of returned messages is chosen by the library
     * The method is under development and may or may not work
     *
     * @chatId - Chat identifier of the message
     * @messageId - Message identifier
     * @offset - Offset of the first entry to return as received from the previous request
     *           Use empty string to get first chunk of results
     * @limit - The maximum number of messages to be returned
     *          Must be positive and can't be greater than 100
     *          Fewer messages may be returned than specified by the limit, even if the end of the list has not been reached
     */
    public static class GetMessagePublicForwards extends Function {

        public long chatId;
        public long messageId;
        public String offset;
        public int limit;

        public GetMessagePublicForwards() {}

        public GetMessagePublicForwards(long chatId, long messageId, String offset, int limit) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.offset = offset;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return 1611049289; }

    }


    /**
     * Removes an active notification from notification list
     * Needs to be called only if the notification is removed by the current user
     *
     * @notificationGroupId - Identifier of notification group to which the notification belongs
     * @notificationId - Identifier of removed notification
     */
    public static class RemoveNotification extends Function {

        public int notificationGroupId;
        public int notificationId;

        public RemoveNotification() {}

        public RemoveNotification(int notificationGroupId, int notificationId) {

            this.notificationGroupId = notificationGroupId;
            this.notificationId = notificationId;

        }

        @Override
        public int getConstructor() { return 862630734; }

    }


    /**
     * Removes a group of active notifications
     * Needs to be called only if the notification group is removed by the current user
     *
     * @notificationGroupId - Notification group identifier
     * @maxNotificationId - The maximum identifier of removed notifications
     */
    public static class RemoveNotificationGroup extends Function {

        public int notificationGroupId;
        public int maxNotificationId;

        public RemoveNotificationGroup() {}

        public RemoveNotificationGroup(int notificationGroupId, int maxNotificationId) {

            this.notificationGroupId = notificationGroupId;
            this.maxNotificationId = maxNotificationId;

        }

        @Override
        public int getConstructor() { return 1713005454; }

    }


    /**
     * Returns an HTTPS link to a message in a chat
     * Available only for already sent messages in supergroups and channels
     * This is an offline request
     *
     * @chatId - Identifier of the chat to which the message belongs
     * @messageId - Identifier of the message
     * @forAlbum - Pass true to create a link for the whole media album
     * @forComment - Pass true to create a link to the message as a channel post comment, or from a message thread
     */
    public static class GetMessageLink extends Function {

        public long chatId;
        public long messageId;
        public boolean forAlbum;
        public boolean forComment;

        public GetMessageLink() {}

        public GetMessageLink(long chatId, long messageId, boolean forAlbum, boolean forComment) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.forAlbum = forAlbum;
            this.forComment = forComment;

        }

        @Override
        public int getConstructor() { return -177667137; }

    }


    /**
     * Returns an HTML code for embedding the message
     * Available only for messages in supergroups and channels with a username
     *
     * @chatId - Identifier of the chat to which the message belongs
     * @messageId - Identifier of the message
     * @forAlbum - Pass true to return an HTML code for embedding of the whole media album
     */
    public static class GetMessageEmbeddingCode extends Function {

        public long chatId;
        public long messageId;
        public boolean forAlbum;

        public GetMessageEmbeddingCode() {}

        public GetMessageEmbeddingCode(long chatId, long messageId, boolean forAlbum) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.forAlbum = forAlbum;

        }

        @Override
        public int getConstructor() { return 1654967561; }

    }


    /**
     * Returns information about a public or private message link
     *
     * @url - The message link in the format "https://t.me/c/...", or "tg://privatepost?...", or "https://t.me/username/...", or "tg://resolve?..."
     */
    public static class GetMessageLinkInfo extends Function {

        public String url;

        public GetMessageLinkInfo() {}

        public GetMessageLinkInfo(String url) {

            this.url = url;

        }

        @Override
        public int getConstructor() { return -700533672; }

    }


    /**
     * Sends a message
     * Returns the sent message
     *
     * @chatId - Target chat
     * @messageThreadId - If not 0, a message thread identifier in which the message will be sent
     * @replyToMessageId - Identifier of the message to reply to or 0
     * @options - Options to be used to send the message
     * @replyMarkup - Markup for replying to the message
     *                For bots only
     * @inputMessageContent - The content of the message to be sent
     */
    public static class SendMessage extends Function {

        public long chatId;
        public long messageThreadId;
        public long replyToMessageId;
        public MessageSendOptions options;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public SendMessage() {}

        public SendMessage(long chatId, long messageThreadId, long replyToMessageId, MessageSendOptions options, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.chatId = chatId;
            this.messageThreadId = messageThreadId;
            this.replyToMessageId = replyToMessageId;
            this.options = options;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 960453021; }

    }


    /**
     * Sends messages grouped together into an album
     * Currently only photo and video messages can be grouped into an album
     * Returns sent messages
     *
     * @chatId - Target chat
     * @messageThreadId - If not 0, a message thread identifier in which the messages will be sent
     * @replyToMessageId - Identifier of a message to reply to or 0
     * @options - Options to be used to send the messages
     * @inputMessageContents - Contents of messages to be sent
     */
    public static class SendMessageAlbum extends Function {

        public long chatId;
        public long messageThreadId;
        public long replyToMessageId;
        public MessageSendOptions options;
        public InputMessageContent[] inputMessageContents;

        public SendMessageAlbum() {}

        public SendMessageAlbum(long chatId, long messageThreadId, long replyToMessageId, MessageSendOptions options, InputMessageContent[] inputMessageContents) {

            this.chatId = chatId;
            this.messageThreadId = messageThreadId;
            this.replyToMessageId = replyToMessageId;
            this.options = options;
            this.inputMessageContents = inputMessageContents;

        }

        @Override
        public int getConstructor() { return 983360432; }

    }


    /**
     * Invites a bot to a chat (if it is not yet a member) and sends it the /start command
     * Bots can't be invited to a private chat other than the chat with the bot
     * Bots can't be invited to channels (although they can be added as admins) and secret chats
     * Returns the sent message
     *
     * @botUserId - Identifier of the bot
     * @chatId - Identifier of the target chat
     * @parameter - A hidden parameter sent to the bot for deep linking purposes (https://core.telegram.org/bots#deep-linking)
     */
    public static class SendBotStartMessage extends Function {

        public int botUserId;
        public long chatId;
        public String parameter;

        public SendBotStartMessage() {}

        public SendBotStartMessage(int botUserId, long chatId, String parameter) {

            this.botUserId = botUserId;
            this.chatId = chatId;
            this.parameter = parameter;

        }

        @Override
        public int getConstructor() { return 1112181339; }

    }


    /**
     * Sends the result of an inline query as a message
     * Returns the sent message
     * Always clears a chat draft message
     *
     * @chatId - Target chat
     * @messageThreadId - If not 0, a message thread identifier in which the message will be sent
     * @replyToMessageId - Identifier of a message to reply to or 0
     * @options - Options to be used to send the message
     * @queryId - Identifier of the inline query
     * @resultId - Identifier of the inline result
     * @hideViaBot - If true, there will be no mention of a bot, via which the message is sent
     *               Can be used only for bots GetOption("animation_search_bot_username"), GetOption("photo_search_bot_username") and GetOption("venue_search_bot_username")
     */
    public static class SendInlineQueryResultMessage extends Function {

        public long chatId;
        public long messageThreadId;
        public long replyToMessageId;
        public MessageSendOptions options;
        public long queryId;
        public String resultId;
        public boolean hideViaBot;

        public SendInlineQueryResultMessage() {}

        public SendInlineQueryResultMessage(long chatId, long messageThreadId, long replyToMessageId, MessageSendOptions options, long queryId, String resultId, boolean hideViaBot) {

            this.chatId = chatId;
            this.messageThreadId = messageThreadId;
            this.replyToMessageId = replyToMessageId;
            this.options = options;
            this.queryId = queryId;
            this.resultId = resultId;
            this.hideViaBot = hideViaBot;

        }

        @Override
        public int getConstructor() { return -948639588; }

    }


    /**
     * Forwards previously sent messages
     * Returns the forwarded messages in the same order as the message identifiers passed in message_ids
     * If a message can't be forwarded, null will be returned instead of the message
     *
     * @chatId - Identifier of the chat to which to forward messages
     * @fromChatId - Identifier of the chat from which to forward messages
     * @messageIds - Identifiers of the messages to forward
     *               Message identifiers must be in a strictly increasing order
     * @options - Options to be used to send the messages
     * @sendCopy - True, if content of the messages needs to be copied without links to the original messages
     *             Always true if the messages are forwarded to a secret chat
     * @removeCaption - True, if media caption of message copies needs to be removed
     *                  Ignored if send_copy is false
     */
    public static class ForwardMessages extends Function {

        public long chatId;
        public long fromChatId;
        public long[] messageIds;
        public MessageSendOptions options;
        public boolean sendCopy;
        public boolean removeCaption;

        public ForwardMessages() {}

        public ForwardMessages(long chatId, long fromChatId, long[] messageIds, MessageSendOptions options, boolean sendCopy, boolean removeCaption) {

            this.chatId = chatId;
            this.fromChatId = fromChatId;
            this.messageIds = messageIds;
            this.options = options;
            this.sendCopy = sendCopy;
            this.removeCaption = removeCaption;

        }

        @Override
        public int getConstructor() { return 2086130821; }

    }


    /**
     * Resends messages which failed to send
     * Can be called only for messages for which messageSendingStateFailed.can_retry is true and after specified in messageSendingStateFailed.retry_after time passed
     * If a message is re-sent, the corresponding failed to send message is deleted
     * Returns the sent messages in the same order as the message identifiers passed in message_ids
     * If a message can't be re-sent, null will be returned instead of the message
     *
     * @chatId - Identifier of the chat to send messages
     * @messageIds - Identifiers of the messages to resend
     *               Message identifiers must be in a strictly increasing order
     */
    public static class ResendMessages extends Function {

        public long chatId;
        public long[] messageIds;

        public ResendMessages() {}

        public ResendMessages(long chatId, long[] messageIds) {

            this.chatId = chatId;
            this.messageIds = messageIds;

        }

        @Override
        public int getConstructor() { return -940655817; }

    }


    /**
     * Changes the current TTL setting (sets a new self-destruct timer) in a secret chat and sends the corresponding message
     *
     * @chatId - Chat identifier
     * @ttl - New TTL value, in seconds
     */
    public static class SendChatSetTtlMessage extends Function {

        public long chatId;
        public int ttl;

        public SendChatSetTtlMessage() {}

        public SendChatSetTtlMessage(long chatId, int ttl) {

            this.chatId = chatId;
            this.ttl = ttl;

        }

        @Override
        public int getConstructor() { return 1432535564; }

    }


    /**
     * Sends a notification about a screenshot taken in a chat
     * Supported only in private and secret chats
     *
     * @chatId - Chat identifier
     */
    public static class SendChatScreenshotTakenNotification extends Function {

        public long chatId;

        public SendChatScreenshotTakenNotification() {}

        public SendChatScreenshotTakenNotification(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 448399457; }

    }


    /**
     * Adds a local message to a chat
     * The message is persistent across application restarts only if the message database is used
     * Returns the added message
     *
     * @chatId - Target chat
     * @senderUserId - Identifier of the user who will be shown as the sender of the message
     *                 May be 0 for channel posts
     * @replyToMessageId - Identifier of the message to reply to or 0
     * @disableNotification - Pass true to disable notification for the message
     * @inputMessageContent - The content of the message to be added
     */
    public static class AddLocalMessage extends Function {

        public long chatId;
        public int senderUserId;
        public long replyToMessageId;
        public boolean disableNotification;
        public InputMessageContent inputMessageContent;

        public AddLocalMessage() {}

        public AddLocalMessage(long chatId, int senderUserId, long replyToMessageId, boolean disableNotification, InputMessageContent inputMessageContent) {

            this.chatId = chatId;
            this.senderUserId = senderUserId;
            this.replyToMessageId = replyToMessageId;
            this.disableNotification = disableNotification;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return -348943149; }

    }


    /**
     * Deletes messages
     *
     * @chatId - Chat identifier
     * @messageIds - Identifiers of the messages to be deleted
     * @revoke - Pass true to try to delete messages for all chat members
     *           Always true for supergroups, channels and secret chats
     */
    public static class DeleteMessages extends Function {

        public long chatId;
        public long[] messageIds;
        public boolean revoke;

        public DeleteMessages() {}

        public DeleteMessages(long chatId, long[] messageIds, boolean revoke) {

            this.chatId = chatId;
            this.messageIds = messageIds;
            this.revoke = revoke;

        }

        @Override
        public int getConstructor() { return 1130090173; }

    }


    /**
     * Deletes all messages sent by the specified user to a chat
     * Supported only for supergroups
     * Requires can_delete_messages administrator privileges
     *
     * @chatId - Chat identifier
     * @userId - User identifier
     */
    public static class DeleteChatMessagesFromUser extends Function {

        public long chatId;
        public int userId;

        public DeleteChatMessagesFromUser() {}

        public DeleteChatMessagesFromUser(long chatId, int userId) {

            this.chatId = chatId;
            this.userId = userId;

        }

        @Override
        public int getConstructor() { return -1599689199; }

    }


    /**
     * Edits the text of a message (or a text of a game message)
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @inputMessageContent - New text content of the message
     *                        Should be of type InputMessageText
     */
    public static class EditMessageText extends Function {

        public long chatId;
        public long messageId;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public EditMessageText() {}

        public EditMessageText(long chatId, long messageId, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 196272567; }

    }


    /**
     * Edits the message content of a live location
     * Messages can be edited for a limited period of time specified in the live location
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @location - New location content of the message
     *             Pass null to stop sharing the live location
     */
    public static class EditMessageLiveLocation extends Function {

        public long chatId;
        public long messageId;
        public ReplyMarkup replyMarkup;
        @Nullable public Location location;

        public EditMessageLiveLocation() {}

        public EditMessageLiveLocation(long chatId, long messageId, ReplyMarkup replyMarkup, @Nullable Location location) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.replyMarkup = replyMarkup;
            this.location = location;

        }

        @Override
        public int getConstructor() { return -1146772745; }

    }


    /**
     * Edits the content of a message with an animation, an audio, a document, a photo or a video
     * The media in the message can't be replaced if the message was set to self-destruct
     * Media can't be replaced by self-destructing media
     * Media in an album can be edited only to contain a photo or a video
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @inputMessageContent - New content of the message
     *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
     */
    public static class EditMessageMedia extends Function {

        public long chatId;
        public long messageId;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public EditMessageMedia() {}

        public EditMessageMedia(long chatId, long messageId, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return -1152678125; }

    }


    /**
     * Edits the message content caption
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @caption - New message content caption
     *            0-GetOption("message_caption_length_max") characters
     */
    public static class EditMessageCaption extends Function {

        public long chatId;
        public long messageId;
        public ReplyMarkup replyMarkup;
        public FormattedText caption;

        public EditMessageCaption() {}

        public EditMessageCaption(long chatId, long messageId, ReplyMarkup replyMarkup, FormattedText caption) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.replyMarkup = replyMarkup;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return 1154677038; }

    }


    /**
     * Edits the message reply markup
     * For bots only
     * Returns the edited message after the edit is completed on the server side
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @replyMarkup - The new message reply markup
     */
    public static class EditMessageReplyMarkup extends Function {

        public long chatId;
        public long messageId;
        public ReplyMarkup replyMarkup;

        public EditMessageReplyMarkup() {}

        public EditMessageReplyMarkup(long chatId, long messageId, ReplyMarkup replyMarkup) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.replyMarkup = replyMarkup;

        }

        @Override
        public int getConstructor() { return 332127881; }

    }


    /**
     * Edits the text of an inline text or game message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     * @inputMessageContent - New text content of the message
     *                        Should be of type InputMessageText
     */
    public static class EditInlineMessageText extends Function {

        public String inlineMessageId;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public EditInlineMessageText() {}

        public EditInlineMessageText(String inlineMessageId, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.inlineMessageId = inlineMessageId;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return -855457307; }

    }


    /**
     * Edits the content of a live location in an inline message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     * @location - New location content of the message
     *             Pass null to stop sharing the live location
     */
    public static class EditInlineMessageLiveLocation extends Function {

        public String inlineMessageId;
        public ReplyMarkup replyMarkup;
        @Nullable public Location location;

        public EditInlineMessageLiveLocation() {}

        public EditInlineMessageLiveLocation(String inlineMessageId, ReplyMarkup replyMarkup, @Nullable Location location) {

            this.inlineMessageId = inlineMessageId;
            this.replyMarkup = replyMarkup;
            this.location = location;

        }

        @Override
        public int getConstructor() { return 655046316; }

    }


    /**
     * Edits the content of a message with an animation, an audio, a document, a photo or a video in an inline message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     *                For bots only
     * @inputMessageContent - New content of the message
     *                        Must be one of the following types: InputMessageAnimation, InputMessageAudio, InputMessageDocument, InputMessagePhoto or InputMessageVideo
     */
    public static class EditInlineMessageMedia extends Function {

        public String inlineMessageId;
        public ReplyMarkup replyMarkup;
        public InputMessageContent inputMessageContent;

        public EditInlineMessageMedia() {}

        public EditInlineMessageMedia(String inlineMessageId, ReplyMarkup replyMarkup, InputMessageContent inputMessageContent) {

            this.inlineMessageId = inlineMessageId;
            this.replyMarkup = replyMarkup;
            this.inputMessageContent = inputMessageContent;

        }

        @Override
        public int getConstructor() { return 23553921; }

    }


    /**
     * Edits the caption of an inline message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     * @caption - New message content caption
     *            0-GetOption("message_caption_length_max") characters
     */
    public static class EditInlineMessageCaption extends Function {

        public String inlineMessageId;
        public ReplyMarkup replyMarkup;
        public FormattedText caption;

        public EditInlineMessageCaption() {}

        public EditInlineMessageCaption(String inlineMessageId, ReplyMarkup replyMarkup, FormattedText caption) {

            this.inlineMessageId = inlineMessageId;
            this.replyMarkup = replyMarkup;
            this.caption = caption;

        }

        @Override
        public int getConstructor() { return -760985929; }

    }


    /**
     * Edits the reply markup of an inline message sent via a bot
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @replyMarkup - The new message reply markup
     */
    public static class EditInlineMessageReplyMarkup extends Function {

        public String inlineMessageId;
        public ReplyMarkup replyMarkup;

        public EditInlineMessageReplyMarkup() {}

        public EditInlineMessageReplyMarkup(String inlineMessageId, ReplyMarkup replyMarkup) {

            this.inlineMessageId = inlineMessageId;
            this.replyMarkup = replyMarkup;

        }

        @Override
        public int getConstructor() { return -67565858; }

    }


    /**
     * Edits the time when a scheduled message will be sent
     * Scheduling state of all messages in the same album or forwarded together with the message will be also changed
     *
     * @chatId - The chat the message belongs to
     * @messageId - Identifier of the message
     * @schedulingState - The new message scheduling state
     *                    Pass null to send the message immediately
     */
    public static class EditMessageSchedulingState extends Function {

        public long chatId;
        public long messageId;
        public MessageSchedulingState schedulingState;

        public EditMessageSchedulingState() {}

        public EditMessageSchedulingState(long chatId, long messageId, MessageSchedulingState schedulingState) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.schedulingState = schedulingState;

        }

        @Override
        public int getConstructor() { return -1372976192; }

    }


    /**
     * Returns all entities (mentions, hashtags, cashtags, bot commands, bank card numbers, URLs, and email addresses) contained in the text
     * Can be called synchronously
     *
     * @text - The text in which to look for entites
     */
    public static class GetTextEntities extends Function {

        public String text;

        public GetTextEntities() {}

        public GetTextEntities(String text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return -341490693; }

    }


    /**
     * Parses Bold, Italic, Underline, Strikethrough, Code, Pre, PreCode, TextUrl and MentionName entities contained in the text
     * Can be called synchronously
     *
     * @text - The text to parse
     * @parseMode - Text parse mode
     */
    public static class ParseTextEntities extends Function {

        public String text;
        public TextParseMode parseMode;

        public ParseTextEntities() {}

        public ParseTextEntities(String text, TextParseMode parseMode) {

            this.text = text;
            this.parseMode = parseMode;

        }

        @Override
        public int getConstructor() { return -1709194593; }

    }


    /**
     * Parses Markdown entities in a human-friendly format, ignoring markup errors
     * Can be called synchronously
     *
     * @text - The text to parse
     *         For example, "__italic__ ~~strikethrough~~ **bold** `code` ```pre``` __[italic__ text_url](telegram.org) __italic**bold italic__bold**"
     */
    public static class ParseMarkdown extends Function {

        public FormattedText text;

        public ParseMarkdown() {}

        public ParseMarkdown(FormattedText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 756366063; }

    }


    /**
     * Replaces text entities with Markdown formatting in a human-friendly format
     * Entities that can't be represented in Markdown unambiguously are kept as is
     * Can be called synchronously
     *
     * @text - The text
     */
    public static class GetMarkdownText extends Function {

        public FormattedText text;

        public GetMarkdownText() {}

        public GetMarkdownText(FormattedText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 164524584; }

    }


    /**
     * Returns the MIME type of a file, guessed by its extension
     * Returns an empty string on failure
     * Can be called synchronously
     *
     * @fileName - The name of the file or path to the file
     */
    public static class GetFileMimeType extends Function {

        public String fileName;

        public GetFileMimeType() {}

        public GetFileMimeType(String fileName) {

            this.fileName = fileName;

        }

        @Override
        public int getConstructor() { return -2073879671; }

    }


    /**
     * Returns the extension of a file, guessed by its MIME type
     * Returns an empty string on failure
     * Can be called synchronously
     *
     * @mimeType - The MIME type of the file
     */
    public static class GetFileExtension extends Function {

        public String mimeType;

        public GetFileExtension() {}

        public GetFileExtension(String mimeType) {

            this.mimeType = mimeType;

        }

        @Override
        public int getConstructor() { return -106055372; }

    }


    /**
     * Removes potentially dangerous characters from the name of a file
     * The encoding of the file name is supposed to be UTF-8
     * Returns an empty string on failure
     * Can be called synchronously
     *
     * @fileName - File name or path to the file
     */
    public static class CleanFileName extends Function {

        public String fileName;

        public CleanFileName() {}

        public CleanFileName(String fileName) {

            this.fileName = fileName;

        }

        @Override
        public int getConstructor() { return 967964667; }

    }


    /**
     * Returns a string stored in the local database from the specified localization target and language pack by its key
     * Returns a 404 error if the string is not found
     * Can be called synchronously
     *
     * @languagePackDatabasePath - Path to the language pack database in which strings are stored
     * @localizationTarget - Localization target to which the language pack belongs
     * @languagePackId - Language pack identifier
     * @key - Language pack key of the string to be returned
     */
    public static class GetLanguagePackString extends Function {

        public String languagePackDatabasePath;
        public String localizationTarget;
        public String languagePackId;
        public String key;

        public GetLanguagePackString() {}

        public GetLanguagePackString(String languagePackDatabasePath, String localizationTarget, String languagePackId, String key) {

            this.languagePackDatabasePath = languagePackDatabasePath;
            this.localizationTarget = localizationTarget;
            this.languagePackId = languagePackId;
            this.key = key;

        }

        @Override
        public int getConstructor() { return 150789747; }

    }


    /**
     * Converts a JSON-serialized string to corresponding JsonValue object
     * Can be called synchronously
     *
     * @json - The JSON-serialized string
     */
    public static class GetJsonValue extends Function {

        public String json;

        public GetJsonValue() {}

        public GetJsonValue(String json) {

            this.json = json;

        }

        @Override
        public int getConstructor() { return -1829086715; }

    }


    /**
     * Converts a JsonValue object to corresponding JSON-serialized string
     * Can be called synchronously
     *
     * @jsonValue - The JsonValue object
     */
    public static class GetJsonString extends Function {

        public JsonValue jsonValue;

        public GetJsonString() {}

        public GetJsonString(JsonValue jsonValue) {

            this.jsonValue = jsonValue;

        }

        @Override
        public int getConstructor() { return 663458849; }

    }


    /**
     * Changes the user answer to a poll
     * A poll in quiz mode can be answered only once
     *
     * @chatId - Identifier of the chat to which the poll belongs
     * @messageId - Identifier of the message containing the poll
     * @optionIds - 0-based identifiers of answer options, chosen by the user
     *              User can choose more than 1 answer option only is the poll allows multiple answers
     */
    public static class SetPollAnswer extends Function {

        public long chatId;
        public long messageId;
        public int[] optionIds;

        public SetPollAnswer() {}

        public SetPollAnswer(long chatId, long messageId, int[] optionIds) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.optionIds = optionIds;

        }

        @Override
        public int getConstructor() { return -1399388792; }

    }


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
    public static class GetPollVoters extends Function {

        public long chatId;
        public long messageId;
        public int optionId;
        public int offset;
        public int limit;

        public GetPollVoters() {}

        public GetPollVoters(long chatId, long messageId, int optionId, int offset, int limit) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.optionId = optionId;
            this.offset = offset;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return 2075288734; }

    }


    /**
     * Stops a poll
     * A poll in a message can be stopped when the message has can_be_edited flag set
     *
     * @chatId - Identifier of the chat to which the poll belongs
     * @messageId - Identifier of the message containing the poll
     * @replyMarkup - The new message reply markup
     *                For bots only
     */
    public static class StopPoll extends Function {

        public long chatId;
        public long messageId;
        public ReplyMarkup replyMarkup;

        public StopPoll() {}

        public StopPoll(long chatId, long messageId, ReplyMarkup replyMarkup) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.replyMarkup = replyMarkup;

        }

        @Override
        public int getConstructor() { return 1659374253; }

    }


    /**
     * Hides a suggested action
     *
     * @action - Suggested action to hide
     */
    public static class HideSuggestedAction extends Function {

        public SuggestedAction action;

        public HideSuggestedAction() {}

        public HideSuggestedAction(SuggestedAction action) {

            this.action = action;

        }

        @Override
        public int getConstructor() { return -1561384065; }

    }


    /**
     * Returns information about a button of type inlineKeyboardButtonTypeLoginUrl
     * The method needs to be called when the user presses the button
     *
     * @chatId - Chat identifier of the message with the button
     * @messageId - Message identifier of the message with the button
     * @buttonId - Button identifier
     */
    public static class GetLoginUrlInfo extends Function {

        public long chatId;
        public long messageId;
        public int buttonId;

        public GetLoginUrlInfo() {}

        public GetLoginUrlInfo(long chatId, long messageId, int buttonId) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.buttonId = buttonId;

        }

        @Override
        public int getConstructor() { return -980042966; }

    }


    /**
     * Returns an HTTP URL which can be used to automatically authorize the user on a website after clicking an inline button of type inlineKeyboardButtonTypeLoginUrl
     * Use the method getLoginUrlInfo to find whether a prior user confirmation is needed
     * If an error is returned, then the button must be handled as an ordinary URL button
     *
     * @chatId - Chat identifier of the message with the button
     * @messageId - Message identifier of the message with the button
     * @buttonId - Button identifier
     * @allowWriteAccess - True, if the user allowed the bot to send them messages
     */
    public static class GetLoginUrl extends Function {

        public long chatId;
        public long messageId;
        public int buttonId;
        public boolean allowWriteAccess;

        public GetLoginUrl() {}

        public GetLoginUrl(long chatId, long messageId, int buttonId, boolean allowWriteAccess) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.buttonId = buttonId;
            this.allowWriteAccess = allowWriteAccess;

        }

        @Override
        public int getConstructor() { return 694973925; }

    }


    /**
     * Sends an inline query to a bot and returns its results
     * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
     *
     * @botUserId - The identifier of the target bot
     * @chatId - Identifier of the chat where the query was sent
     * @userLocation - Location of the user, only if needed
     * @query - Text of the query
     * @offset - Offset of the first entry to return
     */
    public static class GetInlineQueryResults extends Function {

        public int botUserId;
        public long chatId;
        public Location userLocation;
        public String query;
        public String offset;

        public GetInlineQueryResults() {}

        public GetInlineQueryResults(int botUserId, long chatId, Location userLocation, String query, String offset) {

            this.botUserId = botUserId;
            this.chatId = chatId;
            this.userLocation = userLocation;
            this.query = query;
            this.offset = offset;

        }

        @Override
        public int getConstructor() { return -1182511172; }

    }


    /**
     * Sets the result of an inline query
     * For bots only
     *
     * @inlineQueryId - Identifier of the inline query
     * @isPersonal - True, if the result of the query can be cached for the specified user
     * @results - The results of the query
     * @cacheTime - Allowed time to cache the results of the query, in seconds
     * @nextOffset - Offset for the next inline query
     *               Pass an empty string if there are no more results
     * @switchPmText - If non-empty, this text should be shown on the button that opens a private chat with the bot and sends a start message to the bot with the parameter switch_pm_parameter
     * @switchPmParameter - The parameter for the bot start message
     */
    public static class AnswerInlineQuery extends Function {

        public long inlineQueryId;
        public boolean isPersonal;
        public InputInlineQueryResult[] results;
        public int cacheTime;
        public String nextOffset;
        public String switchPmText;
        public String switchPmParameter;

        public AnswerInlineQuery() {}

        public AnswerInlineQuery(long inlineQueryId, boolean isPersonal, InputInlineQueryResult[] results, int cacheTime, String nextOffset, String switchPmText, String switchPmParameter) {

            this.inlineQueryId = inlineQueryId;
            this.isPersonal = isPersonal;
            this.results = results;
            this.cacheTime = cacheTime;
            this.nextOffset = nextOffset;
            this.switchPmText = switchPmText;
            this.switchPmParameter = switchPmParameter;

        }

        @Override
        public int getConstructor() { return 485879477; }

    }


    /**
     * Sends a callback query to a bot and returns an answer
     * Returns an error with code 502 if the bot fails to answer the query before the query timeout expires
     *
     * @chatId - Identifier of the chat with the message
     * @messageId - Identifier of the message from which the query originated
     * @payload - Query payload
     */
    public static class GetCallbackQueryAnswer extends Function {

        public long chatId;
        public long messageId;
        public CallbackQueryPayload payload;

        public GetCallbackQueryAnswer() {}

        public GetCallbackQueryAnswer(long chatId, long messageId, CallbackQueryPayload payload) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.payload = payload;

        }

        @Override
        public int getConstructor() { return 116357727; }

    }


    /**
     * Sets the result of a callback query
     * For bots only
     *
     * @callbackQueryId - Identifier of the callback query
     * @text - Text of the answer
     * @showAlert - If true, an alert should be shown to the user instead of a toast notification
     * @url - URL to be opened
     * @cacheTime - Time during which the result of the query can be cached, in seconds
     */
    public static class AnswerCallbackQuery extends Function {

        public long callbackQueryId;
        public String text;
        public boolean showAlert;
        public String url;
        public int cacheTime;

        public AnswerCallbackQuery() {}

        public AnswerCallbackQuery(long callbackQueryId, String text, boolean showAlert, String url, int cacheTime) {

            this.callbackQueryId = callbackQueryId;
            this.text = text;
            this.showAlert = showAlert;
            this.url = url;
            this.cacheTime = cacheTime;

        }

        @Override
        public int getConstructor() { return -1153028490; }

    }


    /**
     * Sets the result of a shipping query
     * For bots only
     *
     * @shippingQueryId - Identifier of the shipping query
     * @shippingOptions - Available shipping options
     * @errorMessage - An error message, empty on success
     */
    public static class AnswerShippingQuery extends Function {

        public long shippingQueryId;
        public ShippingOption[] shippingOptions;
        public String errorMessage;

        public AnswerShippingQuery() {}

        public AnswerShippingQuery(long shippingQueryId, ShippingOption[] shippingOptions, String errorMessage) {

            this.shippingQueryId = shippingQueryId;
            this.shippingOptions = shippingOptions;
            this.errorMessage = errorMessage;

        }

        @Override
        public int getConstructor() { return -434601324; }

    }


    /**
     * Sets the result of a pre-checkout query
     * For bots only
     *
     * @preCheckoutQueryId - Identifier of the pre-checkout query
     * @errorMessage - An error message, empty on success
     */
    public static class AnswerPreCheckoutQuery extends Function {

        public long preCheckoutQueryId;
        public String errorMessage;

        public AnswerPreCheckoutQuery() {}

        public AnswerPreCheckoutQuery(long preCheckoutQueryId, String errorMessage) {

            this.preCheckoutQueryId = preCheckoutQueryId;
            this.errorMessage = errorMessage;

        }

        @Override
        public int getConstructor() { return -1486789653; }

    }


    /**
     * Updates the game score of the specified user in the game
     * For bots only
     *
     * @chatId - The chat to which the message with the game belongs
     * @messageId - Identifier of the message
     * @editMessage - True, if the message should be edited
     * @userId - User identifier
     * @score - The new score
     * @force - Pass true to update the score even if it decreases
     *          If the score is 0, the user will be deleted from the high score table
     */
    public static class SetGameScore extends Function {

        public long chatId;
        public long messageId;
        public boolean editMessage;
        public int userId;
        public int score;
        public boolean force;

        public SetGameScore() {}

        public SetGameScore(long chatId, long messageId, boolean editMessage, int userId, int score, boolean force) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.editMessage = editMessage;
            this.userId = userId;
            this.score = score;
            this.force = force;

        }

        @Override
        public int getConstructor() { return -1768307069; }

    }


    /**
     * Updates the game score of the specified user in a game
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @editMessage - True, if the message should be edited
     * @userId - User identifier
     * @score - The new score
     * @force - Pass true to update the score even if it decreases
     *          If the score is 0, the user will be deleted from the high score table
     */
    public static class SetInlineGameScore extends Function {

        public String inlineMessageId;
        public boolean editMessage;
        public int userId;
        public int score;
        public boolean force;

        public SetInlineGameScore() {}

        public SetInlineGameScore(String inlineMessageId, boolean editMessage, int userId, int score, boolean force) {

            this.inlineMessageId = inlineMessageId;
            this.editMessage = editMessage;
            this.userId = userId;
            this.score = score;
            this.force = force;

        }

        @Override
        public int getConstructor() { return 758435487; }

    }


    /**
     * Returns the high scores for a game and some part of the high score table in the range of the specified user
     * For bots only
     *
     * @chatId - The chat that contains the message with the game
     * @messageId - Identifier of the message
     * @userId - User identifier
     */
    public static class GetGameHighScores extends Function {

        public long chatId;
        public long messageId;
        public int userId;

        public GetGameHighScores() {}

        public GetGameHighScores(long chatId, long messageId, int userId) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.userId = userId;

        }

        @Override
        public int getConstructor() { return 1920923753; }

    }


    /**
     * Returns game high scores and some part of the high score table in the range of the specified user
     * For bots only
     *
     * @inlineMessageId - Inline message identifier
     * @userId - User identifier
     */
    public static class GetInlineGameHighScores extends Function {

        public String inlineMessageId;
        public int userId;

        public GetInlineGameHighScores() {}

        public GetInlineGameHighScores(String inlineMessageId, int userId) {

            this.inlineMessageId = inlineMessageId;
            this.userId = userId;

        }

        @Override
        public int getConstructor() { return -1833445800; }

    }


    /**
     * Deletes the default reply markup from a chat
     * Must be called after a one-time keyboard or a ForceReply reply markup has been used
     * UpdateChatReplyMarkup will be sent if the reply markup will be changed
     *
     * @chatId - Chat identifier
     * @messageId - The message identifier of the used keyboard
     */
    public static class DeleteChatReplyMarkup extends Function {

        public long chatId;
        public long messageId;

        public DeleteChatReplyMarkup() {}

        public DeleteChatReplyMarkup(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return 100637531; }

    }


    /**
     * Sends a notification about user activity in a chat
     *
     * @chatId - Chat identifier
     * @messageThreadId - If not 0, a message thread identifier in which the action was performed
     * @action - The action description
     */
    public static class SendChatAction extends Function {

        public long chatId;
        public long messageThreadId;
        public ChatAction action;

        public SendChatAction() {}

        public SendChatAction(long chatId, long messageThreadId, ChatAction action) {

            this.chatId = chatId;
            this.messageThreadId = messageThreadId;
            this.action = action;

        }

        @Override
        public int getConstructor() { return 2096947540; }

    }


    /**
     * Informs TDLib that the chat is opened by the user
     * Many useful activities depend on the chat being opened or closed (e.g., in supergroups and channels all updates are received only for opened chats)
     *
     * @chatId - Chat identifier
     */
    public static class OpenChat extends Function {

        public long chatId;

        public OpenChat() {}

        public OpenChat(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return -323371509; }

    }


    /**
     * Informs TDLib that the chat is closed by the user
     * Many useful activities depend on the chat being opened or closed
     *
     * @chatId - Chat identifier
     */
    public static class CloseChat extends Function {

        public long chatId;

        public CloseChat() {}

        public CloseChat(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 39749353; }

    }


    /**
     * Informs TDLib that messages are being viewed by the user
     * Many useful activities depend on whether the messages are currently being viewed or not (e.g., marking messages as read, incrementing a view counter, updating a view counter, removing deleted messages in supergroups and channels)
     *
     * @chatId - Chat identifier
     * @messageThreadId - If not 0, a message thread identifier in which the messages are being viewed
     * @messageIds - The identifiers of the messages being viewed
     * @forceRead - True, if messages in closed chats should be marked as read by the request
     */
    public static class ViewMessages extends Function {

        public long chatId;
        public long messageThreadId;
        public long[] messageIds;
        public boolean forceRead;

        public ViewMessages() {}

        public ViewMessages(long chatId, long messageThreadId, long[] messageIds, boolean forceRead) {

            this.chatId = chatId;
            this.messageThreadId = messageThreadId;
            this.messageIds = messageIds;
            this.forceRead = forceRead;

        }

        @Override
        public int getConstructor() { return -1155961496; }

    }


    /**
     * Informs TDLib that the message content has been opened (e.g., the user has opened a photo, video, document, location or venue, or has listened to an audio file or voice note message)
     * An updateMessageContentOpened update will be generated if something has changed
     *
     * @chatId - Chat identifier of the message
     * @messageId - Identifier of the message with the opened content
     */
    public static class OpenMessageContent extends Function {

        public long chatId;
        public long messageId;

        public OpenMessageContent() {}

        public OpenMessageContent(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return -739088005; }

    }


    /**
     * Marks all mentions in a chat as read
     *
     * @chatId - Chat identifier
     */
    public static class ReadAllChatMentions extends Function {

        public long chatId;

        public ReadAllChatMentions() {}

        public ReadAllChatMentions(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 1357558453; }

    }


    /**
     * Returns an existing chat corresponding to a given user
     *
     * @userId - User identifier
     * @force - If true, the chat will be created without network request
     *          In this case all information about the chat except its type, title and photo can be incorrect
     */
    public static class CreatePrivateChat extends Function {

        public int userId;
        public boolean force;

        public CreatePrivateChat() {}

        public CreatePrivateChat(int userId, boolean force) {

            this.userId = userId;
            this.force = force;

        }

        @Override
        public int getConstructor() { return -1807530364; }

    }


    /**
     * Returns an existing chat corresponding to a known basic group
     *
     * @basicGroupId - Basic group identifier
     * @force - If true, the chat will be created without network request
     *          In this case all information about the chat except its type, title and photo can be incorrect
     */
    public static class CreateBasicGroupChat extends Function {

        public int basicGroupId;
        public boolean force;

        public CreateBasicGroupChat() {}

        public CreateBasicGroupChat(int basicGroupId, boolean force) {

            this.basicGroupId = basicGroupId;
            this.force = force;

        }

        @Override
        public int getConstructor() { return 642492777; }

    }


    /**
     * Returns an existing chat corresponding to a known supergroup or channel
     *
     * @supergroupId - Supergroup or channel identifier
     * @force - If true, the chat will be created without network request
     *          In this case all information about the chat except its type, title and photo can be incorrect
     */
    public static class CreateSupergroupChat extends Function {

        public int supergroupId;
        public boolean force;

        public CreateSupergroupChat() {}

        public CreateSupergroupChat(int supergroupId, boolean force) {

            this.supergroupId = supergroupId;
            this.force = force;

        }

        @Override
        public int getConstructor() { return 352742758; }

    }


    /**
     * Returns an existing chat corresponding to a known secret chat
     *
     * @secretChatId - Secret chat identifier
     */
    public static class CreateSecretChat extends Function {

        public int secretChatId;

        public CreateSecretChat() {}

        public CreateSecretChat(int secretChatId) {

            this.secretChatId = secretChatId;

        }

        @Override
        public int getConstructor() { return 1930285615; }

    }


    /**
     * Creates a new basic group and sends a corresponding messageBasicGroupChatCreate
     * Returns the newly created chat
     *
     * @userIds - Identifiers of users to be added to the basic group
     * @title - Title of the new basic group
     */
    public static class CreateNewBasicGroupChat extends Function {

        public int[] userIds;
        public String title;

        public CreateNewBasicGroupChat() {}

        public CreateNewBasicGroupChat(int[] userIds, String title) {

            this.userIds = userIds;
            this.title = title;

        }

        @Override
        public int getConstructor() { return 1874532069; }

    }


    /**
     * Creates a new supergroup or channel and sends a corresponding messageSupergroupChatCreate
     * Returns the newly created chat
     *
     * @title - Title of the new chat
     * @isChannel - True, if a channel chat should be created
     * @description - Chat description
     * @location - Chat location if a location-based supergroup is being created
     */
    public static class CreateNewSupergroupChat extends Function {

        public String title;
        public boolean isChannel;
        public String description;
        public ChatLocation location;

        public CreateNewSupergroupChat() {}

        public CreateNewSupergroupChat(String title, boolean isChannel, String description, ChatLocation location) {

            this.title = title;
            this.isChannel = isChannel;
            this.description = description;
            this.location = location;

        }

        @Override
        public int getConstructor() { return -8999251; }

    }


    /**
     * Creates a new secret chat
     * Returns the newly created chat
     *
     * @userId - Identifier of the target user
     */
    public static class CreateNewSecretChat extends Function {

        public int userId;

        public CreateNewSecretChat() {}

        public CreateNewSecretChat(int userId) {

            this.userId = userId;

        }

        @Override
        public int getConstructor() { return 1689344881; }

    }


    /**
     * Creates a new supergroup from an existing basic group and sends a corresponding messageChatUpgradeTo and messageChatUpgradeFrom
     * Requires creator privileges
     * Deactivates the original basic group
     *
     * @chatId - Identifier of the chat to upgrade
     */
    public static class UpgradeBasicGroupChatToSupergroupChat extends Function {

        public long chatId;

        public UpgradeBasicGroupChatToSupergroupChat() {}

        public UpgradeBasicGroupChatToSupergroupChat(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 300488122; }

    }


    /**
     * Returns chat lists to which the chat can be added
     * This is an offline request
     *
     * @chatId - Chat identifier
     */
    public static class GetChatListsToAddChat extends Function {

        public long chatId;

        public GetChatListsToAddChat() {}

        public GetChatListsToAddChat(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 654956193; }

    }


    /**
     * Adds a chat to a chat list
     * A chat can't be simultaneously in Main and Archive chat lists, so it is automatically removed from another one if needed
     *
     * @chatId - Chat identifier
     * @chatList - The chat list
     *             Use getChatListsToAddChat to get suitable chat lists
     */
    public static class AddChatToList extends Function {

        public long chatId;
        public ChatList chatList;

        public AddChatToList() {}

        public AddChatToList(long chatId, ChatList chatList) {

            this.chatId = chatId;
            this.chatList = chatList;

        }

        @Override
        public int getConstructor() { return -80523595; }

    }


    /**
     * Returns information about a chat filter by its identifier
     *
     * @chatFilterId - Chat filter identifier
     */
    public static class GetChatFilter extends Function {

        public int chatFilterId;

        public GetChatFilter() {}

        public GetChatFilter(int chatFilterId) {

            this.chatFilterId = chatFilterId;

        }

        @Override
        public int getConstructor() { return 1826317803; }

    }


    /**
     * Creates new chat filter
     * Returns information about the created chat filter
     *
     * @filter - Chat filter
     */
    public static class CreateChatFilter extends Function {

        public ChatFilter filter;

        public CreateChatFilter() {}

        public CreateChatFilter(ChatFilter filter) {

            this.filter = filter;

        }

        @Override
        public int getConstructor() { return 49065126; }

    }


    /**
     * Edits existing chat filter
     * Returns information about the edited chat filter
     *
     * @chatFilterId - Chat filter identifier
     * @filter - The edited chat filter
     */
    public static class EditChatFilter extends Function {

        public int chatFilterId;
        public ChatFilter filter;

        public EditChatFilter() {}

        public EditChatFilter(int chatFilterId, ChatFilter filter) {

            this.chatFilterId = chatFilterId;
            this.filter = filter;

        }

        @Override
        public int getConstructor() { return -1674793086; }

    }


    /**
     * Deletes existing chat filter
     *
     * @chatFilterId - Chat filter identifier
     */
    public static class DeleteChatFilter extends Function {

        public int chatFilterId;

        public DeleteChatFilter() {}

        public DeleteChatFilter(int chatFilterId) {

            this.chatFilterId = chatFilterId;

        }

        @Override
        public int getConstructor() { return -523220877; }

    }


    /**
     * Changes the order of chat filters
     *
     * @chatFilterIds - Identifiers of chat filters in the new correct order
     */
    public static class ReorderChatFilters extends Function {

        public int[] chatFilterIds;

        public ReorderChatFilters() {}

        public ReorderChatFilters(int[] chatFilterIds) {

            this.chatFilterIds = chatFilterIds;

        }

        @Override
        public int getConstructor() { return -1258111097; }

    }


    /**
     * Returns recommended chat filters for the current user
     */
    public static class GetRecommendedChatFilters extends Function {

        @Override
        public int getConstructor() { return -779390746; }

    }


    /**
     * Returns default icon name for a filter
     * Can be called synchronously
     *
     * @filter - Chat filter
     */
    public static class GetChatFilterDefaultIconName extends Function {

        public ChatFilter filter;

        public GetChatFilterDefaultIconName() {}

        public GetChatFilterDefaultIconName(ChatFilter filter) {

            this.filter = filter;

        }

        @Override
        public int getConstructor() { return -1339828680; }

    }


    /**
     * Changes the chat title
     * Supported only for basic groups, supergroups and channels
     * Requires can_change_info rights
     *
     * @chatId - Chat identifier
     * @title - New title of the chat
     */
    public static class SetChatTitle extends Function {

        public long chatId;
        public String title;

        public SetChatTitle() {}

        public SetChatTitle(long chatId, String title) {

            this.chatId = chatId;
            this.title = title;

        }

        @Override
        public int getConstructor() { return 164282047; }

    }


    /**
     * Changes the photo of a chat
     * Supported only for basic groups, supergroups and channels
     * Requires can_change_info rights
     *
     * @chatId - Chat identifier
     * @photo - New chat photo
     *          Pass null to delete the chat photo
     */
    public static class SetChatPhoto extends Function {

        public long chatId;
        public InputChatPhoto photo;

        public SetChatPhoto() {}

        public SetChatPhoto(long chatId, InputChatPhoto photo) {

            this.chatId = chatId;
            this.photo = photo;

        }

        @Override
        public int getConstructor() { return -377778941; }

    }


    /**
     * Changes the chat members permissions
     * Supported only for basic groups and supergroups
     * Requires can_restrict_members administrator right
     *
     * @chatId - Chat identifier
     * @permissions - New non-administrator members permissions in the chat
     */
    public static class SetChatPermissions extends Function {

        public long chatId;
        public ChatPermissions permissions;

        public SetChatPermissions() {}

        public SetChatPermissions(long chatId, ChatPermissions permissions) {

            this.chatId = chatId;
            this.permissions = permissions;

        }

        @Override
        public int getConstructor() { return 2138507006; }

    }


    /**
     * Changes the draft message in a chat
     *
     * @chatId - Chat identifier
     * @messageThreadId - If not 0, a message thread identifier in which the draft was changed
     * @draftMessage - New draft message
     */
    public static class SetChatDraftMessage extends Function {

        public long chatId;
        public long messageThreadId;
        @Nullable public DraftMessage draftMessage;

        public SetChatDraftMessage() {}

        public SetChatDraftMessage(long chatId, long messageThreadId, @Nullable DraftMessage draftMessage) {

            this.chatId = chatId;
            this.messageThreadId = messageThreadId;
            this.draftMessage = draftMessage;

        }

        @Override
        public int getConstructor() { return 1683889946; }

    }


    /**
     * Changes the notification settings of a chat
     * Notification settings of a chat with the current user (Saved Messages) can't be changed
     *
     * @chatId - Chat identifier
     * @notificationSettings - New notification settings for the chat
     *                         If the chat is muted for more than 1 week, it is considered to be muted forever
     */
    public static class SetChatNotificationSettings extends Function {

        public long chatId;
        public ChatNotificationSettings notificationSettings;

        public SetChatNotificationSettings() {}

        public SetChatNotificationSettings(long chatId, ChatNotificationSettings notificationSettings) {

            this.chatId = chatId;
            this.notificationSettings = notificationSettings;

        }

        @Override
        public int getConstructor() { return 777199614; }

    }


    /**
     * Changes the marked as unread state of a chat
     *
     * @chatId - Chat identifier
     * @isMarkedAsUnread - New value of is_marked_as_unread
     */
    public static class ToggleChatIsMarkedAsUnread extends Function {

        public long chatId;
        public boolean isMarkedAsUnread;

        public ToggleChatIsMarkedAsUnread() {}

        public ToggleChatIsMarkedAsUnread(long chatId, boolean isMarkedAsUnread) {

            this.chatId = chatId;
            this.isMarkedAsUnread = isMarkedAsUnread;

        }

        @Override
        public int getConstructor() { return -986129697; }

    }


    /**
     * Changes the block state of a chat
     * Currently, only private chats and supergroups can be blocked
     *
     * @chatId - Chat identifier
     * @isBlocked - New value of is_blocked
     */
    public static class ToggleChatIsBlocked extends Function {

        public long chatId;
        public boolean isBlocked;

        public ToggleChatIsBlocked() {}

        public ToggleChatIsBlocked(long chatId, boolean isBlocked) {

            this.chatId = chatId;
            this.isBlocked = isBlocked;

        }

        @Override
        public int getConstructor() { return 202580115; }

    }


    /**
     * Changes the value of the default disable_notification parameter, used when a message is sent to a chat
     *
     * @chatId - Chat identifier
     * @defaultDisableNotification - New value of default_disable_notification
     */
    public static class ToggleChatDefaultDisableNotification extends Function {

        public long chatId;
        public boolean defaultDisableNotification;

        public ToggleChatDefaultDisableNotification() {}

        public ToggleChatDefaultDisableNotification(long chatId, boolean defaultDisableNotification) {

            this.chatId = chatId;
            this.defaultDisableNotification = defaultDisableNotification;

        }

        @Override
        public int getConstructor() { return 314794002; }

    }


    /**
     * Changes application-specific data associated with a chat
     *
     * @chatId - Chat identifier
     * @clientData - New value of client_data
     */
    public static class SetChatClientData extends Function {

        public long chatId;
        public String clientData;

        public SetChatClientData() {}

        public SetChatClientData(long chatId, String clientData) {

            this.chatId = chatId;
            this.clientData = clientData;

        }

        @Override
        public int getConstructor() { return -827119811; }

    }


    /**
     * Changes information about a chat
     * Available for basic groups, supergroups, and channels
     * Requires can_change_info rights
     *
     * @chatId - Identifier of the chat
     * @description - New chat description
     */
    public static class SetChatDescription extends Function {

        public long chatId;
        public String description;

        public SetChatDescription() {}

        public SetChatDescription(long chatId, String description) {

            this.chatId = chatId;
            this.description = description;

        }

        @Override
        public int getConstructor() { return 1957213277; }

    }


    /**
     * Changes the discussion group of a channel chat
     * Requires can_change_info rights in the channel if it is specified
     *
     * @chatId - Identifier of the channel chat
     *           Pass 0 to remove a link from the supergroup passed in the second argument to a linked channel chat (requires can_pin_messages rights in the supergroup)
     * @discussionChatId - Identifier of a new channel's discussion group
     *                     Use 0 to remove the discussion group
     *                     Use the method getSuitableDiscussionChats to find all suitable groups
     *                     Basic group chats need to be first upgraded to supergroup chats
     *                     If new chat members don't have access to old messages in the supergroup, then toggleSupergroupIsAllHistoryAvailable needs to be used first to change that
     */
    public static class SetChatDiscussionGroup extends Function {

        public long chatId;
        public long discussionChatId;

        public SetChatDiscussionGroup() {}

        public SetChatDiscussionGroup(long chatId, long discussionChatId) {

            this.chatId = chatId;
            this.discussionChatId = discussionChatId;

        }

        @Override
        public int getConstructor() { return -918801736; }

    }


    /**
     * Changes the location of a chat
     * Available only for some location-based supergroups, use supergroupFullInfo.can_set_location to check whether the method is allowed to use
     *
     * @chatId - Chat identifier
     * @location - New location for the chat
     *             Must be valid and not null
     */
    public static class SetChatLocation extends Function {

        public long chatId;
        public ChatLocation location;

        public SetChatLocation() {}

        public SetChatLocation(long chatId, ChatLocation location) {

            this.chatId = chatId;
            this.location = location;

        }

        @Override
        public int getConstructor() { return -767091286; }

    }


    /**
     * Changes the slow mode delay of a chat
     * Available only for supergroups
     * Requires can_restrict_members rights
     *
     * @chatId - Chat identifier
     * @slowModeDelay - New slow mode delay for the chat
     *                  Must be one of 0, 10, 30, 60, 300, 900, 3600
     */
    public static class SetChatSlowModeDelay extends Function {

        public long chatId;
        public int slowModeDelay;

        public SetChatSlowModeDelay() {}

        public SetChatSlowModeDelay(long chatId, int slowModeDelay) {

            this.chatId = chatId;
            this.slowModeDelay = slowModeDelay;

        }

        @Override
        public int getConstructor() { return -540350914; }

    }


    /**
     * Pins a message in a chat
     * Requires can_pin_messages rights
     *
     * @chatId - Identifier of the chat
     * @messageId - Identifier of the new pinned message
     * @disableNotification - True, if there should be no notification about the pinned message
     */
    public static class PinChatMessage extends Function {

        public long chatId;
        public long messageId;
        public boolean disableNotification;

        public PinChatMessage() {}

        public PinChatMessage(long chatId, long messageId, boolean disableNotification) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.disableNotification = disableNotification;

        }

        @Override
        public int getConstructor() { return -554712351; }

    }


    /**
     * Removes the pinned message from a chat
     * Requires can_pin_messages rights in the group or channel
     *
     * @chatId - Identifier of the chat
     */
    public static class UnpinChatMessage extends Function {

        public long chatId;

        public UnpinChatMessage() {}

        public UnpinChatMessage(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 277557690; }

    }


    /**
     * Adds current user as a new member to a chat
     * Private and secret chats can't be joined using this method
     *
     * @chatId - Chat identifier
     */
    public static class JoinChat extends Function {

        public long chatId;

        public JoinChat() {}

        public JoinChat(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 326769313; }

    }


    /**
     * Removes current user from chat members
     * Private and secret chats can't be left using this method
     *
     * @chatId - Chat identifier
     */
    public static class LeaveChat extends Function {

        public long chatId;

        public LeaveChat() {}

        public LeaveChat(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return -1825080735; }

    }


    /**
     * Adds a new member to a chat
     * Members can't be added to private or secret chats
     * Members will not be added until the chat state has been synchronized with the server
     *
     * @chatId - Chat identifier
     * @userId - Identifier of the user
     * @forwardLimit - The number of earlier messages from the chat to be forwarded to the new member
     *                 Ignored for supergroups and channels
     */
    public static class AddChatMember extends Function {

        public long chatId;
        public int userId;
        public int forwardLimit;

        public AddChatMember() {}

        public AddChatMember(long chatId, int userId, int forwardLimit) {

            this.chatId = chatId;
            this.userId = userId;
            this.forwardLimit = forwardLimit;

        }

        @Override
        public int getConstructor() { return 1182817962; }

    }


    /**
     * Adds multiple new members to a chat
     * Currently this option is only available for supergroups and channels
     * This option can't be used to join a chat
     * Members can't be added to a channel if it has more than 200 members
     * Members will not be added until the chat state has been synchronized with the server
     *
     * @chatId - Chat identifier
     * @userIds - Identifiers of the users to be added to the chat
     */
    public static class AddChatMembers extends Function {

        public long chatId;
        public int[] userIds;

        public AddChatMembers() {}

        public AddChatMembers(long chatId, int[] userIds) {

            this.chatId = chatId;
            this.userIds = userIds;

        }

        @Override
        public int getConstructor() { return 1234094617; }

    }


    /**
     * Changes the status of a chat member, needs appropriate privileges
     * This function is currently not suitable for adding new members to the chat and transferring chat ownership
     * Instead, use addChatMember or transferChatOwnership
     * The chat member status will not be changed until it has been synchronized with the server
     *
     * @chatId - Chat identifier
     * @userId - User identifier
     * @status - The new status of the member in the chat
     */
    public static class SetChatMemberStatus extends Function {

        public long chatId;
        public int userId;
        public ChatMemberStatus status;

        public SetChatMemberStatus() {}

        public SetChatMemberStatus(long chatId, int userId, ChatMemberStatus status) {

            this.chatId = chatId;
            this.userId = userId;
            this.status = status;

        }

        @Override
        public int getConstructor() { return -1754439241; }

    }


    /**
     * Checks whether the current session can be used to transfer a chat ownership to another user
     */
    public static class CanTransferOwnership extends Function {

        @Override
        public int getConstructor() { return 634602508; }

    }


    /**
     * Changes the owner of a chat
     * The current user must be a current owner of the chat
     * Use the method canTransferOwnership to check whether the ownership can be transferred from the current session
     * Available only for supergroups and channel chats
     *
     * @chatId - Chat identifier
     * @userId - Identifier of the user to which transfer the ownership
     *           The ownership can't be transferred to a bot or to a deleted user
     * @password - The password of the current user
     */
    public static class TransferChatOwnership extends Function {

        public long chatId;
        public int userId;
        public String password;

        public TransferChatOwnership() {}

        public TransferChatOwnership(long chatId, int userId, String password) {

            this.chatId = chatId;
            this.userId = userId;
            this.password = password;

        }

        @Override
        public int getConstructor() { return -1925047127; }

    }


    /**
     * Returns information about a single member of a chat
     *
     * @chatId - Chat identifier
     * @userId - User identifier
     */
    public static class GetChatMember extends Function {

        public long chatId;
        public int userId;

        public GetChatMember() {}

        public GetChatMember(long chatId, int userId) {

            this.chatId = chatId;
            this.userId = userId;

        }

        @Override
        public int getConstructor() { return 677085892; }

    }


    /**
     * Searches for a specified query in the first name, last name and username of the members of a specified chat
     * Requires administrator rights in channels
     *
     * @chatId - Chat identifier
     * @query - Query to search for
     * @limit - The maximum number of users to be returned
     * @filter - The type of users to return
     *           By default, chatMembersFilterMembers
     */
    public static class SearchChatMembers extends Function {

        public long chatId;
        public String query;
        public int limit;
        public ChatMembersFilter filter;

        public SearchChatMembers() {}

        public SearchChatMembers(long chatId, String query, int limit, ChatMembersFilter filter) {

            this.chatId = chatId;
            this.query = query;
            this.limit = limit;
            this.filter = filter;

        }

        @Override
        public int getConstructor() { return -445823291; }

    }


    /**
     * Returns a list of administrators of the chat with their custom titles
     *
     * @chatId - Chat identifier
     */
    public static class GetChatAdministrators extends Function {

        public long chatId;

        public GetChatAdministrators() {}

        public GetChatAdministrators(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 1544468155; }

    }


    /**
     * Clears draft messages in all chats
     *
     * @excludeSecretChats - If true, local draft messages in secret chats will not be cleared
     */
    public static class ClearAllDraftMessages extends Function {

        public boolean excludeSecretChats;

        public ClearAllDraftMessages() {}

        public ClearAllDraftMessages(boolean excludeSecretChats) {

            this.excludeSecretChats = excludeSecretChats;

        }

        @Override
        public int getConstructor() { return -46369573; }

    }


    /**
     * Returns list of chats with non-default notification settings
     *
     * @scope - If specified, only chats from the specified scope will be returned
     * @compareSound - If true, also chats with non-default sound will be returned
     */
    public static class GetChatNotificationSettingsExceptions extends Function {

        public NotificationSettingsScope scope;
        public boolean compareSound;

        public GetChatNotificationSettingsExceptions() {}

        public GetChatNotificationSettingsExceptions(NotificationSettingsScope scope, boolean compareSound) {

            this.scope = scope;
            this.compareSound = compareSound;

        }

        @Override
        public int getConstructor() { return 201199121; }

    }


    /**
     * Returns the notification settings for chats of a given type
     *
     * @scope - Types of chats for which to return the notification settings information
     */
    public static class GetScopeNotificationSettings extends Function {

        public NotificationSettingsScope scope;

        public GetScopeNotificationSettings() {}

        public GetScopeNotificationSettings(NotificationSettingsScope scope) {

            this.scope = scope;

        }

        @Override
        public int getConstructor() { return -995613361; }

    }


    /**
     * Changes notification settings for chats of a given type
     *
     * @scope - Types of chats for which to change the notification settings
     * @notificationSettings - The new notification settings for the given scope
     */
    public static class SetScopeNotificationSettings extends Function {

        public NotificationSettingsScope scope;
        public ScopeNotificationSettings notificationSettings;

        public SetScopeNotificationSettings() {}

        public SetScopeNotificationSettings(NotificationSettingsScope scope, ScopeNotificationSettings notificationSettings) {

            this.scope = scope;
            this.notificationSettings = notificationSettings;

        }

        @Override
        public int getConstructor() { return -2049984966; }

    }


    /**
     * Resets all notification settings to their default values
     * By default, all chats are unmuted, the sound is set to "default" and message previews are shown
     */
    public static class ResetAllNotificationSettings extends Function {

        @Override
        public int getConstructor() { return -174020359; }

    }


    /**
     * Changes the pinned state of a chat
     * There can be up to GetOption("pinned_chat_count_max")/GetOption("pinned_archived_chat_count_max") pinned non-secret chats and the same number of secret chats in the main/arhive chat list
     *
     * @chatList - Chat list in which to change the pinned state of the chat
     * @chatId - Chat identifier
     * @isPinned - True, if the chat is pinned
     */
    public static class ToggleChatIsPinned extends Function {

        public ChatList chatList;
        public long chatId;
        public boolean isPinned;

        public ToggleChatIsPinned() {}

        public ToggleChatIsPinned(ChatList chatList, long chatId, boolean isPinned) {

            this.chatList = chatList;
            this.chatId = chatId;
            this.isPinned = isPinned;

        }

        @Override
        public int getConstructor() { return -1485429186; }

    }


    /**
     * Changes the order of pinned chats
     *
     * @chatList - Chat list in which to change the order of pinned chats
     * @chatIds - The new list of pinned chats
     */
    public static class SetPinnedChats extends Function {

        public ChatList chatList;
        public long[] chatIds;

        public SetPinnedChats() {}

        public SetPinnedChats(ChatList chatList, long[] chatIds) {

            this.chatList = chatList;
            this.chatIds = chatIds;

        }

        @Override
        public int getConstructor() { return -695640000; }

    }


    /**
     * Downloads a file from the cloud
     * Download progress and completion of the download will be notified through updateFile updates
     *
     * @fileId - Identifier of the file to download
     * @priority - Priority of the download (1-32)
     *             The higher the priority, the earlier the file will be downloaded
     *             If the priorities of two files are equal, then the last one for which downloadFile was called will be downloaded first
     * @offset - The starting position from which the file should be downloaded
     * @limit - Number of bytes which should be downloaded starting from the "offset" position before the download will be automatically cancelled
     *          Use 0 to download without a limit
     * @synchronous - If false, this request returns file state just after the download has been started
     *                If true, this request returns file state only after the download has succeeded, has failed, has been cancelled or a new downloadFile request with different offset/limit parameters was sent
     */
    public static class DownloadFile extends Function {

        public int fileId;
        public int priority;
        public int offset;
        public int limit;
        public boolean synchronous;

        public DownloadFile() {}

        public DownloadFile(int fileId, int priority, int offset, int limit, boolean synchronous) {

            this.fileId = fileId;
            this.priority = priority;
            this.offset = offset;
            this.limit = limit;
            this.synchronous = synchronous;

        }

        @Override
        public int getConstructor() { return -1102026662; }

    }


    /**
     * Returns file downloaded prefix size from a given offset
     *
     * @fileId - Identifier of the file
     * @offset - Offset from which downloaded prefix size should be calculated
     */
    public static class GetFileDownloadedPrefixSize extends Function {

        public int fileId;
        public int offset;

        public GetFileDownloadedPrefixSize() {}

        public GetFileDownloadedPrefixSize(int fileId, int offset) {

            this.fileId = fileId;
            this.offset = offset;

        }

        @Override
        public int getConstructor() { return -1668864864; }

    }


    /**
     * Stops the downloading of a file
     * If a file has already been downloaded, does nothing
     *
     * @fileId - Identifier of a file to stop downloading
     * @onlyIfPending - Pass true to stop downloading only if it hasn't been started, i.e
     *                  Request hasn't been sent to server
     */
    public static class CancelDownloadFile extends Function {

        public int fileId;
        public boolean onlyIfPending;

        public CancelDownloadFile() {}

        public CancelDownloadFile(int fileId, boolean onlyIfPending) {

            this.fileId = fileId;
            this.onlyIfPending = onlyIfPending;

        }

        @Override
        public int getConstructor() { return -1954524450; }

    }


    /**
     * Asynchronously uploads a file to the cloud without sending it in a message
     * UpdateFile will be used to notify about upload progress and successful completion of the upload
     * The file will not have a persistent remote identifier until it will be sent in a message
     *
     * @file - File to upload
     * @fileType - File type
     * @priority - Priority of the upload (1-32)
     *             The higher the priority, the earlier the file will be uploaded
     *             If the priorities of two files are equal, then the first one for which uploadFile was called will be uploaded first
     */
    public static class UploadFile extends Function {

        public InputFile file;
        public FileType fileType;
        public int priority;

        public UploadFile() {}

        public UploadFile(InputFile file, FileType fileType, int priority) {

            this.file = file;
            this.fileType = fileType;
            this.priority = priority;

        }

        @Override
        public int getConstructor() { return -745597786; }

    }


    /**
     * Stops the uploading of a file
     * Supported only for files uploaded by using uploadFile
     * For other files the behavior is undefined
     *
     * @fileId - Identifier of the file to stop uploading
     */
    public static class CancelUploadFile extends Function {

        public int fileId;

        public CancelUploadFile() {}

        public CancelUploadFile(int fileId) {

            this.fileId = fileId;

        }

        @Override
        public int getConstructor() { return 1623539600; }

    }


    /**
     * Writes a part of a generated file
     * This method is intended to be used only if the application has no direct access to TDLib's file system, because it is usually slower than a direct write to the destination file
     *
     * @generationId - The identifier of the generation process
     * @offset - The offset from which to write the data to the file
     * @data - The data to write
     */
    public static class WriteGeneratedFilePart extends Function {

        public long generationId;
        public int offset;
        public byte[] data;

        public WriteGeneratedFilePart() {}

        public WriteGeneratedFilePart(long generationId, int offset, byte[] data) {

            this.generationId = generationId;
            this.offset = offset;
            this.data = data;

        }

        @Override
        public int getConstructor() { return -2062358189; }

    }


    /**
     * Informs TDLib on a file generation progress
     *
     * @generationId - The identifier of the generation process
     * @expectedSize - Expected size of the generated file, in bytes
     *                 0 if unknown
     * @localPrefixSize - The number of bytes already generated
     */
    public static class SetFileGenerationProgress extends Function {

        public long generationId;
        public int expectedSize;
        public int localPrefixSize;

        public SetFileGenerationProgress() {}

        public SetFileGenerationProgress(long generationId, int expectedSize, int localPrefixSize) {

            this.generationId = generationId;
            this.expectedSize = expectedSize;
            this.localPrefixSize = localPrefixSize;

        }

        @Override
        public int getConstructor() { return -540459953; }

    }


    /**
     * Finishes the file generation
     *
     * @generationId - The identifier of the generation process
     * @error - If set, means that file generation has failed and should be terminated
     */
    public static class FinishFileGeneration extends Function {

        public long generationId;
        public Error error;

        public FinishFileGeneration() {}

        public FinishFileGeneration(long generationId, Error error) {

            this.generationId = generationId;
            this.error = error;

        }

        @Override
        public int getConstructor() { return -1055060835; }

    }


    /**
     * Reads a part of a file from the TDLib file cache and returns read bytes
     * This method is intended to be used only if the application has no direct access to TDLib's file system, because it is usually slower than a direct read from the file
     *
     * @fileId - Identifier of the file
     *           The file must be located in the TDLib file cache
     * @offset - The offset from which to read the file
     * @count - Number of bytes to read
     *          An error will be returned if there are not enough bytes available in the file from the specified position
     *          Pass 0 to read all available data from the specified position
     */
    public static class ReadFilePart extends Function {

        public int fileId;
        public int offset;
        public int count;

        public ReadFilePart() {}

        public ReadFilePart(int fileId, int offset, int count) {

            this.fileId = fileId;
            this.offset = offset;
            this.count = count;

        }

        @Override
        public int getConstructor() { return -407749314; }

    }


    /**
     * Deletes a file from the TDLib file cache
     *
     * @fileId - Identifier of the file to delete
     */
    public static class DeleteFile extends Function {

        public int fileId;

        public DeleteFile() {}

        public DeleteFile(int fileId) {

            this.fileId = fileId;

        }

        @Override
        public int getConstructor() { return 1807653676; }

    }


    /**
     * Generates a new invite link for a chat
     * The previously generated link is revoked
     * Available for basic groups, supergroups, and channels
     * Requires administrator privileges and can_invite_users right
     *
     * @chatId - Chat identifier
     */
    public static class GenerateChatInviteLink extends Function {

        public long chatId;

        public GenerateChatInviteLink() {}

        public GenerateChatInviteLink(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return 1945532500; }

    }


    /**
     * Checks the validity of an invite link for a chat and returns information about the corresponding chat
     *
     * @inviteLink - Invite link to be checked
     */
    public static class CheckChatInviteLink extends Function {

        public String inviteLink;

        public CheckChatInviteLink() {}

        public CheckChatInviteLink(String inviteLink) {

            this.inviteLink = inviteLink;

        }

        @Override
        public int getConstructor() { return -496940997; }

    }


    /**
     * Uses an invite link to add the current user to the chat if possible
     * The new member will not be added until the chat state has been synchronized with the server
     *
     * @inviteLink - Invite link to import
     */
    public static class JoinChatByInviteLink extends Function {

        public String inviteLink;

        public JoinChatByInviteLink() {}

        public JoinChatByInviteLink(String inviteLink) {

            this.inviteLink = inviteLink;

        }

        @Override
        public int getConstructor() { return -1049973882; }

    }


    /**
     * Creates a new call
     *
     * @userId - Identifier of the user to be called
     * @protocol - Description of the call protocols supported by the application
     * @isVideo - True, if a video call needs to be created
     */
    public static class CreateCall extends Function {

        public int userId;
        public CallProtocol protocol;
        public boolean isVideo;

        public CreateCall() {}

        public CreateCall(int userId, CallProtocol protocol, boolean isVideo) {

            this.userId = userId;
            this.protocol = protocol;
            this.isVideo = isVideo;

        }

        @Override
        public int getConstructor() { return 1837533340; }

    }


    /**
     * Accepts an incoming call
     *
     * @callId - Call identifier
     * @protocol - Description of the call protocols supported by the application
     */
    public static class AcceptCall extends Function {

        public int callId;
        public CallProtocol protocol;

        public AcceptCall() {}

        public AcceptCall(int callId, CallProtocol protocol) {

            this.callId = callId;
            this.protocol = protocol;

        }

        @Override
        public int getConstructor() { return -646618416; }

    }


    /**
     * Sends call signaling data
     *
     * @callId - Call identifier
     * @data - The data
     */
    public static class SendCallSignalingData extends Function {

        public int callId;
        public byte[] data;

        public SendCallSignalingData() {}

        public SendCallSignalingData(int callId, byte[] data) {

            this.callId = callId;
            this.data = data;

        }

        @Override
        public int getConstructor() { return 1412280732; }

    }


    /**
     * Discards a call
     *
     * @callId - Call identifier
     * @isDisconnected - True, if the user was disconnected
     * @duration - The call duration, in seconds
     * @isVideo - True, if the call was a video call
     * @connectionId - Identifier of the connection used during the call
     */
    public static class DiscardCall extends Function {

        public int callId;
        public boolean isDisconnected;
        public int duration;
        public boolean isVideo;
        public long connectionId;

        public DiscardCall() {}

        public DiscardCall(int callId, boolean isDisconnected, int duration, boolean isVideo, long connectionId) {

            this.callId = callId;
            this.isDisconnected = isDisconnected;
            this.duration = duration;
            this.isVideo = isVideo;
            this.connectionId = connectionId;

        }

        @Override
        public int getConstructor() { return -1784044162; }

    }


    /**
     * Sends a call rating
     *
     * @callId - Call identifier
     * @rating - Call rating
     * @comment - An optional user comment if the rating is less than 5
     * @problems - List of the exact types of problems with the call, specified by the user
     */
    public static class SendCallRating extends Function {

        public int callId;
        public int rating;
        public String comment;
        public CallProblem[] problems;

        public SendCallRating() {}

        public SendCallRating(int callId, int rating, String comment, CallProblem[] problems) {

            this.callId = callId;
            this.rating = rating;
            this.comment = comment;
            this.problems = problems;

        }

        @Override
        public int getConstructor() { return -1402719502; }

    }


    /**
     * Sends debug information for a call
     *
     * @callId - Call identifier
     * @debugInformation - Debug information in application-specific format
     */
    public static class SendCallDebugInformation extends Function {

        public int callId;
        public String debugInformation;

        public SendCallDebugInformation() {}

        public SendCallDebugInformation(int callId, String debugInformation) {

            this.callId = callId;
            this.debugInformation = debugInformation;

        }

        @Override
        public int getConstructor() { return 2019243839; }

    }


    /**
     * Blocks an original sender of a message in the Replies chat
     *
     * @messageId - The identifier of an incoming message in the Replies chat
     * @deleteMessage - Pass true if the message must be deleted
     * @deleteAllMessages - Pass true if all messages from the same sender must be deleted
     * @reportSpam - Pass true if the sender must be reported to the Telegram moderators
     */
    public static class BlockChatFromReplies extends Function {

        public long messageId;
        public boolean deleteMessage;
        public boolean deleteAllMessages;
        public boolean reportSpam;

        public BlockChatFromReplies() {}

        public BlockChatFromReplies(long messageId, boolean deleteMessage, boolean deleteAllMessages, boolean reportSpam) {

            this.messageId = messageId;
            this.deleteMessage = deleteMessage;
            this.deleteAllMessages = deleteAllMessages;
            this.reportSpam = reportSpam;

        }

        @Override
        public int getConstructor() { return -869244252; }

    }


    /**
     * Returns chats that were blocked by the current user
     *
     * @offset - Number of chats to skip in the result
     * @limit - The maximum number of chats to return
     */
    public static class GetBlockedChats extends Function {

        public int offset;
        public int limit;

        public GetBlockedChats() {}

        public GetBlockedChats(int offset, int limit) {

            this.offset = offset;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -733106443; }

    }


    /**
     * Adds a user to the contact list or edits an existing contact by their user identifier
     *
     * @contact - The contact to add or edit
     *            Phone number can be empty and needs to be specified only if known, vCard is ignored
     * @sharePhoneNumber - True, if the new contact needs to be allowed to see current user's phone number
     *                     A corresponding rule to userPrivacySettingShowPhoneNumber will be added if needed
     *                     Use the field UserFullInfo.need_phone_number_privacy_exception to check whether the current user needs to be asked to share their phone number
     */
    public static class AddContact extends Function {

        public Contact contact;
        public boolean sharePhoneNumber;

        public AddContact() {}

        public AddContact(Contact contact, boolean sharePhoneNumber) {

            this.contact = contact;
            this.sharePhoneNumber = sharePhoneNumber;

        }

        @Override
        public int getConstructor() { return 1869640000; }

    }


    /**
     * Adds new contacts or edits existing contacts by their phone numbers
     * Contacts' user identifiers are ignored
     *
     * @contacts - The list of contacts to import or edit
     *             Contacts' vCard are ignored and are not imported
     */
    public static class ImportContacts extends Function {

        public Contact[] contacts;

        public ImportContacts() {}

        public ImportContacts(Contact[] contacts) {

            this.contacts = contacts;

        }

        @Override
        public int getConstructor() { return -215132767; }

    }


    /**
     * Returns all user contacts
     */
    public static class GetContacts extends Function {

        @Override
        public int getConstructor() { return -1417722768; }

    }


    /**
     * Searches for the specified query in the first names, last names and usernames of the known user contacts
     *
     * @query - Query to search for
     *          May be empty to return all contacts
     * @limit - The maximum number of users to be returned
     */
    public static class SearchContacts extends Function {

        public String query;
        public int limit;

        public SearchContacts() {}

        public SearchContacts(String query, int limit) {

            this.query = query;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -1794690715; }

    }


    /**
     * Removes users from the contact list
     *
     * @userIds - Identifiers of users to be deleted
     */
    public static class RemoveContacts extends Function {

        public int[] userIds;

        public RemoveContacts() {}

        public RemoveContacts(int[] userIds) {

            this.userIds = userIds;

        }

        @Override
        public int getConstructor() { return -615510759; }

    }


    /**
     * Returns the total number of imported contacts
     */
    public static class GetImportedContactCount extends Function {

        @Override
        public int getConstructor() { return -656336346; }

    }


    /**
     * Changes imported contacts using the list of current user contacts saved on the device
     * Imports newly added contacts and, if at least the file database is enabled, deletes recently deleted contacts
     * Query result depends on the result of the previous query, so only one query is possible at the same time
     *
     * @contacts - The new list of contacts, contact's vCard are ignored and are not imported
     */
    public static class ChangeImportedContacts extends Function {

        public Contact[] contacts;

        public ChangeImportedContacts() {}

        public ChangeImportedContacts(Contact[] contacts) {

            this.contacts = contacts;

        }

        @Override
        public int getConstructor() { return 1968207955; }

    }


    /**
     * Clears all imported contacts, contact list remains unchanged
     */
    public static class ClearImportedContacts extends Function {

        @Override
        public int getConstructor() { return 869503298; }

    }


    /**
     * Shares the phone number of the current user with a mutual contact
     * Supposed to be called when the user clicks on chatActionBarSharePhoneNumber
     *
     * @userId - Identifier of the user with whom to share the phone number
     *           The user must be a mutual contact
     */
    public static class SharePhoneNumber extends Function {

        public int userId;

        public SharePhoneNumber() {}

        public SharePhoneNumber(int userId) {

            this.userId = userId;

        }

        @Override
        public int getConstructor() { return -370669878; }

    }


    /**
     * Returns the profile photos of a user
     * The result of this query may be outdated: some photos might have been deleted already
     *
     * @userId - User identifier
     * @offset - The number of photos to skip
     * @limit - The maximum number of photos to be returned
     */
    public static class GetUserProfilePhotos extends Function {

        public int userId;
        public int offset;
        public int limit;

        public GetUserProfilePhotos() {}

        public GetUserProfilePhotos(int userId, int offset, int limit) {

            this.userId = userId;
            this.offset = offset;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -768699141; }

    }


    /**
     * Returns stickers from the installed sticker sets that correspond to a given emoji
     * If the emoji is not empty, favorite and recently used stickers may also be returned
     *
     * @emoji - String representation of emoji
     *          If empty, returns all known installed stickers
     * @limit - The maximum number of stickers to be returned
     */
    public static class GetStickers extends Function {

        public String emoji;
        public int limit;

        public GetStickers() {}

        public GetStickers(String emoji, int limit) {

            this.emoji = emoji;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -1594919556; }

    }


    /**
     * Searches for stickers from public sticker sets that correspond to a given emoji
     *
     * @emoji - String representation of emoji
     * @limit - The maximum number of stickers to be returned
     */
    public static class SearchStickers extends Function {

        public String emoji;
        public int limit;

        public SearchStickers() {}

        public SearchStickers(String emoji, int limit) {

            this.emoji = emoji;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return 1555771203; }

    }


    /**
     * Returns a list of installed sticker sets
     *
     * @isMasks - Pass true to return mask sticker sets
     *            Pass false to return ordinary sticker sets
     */
    public static class GetInstalledStickerSets extends Function {

        public boolean isMasks;

        public GetInstalledStickerSets() {}

        public GetInstalledStickerSets(boolean isMasks) {

            this.isMasks = isMasks;

        }

        @Override
        public int getConstructor() { return 1214523749; }

    }


    /**
     * Returns a list of archived sticker sets
     *
     * @isMasks - Pass true to return mask stickers sets
     *            Pass false to return ordinary sticker sets
     * @offsetStickerSetId - Identifier of the sticker set from which to return the result
     * @limit - The maximum number of sticker sets to return
     */
    public static class GetArchivedStickerSets extends Function {

        public boolean isMasks;
        public long offsetStickerSetId;
        public int limit;

        public GetArchivedStickerSets() {}

        public GetArchivedStickerSets(boolean isMasks, long offsetStickerSetId, int limit) {

            this.isMasks = isMasks;
            this.offsetStickerSetId = offsetStickerSetId;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return 1996943238; }

    }


    /**
     * Returns a list of trending sticker sets
     * For the optimal performance the number of returned sticker sets is chosen by the library
     *
     * @offset - The offset from which to return the sticker sets
     * @limit - The maximum number of sticker sets to be returned
     *          Fewer sticker sets may be returned than specified by the limit, even if the end of the list has not been reached
     */
    public static class GetTrendingStickerSets extends Function {

        public int offset;
        public int limit;

        public GetTrendingStickerSets() {}

        public GetTrendingStickerSets(int offset, int limit) {

            this.offset = offset;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return -1494581948; }

    }


    /**
     * Returns a list of sticker sets attached to a file
     * Currently only photos and videos can have attached sticker sets
     *
     * @fileId - File identifier
     */
    public static class GetAttachedStickerSets extends Function {

        public int fileId;

        public GetAttachedStickerSets() {}

        public GetAttachedStickerSets(int fileId) {

            this.fileId = fileId;

        }

        @Override
        public int getConstructor() { return 1302172429; }

    }


    /**
     * Returns information about a sticker set by its identifier
     *
     * @setId - Identifier of the sticker set
     */
    public static class GetStickerSet extends Function {

        public long setId;

        public GetStickerSet() {}

        public GetStickerSet(long setId) {

            this.setId = setId;

        }

        @Override
        public int getConstructor() { return 1052318659; }

    }


    /**
     * Searches for a sticker set by its name
     *
     * @name - Name of the sticker set
     */
    public static class SearchStickerSet extends Function {

        public String name;

        public SearchStickerSet() {}

        public SearchStickerSet(String name) {

            this.name = name;

        }

        @Override
        public int getConstructor() { return 1157930222; }

    }


    /**
     * Searches for installed sticker sets by looking for specified query in their title and name
     *
     * @isMasks - Pass true to return mask sticker sets
     *            Pass false to return ordinary sticker sets
     * @query - Query to search for
     * @limit - The maximum number of sticker sets to return
     */
    public static class SearchInstalledStickerSets extends Function {

        public boolean isMasks;
        public String query;
        public int limit;

        public SearchInstalledStickerSets() {}

        public SearchInstalledStickerSets(boolean isMasks, String query, int limit) {

            this.isMasks = isMasks;
            this.query = query;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return 681171344; }

    }


    /**
     * Searches for ordinary sticker sets by looking for specified query in their title and name
     * Excludes installed sticker sets from the results
     *
     * @query - Query to search for
     */
    public static class SearchStickerSets extends Function {

        public String query;

        public SearchStickerSets() {}

        public SearchStickerSets(String query) {

            this.query = query;

        }

        @Override
        public int getConstructor() { return -1082314629; }

    }


    /**
     * Installs/uninstalls or activates/archives a sticker set
     *
     * @setId - Identifier of the sticker set
     * @isInstalled - The new value of is_installed
     * @isArchived - The new value of is_archived
     *               A sticker set can't be installed and archived simultaneously
     */
    public static class ChangeStickerSet extends Function {

        public long setId;
        public boolean isInstalled;
        public boolean isArchived;

        public ChangeStickerSet() {}

        public ChangeStickerSet(long setId, boolean isInstalled, boolean isArchived) {

            this.setId = setId;
            this.isInstalled = isInstalled;
            this.isArchived = isArchived;

        }

        @Override
        public int getConstructor() { return 449357293; }

    }


    /**
     * Informs the server that some trending sticker sets have been viewed by the user
     *
     * @stickerSetIds - Identifiers of viewed trending sticker sets
     */
    public static class ViewTrendingStickerSets extends Function {

        public long[] stickerSetIds;

        public ViewTrendingStickerSets() {}

        public ViewTrendingStickerSets(long[] stickerSetIds) {

            this.stickerSetIds = stickerSetIds;

        }

        @Override
        public int getConstructor() { return -952416520; }

    }


    /**
     * Changes the order of installed sticker sets
     *
     * @isMasks - Pass true to change the order of mask sticker sets
     *            Pass false to change the order of ordinary sticker sets
     * @stickerSetIds - Identifiers of installed sticker sets in the new correct order
     */
    public static class ReorderInstalledStickerSets extends Function {

        public boolean isMasks;
        public long[] stickerSetIds;

        public ReorderInstalledStickerSets() {}

        public ReorderInstalledStickerSets(boolean isMasks, long[] stickerSetIds) {

            this.isMasks = isMasks;
            this.stickerSetIds = stickerSetIds;

        }

        @Override
        public int getConstructor() { return 1114537563; }

    }


    /**
     * Returns a list of recently used stickers
     *
     * @isAttached - Pass true to return stickers and masks that were recently attached to photos or video files
     *               Pass false to return recently sent stickers
     */
    public static class GetRecentStickers extends Function {

        public boolean isAttached;

        public GetRecentStickers() {}

        public GetRecentStickers(boolean isAttached) {

            this.isAttached = isAttached;

        }

        @Override
        public int getConstructor() { return -579622241; }

    }


    /**
     * Manually adds a new sticker to the list of recently used stickers
     * The new sticker is added to the top of the list
     * If the sticker was already in the list, it is removed from the list first
     * Only stickers belonging to a sticker set can be added to this list
     *
     * @isAttached - Pass true to add the sticker to the list of stickers recently attached to photo or video files
     *               Pass false to add the sticker to the list of recently sent stickers
     * @sticker - Sticker file to add
     */
    public static class AddRecentSticker extends Function {

        public boolean isAttached;
        public InputFile sticker;

        public AddRecentSticker() {}

        public AddRecentSticker(boolean isAttached, InputFile sticker) {

            this.isAttached = isAttached;
            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return -1478109026; }

    }


    /**
     * Removes a sticker from the list of recently used stickers
     *
     * @isAttached - Pass true to remove the sticker from the list of stickers recently attached to photo or video files
     *               Pass false to remove the sticker from the list of recently sent stickers
     * @sticker - Sticker file to delete
     */
    public static class RemoveRecentSticker extends Function {

        public boolean isAttached;
        public InputFile sticker;

        public RemoveRecentSticker() {}

        public RemoveRecentSticker(boolean isAttached, InputFile sticker) {

            this.isAttached = isAttached;
            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return 1246577677; }

    }


    /**
     * Clears the list of recently used stickers
     *
     * @isAttached - Pass true to clear the list of stickers recently attached to photo or video files
     *               Pass false to clear the list of recently sent stickers
     */
    public static class ClearRecentStickers extends Function {

        public boolean isAttached;

        public ClearRecentStickers() {}

        public ClearRecentStickers(boolean isAttached) {

            this.isAttached = isAttached;

        }

        @Override
        public int getConstructor() { return -321242684; }

    }


    /**
     * Returns favorite stickers
     */
    public static class GetFavoriteStickers extends Function {

        @Override
        public int getConstructor() { return -338964672; }

    }


    /**
     * Adds a new sticker to the list of favorite stickers
     * The new sticker is added to the top of the list
     * If the sticker was already in the list, it is removed from the list first
     * Only stickers belonging to a sticker set can be added to this list
     *
     * @sticker - Sticker file to add
     */
    public static class AddFavoriteSticker extends Function {

        public InputFile sticker;

        public AddFavoriteSticker() {}

        public AddFavoriteSticker(InputFile sticker) {

            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return 324504799; }

    }


    /**
     * Removes a sticker from the list of favorite stickers
     *
     * @sticker - Sticker file to delete from the list
     */
    public static class RemoveFavoriteSticker extends Function {

        public InputFile sticker;

        public RemoveFavoriteSticker() {}

        public RemoveFavoriteSticker(InputFile sticker) {

            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return 1152945264; }

    }


    /**
     * Returns emoji corresponding to a sticker
     * The list is only for informational purposes, because a sticker is always sent with a fixed emoji from the corresponding Sticker object
     *
     * @sticker - Sticker file identifier
     */
    public static class GetStickerEmojis extends Function {

        public InputFile sticker;

        public GetStickerEmojis() {}

        public GetStickerEmojis(InputFile sticker) {

            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return -1895508665; }

    }


    /**
     * Searches for emojis by keywords
     * Supported only if the file database is enabled
     *
     * @text - Text to search for
     * @exactMatch - True, if only emojis, which exactly match text needs to be returned
     * @inputLanguageCodes - List of possible IETF language tags of the user's input language
     *                       May be empty if unknown
     */
    public static class SearchEmojis extends Function {

        public String text;
        public boolean exactMatch;
        public String[] inputLanguageCodes;

        public SearchEmojis() {}

        public SearchEmojis(String text, boolean exactMatch, String[] inputLanguageCodes) {

            this.text = text;
            this.exactMatch = exactMatch;
            this.inputLanguageCodes = inputLanguageCodes;

        }

        @Override
        public int getConstructor() { return 398837927; }

    }


    /**
     * Returns an HTTP URL which can be used to automatically log in to the translation platform and suggest new emoji replacements
     * The URL will be valid for 30 seconds after generation
     *
     * @languageCode - Language code for which the emoji replacements will be suggested
     */
    public static class GetEmojiSuggestionsUrl extends Function {

        public String languageCode;

        public GetEmojiSuggestionsUrl() {}

        public GetEmojiSuggestionsUrl(String languageCode) {

            this.languageCode = languageCode;

        }

        @Override
        public int getConstructor() { return -1404101841; }

    }


    /**
     * Returns saved animations
     */
    public static class GetSavedAnimations extends Function {

        @Override
        public int getConstructor() { return 7051032; }

    }


    /**
     * Manually adds a new animation to the list of saved animations
     * The new animation is added to the beginning of the list
     * If the animation was already in the list, it is removed first
     * Only non-secret video animations with MIME type "video/mp4" can be added to the list
     *
     * @animation - The animation file to be added
     *              Only animations known to the server (i.e
     *              Successfully sent via a message) can be added to the list
     */
    public static class AddSavedAnimation extends Function {

        public InputFile animation;

        public AddSavedAnimation() {}

        public AddSavedAnimation(InputFile animation) {

            this.animation = animation;

        }

        @Override
        public int getConstructor() { return -1538525088; }

    }


    /**
     * Removes an animation from the list of saved animations
     *
     * @animation - Animation file to be removed
     */
    public static class RemoveSavedAnimation extends Function {

        public InputFile animation;

        public RemoveSavedAnimation() {}

        public RemoveSavedAnimation(InputFile animation) {

            this.animation = animation;

        }

        @Override
        public int getConstructor() { return -495605479; }

    }


    /**
     * Returns up to 20 recently used inline bots in the order of their last usage
     */
    public static class GetRecentInlineBots extends Function {

        @Override
        public int getConstructor() { return 1437823548; }

    }


    /**
     * Searches for recently used hashtags by their prefix
     *
     * @prefix - Hashtag prefix to search for
     * @limit - The maximum number of hashtags to be returned
     */
    public static class SearchHashtags extends Function {

        public String prefix;
        public int limit;

        public SearchHashtags() {}

        public SearchHashtags(String prefix, int limit) {

            this.prefix = prefix;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return 1043637617; }

    }


    /**
     * Removes a hashtag from the list of recently used hashtags
     *
     * @hashtag - Hashtag to delete
     */
    public static class RemoveRecentHashtag extends Function {

        public String hashtag;

        public RemoveRecentHashtag() {}

        public RemoveRecentHashtag(String hashtag) {

            this.hashtag = hashtag;

        }

        @Override
        public int getConstructor() { return -1013735260; }

    }


    /**
     * Returns a web page preview by the text of the message
     * Do not call this function too often
     * Returns a 404 error if the web page has no preview
     *
     * @text - Message text with formatting
     */
    public static class GetWebPagePreview extends Function {

        public FormattedText text;

        public GetWebPagePreview() {}

        public GetWebPagePreview(FormattedText text) {

            this.text = text;

        }

        @Override
        public int getConstructor() { return 573441580; }

    }


    /**
     * Returns an instant view version of a web page if available
     * Returns a 404 error if the web page has no instant view page
     *
     * @url - The web page URL
     * @forceFull - If true, the full instant view for the web page will be returned
     */
    public static class GetWebPageInstantView extends Function {

        public String url;
        public boolean forceFull;

        public GetWebPageInstantView() {}

        public GetWebPageInstantView(String url, boolean forceFull) {

            this.url = url;
            this.forceFull = forceFull;

        }

        @Override
        public int getConstructor() { return -1962649975; }

    }


    /**
     * Changes a profile photo for the current user
     *
     * @photo - Profile photo to set
     */
    public static class SetProfilePhoto extends Function {

        public InputChatPhoto photo;

        public SetProfilePhoto() {}

        public SetProfilePhoto(InputChatPhoto photo) {

            this.photo = photo;

        }

        @Override
        public int getConstructor() { return -2069678882; }

    }


    /**
     * Deletes a profile photo
     *
     * @profilePhotoId - Identifier of the profile photo to delete
     */
    public static class DeleteProfilePhoto extends Function {

        public long profilePhotoId;

        public DeleteProfilePhoto() {}

        public DeleteProfilePhoto(long profilePhotoId) {

            this.profilePhotoId = profilePhotoId;

        }

        @Override
        public int getConstructor() { return 1319794625; }

    }


    /**
     * Changes the first and last name of the current user
     *
     * @firstName - The new value of the first name for the user
     * @lastName - The new value of the optional last name for the user
     */
    public static class SetName extends Function {

        public String firstName;
        public String lastName;

        public SetName() {}

        public SetName(String firstName, String lastName) {

            this.firstName = firstName;
            this.lastName = lastName;

        }

        @Override
        public int getConstructor() { return 1711693584; }

    }


    /**
     * Changes the bio of the current user
     *
     * @bio - The new value of the user bio
     */
    public static class SetBio extends Function {

        public String bio;

        public SetBio() {}

        public SetBio(String bio) {

            this.bio = bio;

        }

        @Override
        public int getConstructor() { return -1619582124; }

    }


    /**
     * Changes the username of the current user
     *
     * @username - The new value of the username
     *             Use an empty string to remove the username
     */
    public static class SetUsername extends Function {

        public String username;

        public SetUsername() {}

        public SetUsername(String username) {

            this.username = username;

        }

        @Override
        public int getConstructor() { return 439901214; }

    }


    /**
     * Changes the location of the current user
     * Needs to be called if GetOption("is_location_visible") is true and location changes for more than 1 kilometer
     *
     * @location - The new location of the user
     */
    public static class SetLocation extends Function {

        public Location location;

        public SetLocation() {}

        public SetLocation(Location location) {

            this.location = location;

        }

        @Override
        public int getConstructor() { return 93926257; }

    }


    /**
     * Changes the phone number of the user and sends an authentication code to the user's new phone number
     * On success, returns information about the sent code
     *
     * @phoneNumber - The new phone number of the user in international format
     * @settings - Settings for the authentication of the user's phone number
     */
    public static class ChangePhoneNumber extends Function {

        public String phoneNumber;
        public PhoneNumberAuthenticationSettings settings;

        public ChangePhoneNumber() {}

        public ChangePhoneNumber(String phoneNumber, PhoneNumberAuthenticationSettings settings) {

            this.phoneNumber = phoneNumber;
            this.settings = settings;

        }

        @Override
        public int getConstructor() { return -124666973; }

    }


    /**
     * Re-sends the authentication code sent to confirm a new phone number for the user
     * Works only if the previously received authenticationCodeInfo next_code_type was not null
     */
    public static class ResendChangePhoneNumberCode extends Function {

        @Override
        public int getConstructor() { return -786772060; }

    }


    /**
     * Checks the authentication code sent to confirm a new phone number of the user
     *
     * @code - Verification code received by SMS, phone call or flash call
     */
    public static class CheckChangePhoneNumberCode extends Function {

        public String code;

        public CheckChangePhoneNumberCode() {}

        public CheckChangePhoneNumberCode(String code) {

            this.code = code;

        }

        @Override
        public int getConstructor() { return -1720278429; }

    }


    /**
     * Sets the list of commands supported by the bot
     * For bots only
     *
     * @commands - List of the bot's commands
     */
    public static class SetCommands extends Function {

        public BotCommand[] commands;

        public SetCommands() {}

        public SetCommands(BotCommand[] commands) {

            this.commands = commands;

        }

        @Override
        public int getConstructor() { return 355010146; }

    }


    /**
     * Returns all active sessions of the current user
     */
    public static class GetActiveSessions extends Function {

        @Override
        public int getConstructor() { return 1119710526; }

    }


    /**
     * Terminates a session of the current user
     *
     * @sessionId - Session identifier
     */
    public static class TerminateSession extends Function {

        public long sessionId;

        public TerminateSession() {}

        public TerminateSession(long sessionId) {

            this.sessionId = sessionId;

        }

        @Override
        public int getConstructor() { return -407385812; }

    }


    /**
     * Terminates all other sessions of the current user
     */
    public static class TerminateAllOtherSessions extends Function {

        @Override
        public int getConstructor() { return 1874485523; }

    }


    /**
     * Returns all website where the current user used Telegram to log in
     */
    public static class GetConnectedWebsites extends Function {

        @Override
        public int getConstructor() { return -170536110; }

    }


    /**
     * Disconnects website from the current user's Telegram account
     *
     * @websiteId - Website identifier
     */
    public static class DisconnectWebsite extends Function {

        public long websiteId;

        public DisconnectWebsite() {}

        public DisconnectWebsite(long websiteId) {

            this.websiteId = websiteId;

        }

        @Override
        public int getConstructor() { return -778767395; }

    }


    /**
     * Disconnects all websites from the current user's Telegram account
     */
    public static class DisconnectAllWebsites extends Function {

        @Override
        public int getConstructor() { return -1082985981; }

    }


    /**
     * Changes the username of a supergroup or channel, requires owner privileges in the supergroup or channel
     *
     * @supergroupId - Identifier of the supergroup or channel
     * @username - New value of the username
     *             Use an empty string to remove the username
     */
    public static class SetSupergroupUsername extends Function {

        public int supergroupId;
        public String username;

        public SetSupergroupUsername() {}

        public SetSupergroupUsername(int supergroupId, String username) {

            this.supergroupId = supergroupId;
            this.username = username;

        }

        @Override
        public int getConstructor() { return -1428333122; }

    }


    /**
     * Changes the sticker set of a supergroup
     * Requires can_change_info rights
     *
     * @supergroupId - Identifier of the supergroup
     * @stickerSetId - New value of the supergroup sticker set identifier
     *                 Use 0 to remove the supergroup sticker set
     */
    public static class SetSupergroupStickerSet extends Function {

        public int supergroupId;
        public long stickerSetId;

        public SetSupergroupStickerSet() {}

        public SetSupergroupStickerSet(int supergroupId, long stickerSetId) {

            this.supergroupId = supergroupId;
            this.stickerSetId = stickerSetId;

        }

        @Override
        public int getConstructor() { return -295782298; }

    }


    /**
     * Toggles sender signatures messages sent in a channel
     * Requires can_change_info rights
     *
     * @supergroupId - Identifier of the channel
     * @signMessages - New value of sign_messages
     */
    public static class ToggleSupergroupSignMessages extends Function {

        public int supergroupId;
        public boolean signMessages;

        public ToggleSupergroupSignMessages() {}

        public ToggleSupergroupSignMessages(int supergroupId, boolean signMessages) {

            this.supergroupId = supergroupId;
            this.signMessages = signMessages;

        }

        @Override
        public int getConstructor() { return -558196581; }

    }


    /**
     * Toggles whether the message history of a supergroup is available to new members
     * Requires can_change_info rights
     *
     * @supergroupId - The identifier of the supergroup
     * @isAllHistoryAvailable - The new value of is_all_history_available
     */
    public static class ToggleSupergroupIsAllHistoryAvailable extends Function {

        public int supergroupId;
        public boolean isAllHistoryAvailable;

        public ToggleSupergroupIsAllHistoryAvailable() {}

        public ToggleSupergroupIsAllHistoryAvailable(int supergroupId, boolean isAllHistoryAvailable) {

            this.supergroupId = supergroupId;
            this.isAllHistoryAvailable = isAllHistoryAvailable;

        }

        @Override
        public int getConstructor() { return 1701526555; }

    }


    /**
     * Reports some messages from a user in a supergroup as spam
     * Requires administrator rights in the supergroup
     *
     * @supergroupId - Supergroup identifier
     * @userId - User identifier
     * @messageIds - Identifiers of messages sent in the supergroup by the user
     *               This list must be non-empty
     */
    public static class ReportSupergroupSpam extends Function {

        public int supergroupId;
        public int userId;
        public long[] messageIds;

        public ReportSupergroupSpam() {}

        public ReportSupergroupSpam(int supergroupId, int userId, long[] messageIds) {

            this.supergroupId = supergroupId;
            this.userId = userId;
            this.messageIds = messageIds;

        }

        @Override
        public int getConstructor() { return -2125451498; }

    }


    /**
     * Returns information about members or banned users in a supergroup or channel
     * Can be used only if SupergroupFullInfo.can_get_members == true
     * Additionally, administrator privileges may be required for some filters
     *
     * @supergroupId - Identifier of the supergroup or channel
     * @filter - The type of users to return
     *           By default, supergroupMembersRecent
     * @offset - Number of users to skip
     * @limit - The maximum number of users be returned
     *          Up to 200
     */
    public static class GetSupergroupMembers extends Function {

        public int supergroupId;
        public SupergroupMembersFilter filter;
        public int offset;
        public int limit;

        public GetSupergroupMembers() {}

        public GetSupergroupMembers(int supergroupId, SupergroupMembersFilter filter, int offset, int limit) {

            this.supergroupId = supergroupId;
            this.filter = filter;
            this.offset = offset;
            this.limit = limit;

        }

        @Override
        public int getConstructor() { return 1427643098; }

    }


    /**
     * Deletes a supergroup or channel along with all messages in the corresponding chat
     * This will release the supergroup or channel username and remove all members
     * Requires owner privileges in the supergroup or channel
     * Chats with more than 1000 members can't be deleted using this method
     *
     * @supergroupId - Identifier of the supergroup or channel
     */
    public static class DeleteSupergroup extends Function {

        public int supergroupId;

        public DeleteSupergroup() {}

        public DeleteSupergroup(int supergroupId) {

            this.supergroupId = supergroupId;

        }

        @Override
        public int getConstructor() { return -1999855965; }

    }


    /**
     * Closes a secret chat, effectively transferring its state to secretChatStateClosed
     *
     * @secretChatId - Secret chat identifier
     */
    public static class CloseSecretChat extends Function {

        public int secretChatId;

        public CloseSecretChat() {}

        public CloseSecretChat(int secretChatId) {

            this.secretChatId = secretChatId;

        }

        @Override
        public int getConstructor() { return -471006133; }

    }


    /**
     * Returns a list of service actions taken by chat members and administrators in the last 48 hours
     * Available only for supergroups and channels
     * Requires administrator rights
     * Returns results in reverse chronological order (i
     * E., in order of decreasing event_id)
     *
     * @chatId - Chat identifier
     * @query - Search query by which to filter events
     * @fromEventId - Identifier of an event from which to return results
     *                Use 0 to get results from the latest events
     * @limit - The maximum number of events to return
     * @filters - The types of events to return
     *            By default, all types will be returned
     * @userIds - User identifiers by which to filter events
     *            By default, events relating to all users will be returned
     */
    public static class GetChatEventLog extends Function {

        public long chatId;
        public String query;
        public long fromEventId;
        public int limit;
        public ChatEventLogFilters filters;
        public int[] userIds;

        public GetChatEventLog() {}

        public GetChatEventLog(long chatId, String query, long fromEventId, int limit, ChatEventLogFilters filters, int[] userIds) {

            this.chatId = chatId;
            this.query = query;
            this.fromEventId = fromEventId;
            this.limit = limit;
            this.filters = filters;
            this.userIds = userIds;

        }

        @Override
        public int getConstructor() { return 206900967; }

    }


    /**
     * Returns an invoice payment form
     * This method should be called when the user presses inlineKeyboardButtonBuy
     *
     * @chatId - Chat identifier of the Invoice message
     * @messageId - Message identifier
     */
    public static class GetPaymentForm extends Function {

        public long chatId;
        public long messageId;

        public GetPaymentForm() {}

        public GetPaymentForm(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return -2146950882; }

    }


    /**
     * Validates the order information provided by a user and returns the available shipping options for a flexible invoice
     *
     * @chatId - Chat identifier of the Invoice message
     * @messageId - Message identifier
     * @orderInfo - The order information, provided by the user
     * @allowSave - True, if the order information can be saved
     */
    public static class ValidateOrderInfo extends Function {

        public long chatId;
        public long messageId;
        public OrderInfo orderInfo;
        public boolean allowSave;

        public ValidateOrderInfo() {}

        public ValidateOrderInfo(long chatId, long messageId, OrderInfo orderInfo, boolean allowSave) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.orderInfo = orderInfo;
            this.allowSave = allowSave;

        }

        @Override
        public int getConstructor() { return 9480644; }

    }


    /**
     * Sends a filled-out payment form to the bot for final verification
     *
     * @chatId - Chat identifier of the Invoice message
     * @messageId - Message identifier
     * @orderInfoId - Identifier returned by ValidateOrderInfo, or an empty string
     * @shippingOptionId - Identifier of a chosen shipping option, if applicable
     * @credentials - The credentials chosen by user for payment
     */
    public static class SendPaymentForm extends Function {

        public long chatId;
        public long messageId;
        public String orderInfoId;
        public String shippingOptionId;
        public InputCredentials credentials;

        public SendPaymentForm() {}

        public SendPaymentForm(long chatId, long messageId, String orderInfoId, String shippingOptionId, InputCredentials credentials) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.orderInfoId = orderInfoId;
            this.shippingOptionId = shippingOptionId;
            this.credentials = credentials;

        }

        @Override
        public int getConstructor() { return 591581572; }

    }


    /**
     * Returns information about a successful payment
     *
     * @chatId - Chat identifier of the PaymentSuccessful message
     * @messageId - Message identifier
     */
    public static class GetPaymentReceipt extends Function {

        public long chatId;
        public long messageId;

        public GetPaymentReceipt() {}

        public GetPaymentReceipt(long chatId, long messageId) {

            this.chatId = chatId;
            this.messageId = messageId;

        }

        @Override
        public int getConstructor() { return 1013758294; }

    }


    /**
     * Returns saved order info, if any
     */
    public static class GetSavedOrderInfo extends Function {

        @Override
        public int getConstructor() { return -1152016675; }

    }


    /**
     * Deletes saved order info
     */
    public static class DeleteSavedOrderInfo extends Function {

        @Override
        public int getConstructor() { return 1629058164; }

    }


    /**
     * Deletes saved credentials for all payment provider bots
     */
    public static class DeleteSavedCredentials extends Function {

        @Override
        public int getConstructor() { return 826300114; }

    }


    /**
     * Returns a user that can be contacted to get support
     */
    public static class GetSupportUser extends Function {

        @Override
        public int getConstructor() { return -1733497700; }

    }


    /**
     * Returns backgrounds installed by the user
     *
     * @forDarkTheme - True, if the backgrounds need to be ordered for dark theme
     */
    public static class GetBackgrounds extends Function {

        public boolean forDarkTheme;

        public GetBackgrounds() {}

        public GetBackgrounds(boolean forDarkTheme) {

            this.forDarkTheme = forDarkTheme;

        }

        @Override
        public int getConstructor() { return 249072633; }

    }


    /**
     * Constructs a persistent HTTP URL for a background
     *
     * @name - Background name
     * @type - Background type
     */
    public static class GetBackgroundUrl extends Function {

        public String name;
        public BackgroundType type;

        public GetBackgroundUrl() {}

        public GetBackgroundUrl(String name, BackgroundType type) {

            this.name = name;
            this.type = type;

        }

        @Override
        public int getConstructor() { return 733769682; }

    }


    /**
     * Searches for a background by its name
     *
     * @name - The name of the background
     */
    public static class SearchBackground extends Function {

        public String name;

        public SearchBackground() {}

        public SearchBackground(String name) {

            this.name = name;

        }

        @Override
        public int getConstructor() { return -2130996959; }

    }


    /**
     * Changes the background selected by the user
     * Adds background to the list of installed backgrounds
     *
     * @background - The input background to use, null for filled backgrounds
     * @type - Background type
     *         Null for default background
     *         The method will return error 404 if type is null
     * @forDarkTheme - True, if the background is chosen for dark theme
     */
    public static class SetBackground extends Function {

        public InputBackground background;
        public BackgroundType type;
        public boolean forDarkTheme;

        public SetBackground() {}

        public SetBackground(InputBackground background, BackgroundType type, boolean forDarkTheme) {

            this.background = background;
            this.type = type;
            this.forDarkTheme = forDarkTheme;

        }

        @Override
        public int getConstructor() { return -1035439225; }

    }


    /**
     * Removes background from the list of installed backgrounds
     *
     * @backgroundId - The background identifier
     */
    public static class RemoveBackground extends Function {

        public long backgroundId;

        public RemoveBackground() {}

        public RemoveBackground(long backgroundId) {

            this.backgroundId = backgroundId;

        }

        @Override
        public int getConstructor() { return -1484545642; }

    }


    /**
     * Resets list of installed backgrounds to its default value
     */
    public static class ResetBackgrounds extends Function {

        @Override
        public int getConstructor() { return 204852088; }

    }


    /**
     * Returns information about the current localization target
     * This is an offline request if only_local is true
     * Can be called before authorization
     *
     * @onlyLocal - If true, returns only locally available information without sending network requests
     */
    public static class GetLocalizationTargetInfo extends Function {

        public boolean onlyLocal;

        public GetLocalizationTargetInfo() {}

        public GetLocalizationTargetInfo(boolean onlyLocal) {

            this.onlyLocal = onlyLocal;

        }

        @Override
        public int getConstructor() { return 1849499526; }

    }


    /**
     * Returns information about a language pack
     * Returned language pack identifier may be different from a provided one
     * Can be called before authorization
     *
     * @languagePackId - Language pack identifier
     */
    public static class GetLanguagePackInfo extends Function {

        public String languagePackId;

        public GetLanguagePackInfo() {}

        public GetLanguagePackInfo(String languagePackId) {

            this.languagePackId = languagePackId;

        }

        @Override
        public int getConstructor() { return 2077809320; }

    }


    /**
     * Returns strings from a language pack in the current localization target by their keys
     * Can be called before authorization
     *
     * @languagePackId - Language pack identifier of the strings to be returned
     * @keys - Language pack keys of the strings to be returned
     *         Leave empty to request all available strings
     */
    public static class GetLanguagePackStrings extends Function {

        public String languagePackId;
        public String[] keys;

        public GetLanguagePackStrings() {}

        public GetLanguagePackStrings(String languagePackId, String[] keys) {

            this.languagePackId = languagePackId;
            this.keys = keys;

        }

        @Override
        public int getConstructor() { return 1246259088; }

    }


    /**
     * Fetches the latest versions of all strings from a language pack in the current localization target from the server
     * This method doesn't need to be called explicitly for the current used/base language packs
     * Can be called before authorization
     *
     * @languagePackId - Language pack identifier
     */
    public static class SynchronizeLanguagePack extends Function {

        public String languagePackId;

        public SynchronizeLanguagePack() {}

        public SynchronizeLanguagePack(String languagePackId) {

            this.languagePackId = languagePackId;

        }

        @Override
        public int getConstructor() { return -2065307858; }

    }


    /**
     * Adds a custom server language pack to the list of installed language packs in current localization target
     * Can be called before authorization
     *
     * @languagePackId - Identifier of a language pack to be added
     *                   May be different from a name that is used in an "https://t.me/setlanguage/" link
     */
    public static class AddCustomServerLanguagePack extends Function {

        public String languagePackId;

        public AddCustomServerLanguagePack() {}

        public AddCustomServerLanguagePack(String languagePackId) {

            this.languagePackId = languagePackId;

        }

        @Override
        public int getConstructor() { return 4492771; }

    }


    /**
     * Adds or changes a custom local language pack to the current localization target
     *
     * @info - Information about the language pack
     *         Language pack ID must start with 'X', consist only of English letters, digits and hyphens, and must not exceed 64 characters
     *         Can be called before authorization
     * @strings - Strings of the new language pack
     */
    public static class SetCustomLanguagePack extends Function {

        public LanguagePackInfo info;
        public LanguagePackString[] strings;

        public SetCustomLanguagePack() {}

        public SetCustomLanguagePack(LanguagePackInfo info, LanguagePackString[] strings) {

            this.info = info;
            this.strings = strings;

        }

        @Override
        public int getConstructor() { return -296742819; }

    }


    /**
     * Edits information about a custom local language pack in the current localization target
     * Can be called before authorization
     *
     * @info - New information about the custom local language pack
     */
    public static class EditCustomLanguagePackInfo extends Function {

        public LanguagePackInfo info;

        public EditCustomLanguagePackInfo() {}

        public EditCustomLanguagePackInfo(LanguagePackInfo info) {

            this.info = info;

        }

        @Override
        public int getConstructor() { return 1320751257; }

    }


    /**
     * Adds, edits or deletes a string in a custom local language pack
     * Can be called before authorization
     *
     * @languagePackId - Identifier of a previously added custom local language pack in the current localization target
     * @newString - New language pack string
     */
    public static class SetCustomLanguagePackString extends Function {

        public String languagePackId;
        public LanguagePackString newString;

        public SetCustomLanguagePackString() {}

        public SetCustomLanguagePackString(String languagePackId, LanguagePackString newString) {

            this.languagePackId = languagePackId;
            this.newString = newString;

        }

        @Override
        public int getConstructor() { return 1316365592; }

    }


    /**
     * Deletes all information about a language pack in the current localization target
     * The language pack which is currently in use (including base language pack) or is being synchronized can't be deleted
     * Can be called before authorization
     *
     * @languagePackId - Identifier of the language pack to delete
     */
    public static class DeleteLanguagePack extends Function {

        public String languagePackId;

        public DeleteLanguagePack() {}

        public DeleteLanguagePack(String languagePackId) {

            this.languagePackId = languagePackId;

        }

        @Override
        public int getConstructor() { return -2108761026; }

    }


    /**
     * Registers the currently used device for receiving push notifications
     * Returns a globally unique identifier of the push notification subscription
     *
     * @deviceToken - Device token
     * @otherUserIds - List of user identifiers of other users currently using the application
     */
    public static class RegisterDevice extends Function {

        public DeviceToken deviceToken;
        public int[] otherUserIds;

        public RegisterDevice() {}

        public RegisterDevice(DeviceToken deviceToken, int[] otherUserIds) {

            this.deviceToken = deviceToken;
            this.otherUserIds = otherUserIds;

        }

        @Override
        public int getConstructor() { return 1734127493; }

    }


    /**
     * Handles a push notification
     * Returns error with code 406 if the push notification is not supported and connection to the server is required to fetch new data
     * Can be called before authorization
     *
     * @payload - JSON-encoded push notification payload with all fields sent by the server, and "google.sent_time" and "google.notification.sound" fields added
     */
    public static class ProcessPushNotification extends Function {

        public String payload;

        public ProcessPushNotification() {}

        public ProcessPushNotification(String payload) {

            this.payload = payload;

        }

        @Override
        public int getConstructor() { return 786679952; }

    }


    /**
     * Returns a globally unique push notification subscription identifier for identification of an account, which has received a push notification
     * Can be called synchronously
     *
     * @payload - JSON-encoded push notification payload
     */
    public static class GetPushReceiverId extends Function {

        public String payload;

        public GetPushReceiverId() {}

        public GetPushReceiverId(String payload) {

            this.payload = payload;

        }

        @Override
        public int getConstructor() { return -286505294; }

    }


    /**
     * Returns t.me URLs recently visited by a newly registered user
     *
     * @referrer - Google Play referrer to identify the user
     */
    public static class GetRecentlyVisitedTMeUrls extends Function {

        public String referrer;

        public GetRecentlyVisitedTMeUrls() {}

        public GetRecentlyVisitedTMeUrls(String referrer) {

            this.referrer = referrer;

        }

        @Override
        public int getConstructor() { return 806754961; }

    }


    /**
     * Changes user privacy settings
     *
     * @setting - The privacy setting
     * @rules - The new privacy rules
     */
    public static class SetUserPrivacySettingRules extends Function {

        public UserPrivacySetting setting;
        public UserPrivacySettingRules rules;

        public SetUserPrivacySettingRules() {}

        public SetUserPrivacySettingRules(UserPrivacySetting setting, UserPrivacySettingRules rules) {

            this.setting = setting;
            this.rules = rules;

        }

        @Override
        public int getConstructor() { return -473812741; }

    }


    /**
     * Returns the current privacy settings
     *
     * @setting - The privacy setting
     */
    public static class GetUserPrivacySettingRules extends Function {

        public UserPrivacySetting setting;

        public GetUserPrivacySettingRules() {}

        public GetUserPrivacySettingRules(UserPrivacySetting setting) {

            this.setting = setting;

        }

        @Override
        public int getConstructor() { return -2077223311; }

    }


    /**
     * Returns the value of an option by its name
     * (Check the list of available options on https://core.telegram.org/tdlib/options.) Can be called before authorization
     *
     * @name - The name of the option
     */
    public static class GetOption extends Function {

        public String name;

        public GetOption() {}

        public GetOption(String name) {

            this.name = name;

        }

        @Override
        public int getConstructor() { return -1572495746; }

    }


    /**
     * Sets the value of an option
     * (Check the list of available options on https://core.telegram.org/tdlib/options.) Only writable options can be set
     * Can be called before authorization
     *
     * @name - The name of the option
     * @value - The new value of the option
     */
    public static class SetOption extends Function {

        public String name;
        public OptionValue value;

        public SetOption() {}

        public SetOption(String name, OptionValue value) {

            this.name = name;
            this.value = value;

        }

        @Override
        public int getConstructor() { return 2114670322; }

    }


    /**
     * Changes the period of inactivity after which the account of the current user will automatically be deleted
     *
     * @ttl - New account TTL
     */
    public static class SetAccountTtl extends Function {

        public AccountTtl ttl;

        public SetAccountTtl() {}

        public SetAccountTtl(AccountTtl ttl) {

            this.ttl = ttl;

        }

        @Override
        public int getConstructor() { return 701389032; }

    }


    /**
     * Returns the period of inactivity after which the account of the current user will automatically be deleted
     */
    public static class GetAccountTtl extends Function {

        @Override
        public int getConstructor() { return -443905161; }

    }


    /**
     * Deletes the account of the current user, deleting all information associated with the user from the server
     * The phone number of the account can be used to create a new account
     * Can be called before authorization when the current authorization state is authorizationStateWaitPassword
     *
     * @reason - The reason why the account was deleted
     */
    public static class DeleteAccount extends Function {

        @Nullable public String reason;

        public DeleteAccount() {}

        public DeleteAccount(@Nullable String reason) {

            this.reason = reason;

        }

        @Override
        public int getConstructor() { return -1203056508; }

    }


    /**
     * Removes a chat action bar without any other action
     *
     * @chatId - Chat identifier
     */
    public static class RemoveChatActionBar extends Function {

        public long chatId;

        public RemoveChatActionBar() {}

        public RemoveChatActionBar(long chatId) {

            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return -1650968070; }

    }


    /**
     * Reports a chat to the Telegram moderators
     * A chat can be reported only from the chat action bar, or if this is a private chats with a bot, a private chat with a user sharing their location, a supergroup, or a channel, since other chats can't be checked by moderators
     *
     * @chatId - Chat identifier
     * @reason - The reason for reporting the chat
     * @messageIds - Identifiers of reported messages, if any
     */
    public static class ReportChat extends Function {

        public long chatId;
        public ChatReportReason reason;
        public long[] messageIds;

        public ReportChat() {}

        public ReportChat(long chatId, ChatReportReason reason, long[] messageIds) {

            this.chatId = chatId;
            this.reason = reason;
            this.messageIds = messageIds;

        }

        @Override
        public int getConstructor() { return -312579772; }

    }


    /**
     * Returns an HTTP URL with the chat statistics
     * Currently this method of getting the statistics are disabled and can be deleted in the future
     *
     * @chatId - Chat identifier
     * @parameters - Parameters from "tg://statsrefresh?params=******" link
     * @isDark - Pass true if a URL with the dark theme must be returned
     */
    public static class GetChatStatisticsUrl extends Function {

        public long chatId;
        public String parameters;
        public boolean isDark;

        public GetChatStatisticsUrl() {}

        public GetChatStatisticsUrl(long chatId, String parameters, boolean isDark) {

            this.chatId = chatId;
            this.parameters = parameters;
            this.isDark = isDark;

        }

        @Override
        public int getConstructor() { return 1114621183; }

    }


    /**
     * Returns detailed statistics about a chat
     * Currently this method can be used only for supergroups and channels
     * Can be used only if SupergroupFullInfo.can_get_statistics == true
     *
     * @chatId - Chat identifier
     * @isDark - Pass true if a dark theme is used by the application
     */
    public static class GetChatStatistics extends Function {

        public long chatId;
        public boolean isDark;

        public GetChatStatistics() {}

        public GetChatStatistics(long chatId, boolean isDark) {

            this.chatId = chatId;
            this.isDark = isDark;

        }

        @Override
        public int getConstructor() { return 327057816; }

    }


    /**
     * Returns detailed statistics about a message
     * Can be used only if Message.can_get_statistics == true
     * The method is under development and may or may not work
     *
     * @chatId - Chat identifier
     * @messageId - Message identifier
     * @isDark - Pass true if a dark theme is used by the application
     */
    public static class GetMessageStatistics extends Function {

        public long chatId;
        public long messageId;
        public boolean isDark;

        public GetMessageStatistics() {}

        public GetMessageStatistics(long chatId, long messageId, boolean isDark) {

            this.chatId = chatId;
            this.messageId = messageId;
            this.isDark = isDark;

        }

        @Override
        public int getConstructor() { return 1270194648; }

    }


    /**
     * Loads asynchronous or zoomed in chat or message statistics graph
     *
     * @chatId - Chat identifier
     * @token - The token for graph loading
     * @x - X-value for zoomed in graph or 0 otherwise
     */
    public static class GetStatisticsGraph extends Function {

        public long chatId;
        public String token;
        public long x;

        public GetStatisticsGraph() {}

        public GetStatisticsGraph(long chatId, String token, long x) {

            this.chatId = chatId;
            this.token = token;
            this.x = x;

        }

        @Override
        public int getConstructor() { return 687537922; }

    }


    /**
     * Returns storage usage statistics
     * Can be called before authorization
     *
     * @chatLimit - The maximum number of chats with the largest storage usage for which separate statistics should be returned
     *              All other chats will be grouped in entries with chat_id == 0
     *              If the chat info database is not used, the chat_limit is ignored and is always set to 0
     */
    public static class GetStorageStatistics extends Function {

        public int chatLimit;

        public GetStorageStatistics() {}

        public GetStorageStatistics(int chatLimit) {

            this.chatLimit = chatLimit;

        }

        @Override
        public int getConstructor() { return -853193929; }

    }


    /**
     * Quickly returns approximate storage usage statistics
     * Can be called before authorization
     */
    public static class GetStorageStatisticsFast extends Function {

        @Override
        public int getConstructor() { return 61368066; }

    }


    /**
     * Returns database statistics
     */
    public static class GetDatabaseStatistics extends Function {

        @Override
        public int getConstructor() { return -1942760263; }

    }


    /**
     * Optimizes storage usage, i.e
     * Deletes some files and returns new storage usage statistics
     * Secret thumbnails can't be deleted
     *
     * @size - Limit on the total size of files after deletion
     *         Pass -1 to use the default limit
     * @ttl - Limit on the time that has passed since the last time a file was accessed (or creation time for some filesystems)
     *        Pass -1 to use the default limit
     * @count - Limit on the total count of files after deletion
     *          Pass -1 to use the default limit
     * @immunityDelay - The amount of time after the creation of a file during which it can't be deleted, in seconds
     *                  Pass -1 to use the default value
     * @fileTypes - If not empty, only files with the given type(s) are considered
     *              By default, all types except thumbnails, profile photos, stickers and wallpapers are deleted
     * @chatIds - If not empty, only files from the given chats are considered
     *            Use 0 as chat identifier to delete files not belonging to any chat (e.g., profile photos)
     * @excludeChatIds - If not empty, files from the given chats are excluded
     *                   Use 0 as chat identifier to exclude all files not belonging to any chat (e.g., profile photos)
     * @returnDeletedFileStatistics - Pass true if deleted file statistics need to be returned instead of the whole storage usage statistics
     *                                Affects only returned statistics
     * @chatLimit - Same as in getStorageStatistics
     *              Affects only returned statistics
     */
    public static class OptimizeStorage extends Function {

        public long size;
        public int ttl;
        public int count;
        public int immunityDelay;
        public FileType[] fileTypes;
        public long[] chatIds;
        public long[] excludeChatIds;
        public boolean returnDeletedFileStatistics;
        public int chatLimit;

        public OptimizeStorage() {}

        public OptimizeStorage(long size, int ttl, int count, int immunityDelay, FileType[] fileTypes, long[] chatIds, long[] excludeChatIds, boolean returnDeletedFileStatistics, int chatLimit) {

            this.size = size;
            this.ttl = ttl;
            this.count = count;
            this.immunityDelay = immunityDelay;
            this.fileTypes = fileTypes;
            this.chatIds = chatIds;
            this.excludeChatIds = excludeChatIds;
            this.returnDeletedFileStatistics = returnDeletedFileStatistics;
            this.chatLimit = chatLimit;

        }

        @Override
        public int getConstructor() { return 853186759; }

    }


    /**
     * Sets the current network type
     * Can be called before authorization
     * Calling this method forces all network connections to reopen, mitigating the delay in switching between different networks, so it should be called whenever the network is changed, even if the network type remains the same
     * Network type is used to check whether the library can use the network at all and also for collecting detailed network data usage statistics
     *
     * @type - The new network type
     *         By default, networkTypeOther
     */
    public static class SetNetworkType extends Function {

        public NetworkType type;

        public SetNetworkType() {}

        public SetNetworkType(NetworkType type) {

            this.type = type;

        }

        @Override
        public int getConstructor() { return -701635234; }

    }


    /**
     * Returns network data usage statistics
     * Can be called before authorization
     *
     * @onlyCurrent - If true, returns only data for the current library launch
     */
    public static class GetNetworkStatistics extends Function {

        public boolean onlyCurrent;

        public GetNetworkStatistics() {}

        public GetNetworkStatistics(boolean onlyCurrent) {

            this.onlyCurrent = onlyCurrent;

        }

        @Override
        public int getConstructor() { return -986228706; }

    }


    /**
     * Adds the specified data to data usage statistics
     * Can be called before authorization
     *
     * @entry - The network statistics entry with the data to be added to statistics
     */
    public static class AddNetworkStatistics extends Function {

        public NetworkStatisticsEntry entry;

        public AddNetworkStatistics() {}

        public AddNetworkStatistics(NetworkStatisticsEntry entry) {

            this.entry = entry;

        }

        @Override
        public int getConstructor() { return 1264825305; }

    }


    /**
     * Resets all network data usage statistics to zero
     * Can be called before authorization
     */
    public static class ResetNetworkStatistics extends Function {

        @Override
        public int getConstructor() { return 1646452102; }

    }


    /**
     * Returns auto-download settings presets for the current user
     */
    public static class GetAutoDownloadSettingsPresets extends Function {

        @Override
        public int getConstructor() { return -1721088201; }

    }


    /**
     * Sets auto-download settings
     *
     * @settings - New user auto-download settings
     * @type - Type of the network for which the new settings are applied
     */
    public static class SetAutoDownloadSettings extends Function {

        public AutoDownloadSettings settings;
        public NetworkType type;

        public SetAutoDownloadSettings() {}

        public SetAutoDownloadSettings(AutoDownloadSettings settings, NetworkType type) {

            this.settings = settings;
            this.type = type;

        }

        @Override
        public int getConstructor() { return -353671948; }

    }


    /**
     * Returns information about a bank card
     *
     * @bankCardNumber - The bank card number
     */
    public static class GetBankCardInfo extends Function {

        public String bankCardNumber;

        public GetBankCardInfo() {}

        public GetBankCardInfo(String bankCardNumber) {

            this.bankCardNumber = bankCardNumber;

        }

        @Override
        public int getConstructor() { return -1310515792; }

    }


    /**
     * Returns one of the available Telegram Passport elements
     *
     * @type - Telegram Passport element type
     * @password - Password of the current user
     */
    public static class GetPassportElement extends Function {

        public PassportElementType type;
        public String password;

        public GetPassportElement() {}

        public GetPassportElement(PassportElementType type, String password) {

            this.type = type;
            this.password = password;

        }

        @Override
        public int getConstructor() { return -1882398342; }

    }


    /**
     * Returns all available Telegram Passport elements
     *
     * @password - Password of the current user
     */
    public static class GetAllPassportElements extends Function {

        public String password;

        public GetAllPassportElements() {}

        public GetAllPassportElements(String password) {

            this.password = password;

        }

        @Override
        public int getConstructor() { return -2038945045; }

    }


    /**
     * Adds an element to the user's Telegram Passport
     * May return an error with a message "PHONE_VERIFICATION_NEEDED" or "EMAIL_VERIFICATION_NEEDED" if the chosen phone number or the chosen email address must be verified first
     *
     * @element - Input Telegram Passport element
     * @password - Password of the current user
     */
    public static class SetPassportElement extends Function {

        public InputPassportElement element;
        public String password;

        public SetPassportElement() {}

        public SetPassportElement(InputPassportElement element, String password) {

            this.element = element;
            this.password = password;

        }

        @Override
        public int getConstructor() { return 2068173212; }

    }


    /**
     * Deletes a Telegram Passport element
     *
     * @type - Element type
     */
    public static class DeletePassportElement extends Function {

        public PassportElementType type;

        public DeletePassportElement() {}

        public DeletePassportElement(PassportElementType type) {

            this.type = type;

        }

        @Override
        public int getConstructor() { return -1719555468; }

    }


    /**
     * Informs the user that some of the elements in their Telegram Passport contain errors
     * For bots only
     * The user will not be able to resend the elements, until the errors are fixed
     *
     * @userId - User identifier
     * @errors - The errors
     */
    public static class SetPassportElementErrors extends Function {

        public int userId;
        public InputPassportElementError[] errors;

        public SetPassportElementErrors() {}

        public SetPassportElementErrors(int userId, InputPassportElementError[] errors) {

            this.userId = userId;
            this.errors = errors;

        }

        @Override
        public int getConstructor() { return 1455869875; }

    }


    /**
     * Returns an IETF language tag of the language preferred in the country, which should be used to fill native fields in Telegram Passport personal details
     * Returns a 404 error if unknown
     *
     * @countryCode - A two-letter ISO 3166-1 alpha-2 country code
     */
    public static class GetPreferredCountryLanguage extends Function {

        public String countryCode;

        public GetPreferredCountryLanguage() {}

        public GetPreferredCountryLanguage(String countryCode) {

            this.countryCode = countryCode;

        }

        @Override
        public int getConstructor() { return -933049386; }

    }


    /**
     * Sends a code to verify a phone number to be added to a user's Telegram Passport
     *
     * @phoneNumber - The phone number of the user, in international format
     * @settings - Settings for the authentication of the user's phone number
     */
    public static class SendPhoneNumberVerificationCode extends Function {

        public String phoneNumber;
        public PhoneNumberAuthenticationSettings settings;

        public SendPhoneNumberVerificationCode() {}

        public SendPhoneNumberVerificationCode(String phoneNumber, PhoneNumberAuthenticationSettings settings) {

            this.phoneNumber = phoneNumber;
            this.settings = settings;

        }

        @Override
        public int getConstructor() { return 2081689035; }

    }


    /**
     * Re-sends the code to verify a phone number to be added to a user's Telegram Passport
     */
    public static class ResendPhoneNumberVerificationCode extends Function {

        @Override
        public int getConstructor() { return 1367629820; }

    }


    /**
     * Checks the phone number verification code for Telegram Passport
     *
     * @code - Verification code
     */
    public static class CheckPhoneNumberVerificationCode extends Function {

        public String code;

        public CheckPhoneNumberVerificationCode() {}

        public CheckPhoneNumberVerificationCode(String code) {

            this.code = code;

        }

        @Override
        public int getConstructor() { return 1497462718; }

    }


    /**
     * Sends a code to verify an email address to be added to a user's Telegram Passport
     *
     * @emailAddress - Email address
     */
    public static class SendEmailAddressVerificationCode extends Function {

        public String emailAddress;

        public SendEmailAddressVerificationCode() {}

        public SendEmailAddressVerificationCode(String emailAddress) {

            this.emailAddress = emailAddress;

        }

        @Override
        public int getConstructor() { return -221621379; }

    }


    /**
     * Re-sends the code to verify an email address to be added to a user's Telegram Passport
     */
    public static class ResendEmailAddressVerificationCode extends Function {

        @Override
        public int getConstructor() { return -1872416732; }

    }


    /**
     * Checks the email address verification code for Telegram Passport
     *
     * @code - Verification code
     */
    public static class CheckEmailAddressVerificationCode extends Function {

        public String code;

        public CheckEmailAddressVerificationCode() {}

        public CheckEmailAddressVerificationCode(String code) {

            this.code = code;

        }

        @Override
        public int getConstructor() { return -426386685; }

    }


    /**
     * Returns a Telegram Passport authorization form for sharing data with a service
     *
     * @botUserId - User identifier of the service's bot
     * @scope - Telegram Passport element types requested by the service
     * @publicKey - Service's public_key
     * @nonce - Authorization form nonce provided by the service
     */
    public static class GetPassportAuthorizationForm extends Function {

        public int botUserId;
        public String scope;
        public String publicKey;
        public String nonce;

        public GetPassportAuthorizationForm() {}

        public GetPassportAuthorizationForm(int botUserId, String scope, String publicKey, String nonce) {

            this.botUserId = botUserId;
            this.scope = scope;
            this.publicKey = publicKey;
            this.nonce = nonce;

        }

        @Override
        public int getConstructor() { return -1468394095; }

    }


    /**
     * Returns already available Telegram Passport elements suitable for completing a Telegram Passport authorization form
     * Result can be received only once for each authorization form
     *
     * @autorizationFormId - Authorization form identifier
     * @password - Password of the current user
     */
    public static class GetPassportAuthorizationFormAvailableElements extends Function {

        public int autorizationFormId;
        public String password;

        public GetPassportAuthorizationFormAvailableElements() {}

        public GetPassportAuthorizationFormAvailableElements(int autorizationFormId, String password) {

            this.autorizationFormId = autorizationFormId;
            this.password = password;

        }

        @Override
        public int getConstructor() { return 1738134754; }

    }


    /**
     * Sends a Telegram Passport authorization form, effectively sharing data with the service
     * This method must be called after getPassportAuthorizationFormAvailableElements if some previously available elements need to be used
     *
     * @autorizationFormId - Authorization form identifier
     * @types - Types of Telegram Passport elements chosen by user to complete the authorization form
     */
    public static class SendPassportAuthorizationForm extends Function {

        public int autorizationFormId;
        public PassportElementType[] types;

        public SendPassportAuthorizationForm() {}

        public SendPassportAuthorizationForm(int autorizationFormId, PassportElementType[] types) {

            this.autorizationFormId = autorizationFormId;
            this.types = types;

        }

        @Override
        public int getConstructor() { return -602402218; }

    }


    /**
     * Sends phone number confirmation code
     * Should be called when user presses "https://t.me/confirmphone?phone=*******&hash=**********" or "tg://confirmphone?phone=*******&hash=**********" link
     *
     * @hash - Value of the "hash" parameter from the link
     * @phoneNumber - Value of the "phone" parameter from the link
     * @settings - Settings for the authentication of the user's phone number
     */
    public static class SendPhoneNumberConfirmationCode extends Function {

        public String hash;
        public String phoneNumber;
        public PhoneNumberAuthenticationSettings settings;

        public SendPhoneNumberConfirmationCode() {}

        public SendPhoneNumberConfirmationCode(String hash, String phoneNumber, PhoneNumberAuthenticationSettings settings) {

            this.hash = hash;
            this.phoneNumber = phoneNumber;
            this.settings = settings;

        }

        @Override
        public int getConstructor() { return -1901171495; }

    }


    /**
     * Resends phone number confirmation code
     */
    public static class ResendPhoneNumberConfirmationCode extends Function {

        @Override
        public int getConstructor() { return 2069068522; }

    }


    /**
     * Checks phone number confirmation code
     *
     * @code - The phone number confirmation code
     */
    public static class CheckPhoneNumberConfirmationCode extends Function {

        public String code;

        public CheckPhoneNumberConfirmationCode() {}

        public CheckPhoneNumberConfirmationCode(String code) {

            this.code = code;

        }

        @Override
        public int getConstructor() { return -1348060966; }

    }


    /**
     * Informs the server about the number of pending bot updates if they haven't been processed for a long time
     * For bots only
     *
     * @pendingUpdateCount - The number of pending updates
     * @errorMessage - The last error message
     */
    public static class SetBotUpdatesStatus extends Function {

        public int pendingUpdateCount;
        public String errorMessage;

        public SetBotUpdatesStatus() {}

        public SetBotUpdatesStatus(int pendingUpdateCount, String errorMessage) {

            this.pendingUpdateCount = pendingUpdateCount;
            this.errorMessage = errorMessage;

        }

        @Override
        public int getConstructor() { return -1154926191; }

    }


    /**
     * Uploads a PNG image with a sticker
     * For bots only
     * Returns the uploaded file
     *
     * @userId - Sticker file owner
     * @pngSticker - PNG image with the sticker
     *               Must be up to 512 KB in size and fit in 512x512 square
     */
    public static class UploadStickerFile extends Function {

        public int userId;
        public InputFile pngSticker;

        public UploadStickerFile() {}

        public UploadStickerFile(int userId, InputFile pngSticker) {

            this.userId = userId;
            this.pngSticker = pngSticker;

        }

        @Override
        public int getConstructor() { return 1134087551; }

    }


    /**
     * Creates a new sticker set
     * For bots only
     * Returns the newly created sticker set
     *
     * @userId - Sticker set owner
     * @title - Sticker set title
     * @name - Sticker set name
     *         Can contain only English letters, digits and underscores
     *         Must end with *"_by_<bot username>"* (*<bot_username>* is case insensitive)
     * @isMasks - True, if stickers are masks
     *            Animated stickers can't be masks
     * @stickers - List of stickers to be added to the set
     *             All stickers must be of the same type
     */
    public static class CreateNewStickerSet extends Function {

        public int userId;
        public String title;
        public String name;
        public boolean isMasks;
        public InputSticker[] stickers;

        public CreateNewStickerSet() {}

        public CreateNewStickerSet(int userId, String title, String name, boolean isMasks, InputSticker[] stickers) {

            this.userId = userId;
            this.title = title;
            this.name = name;
            this.isMasks = isMasks;
            this.stickers = stickers;

        }

        @Override
        public int getConstructor() { return -1139329506; }

    }


    /**
     * Adds a new sticker to a set
     * For bots only
     * Returns the sticker set
     *
     * @userId - Sticker set owner
     * @name - Sticker set name
     * @sticker - Sticker to add to the set
     */
    public static class AddStickerToSet extends Function {

        public int userId;
        public String name;
        public InputSticker sticker;

        public AddStickerToSet() {}

        public AddStickerToSet(int userId, String name, InputSticker sticker) {

            this.userId = userId;
            this.name = name;
            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return -454661588; }

    }


    /**
     * Sets a sticker set thumbnail
     * For bots only
     * Returns the sticker set
     *
     * @userId - Sticker set owner
     * @name - Sticker set name
     * @thumbnail - Thumbnail to set in PNG or TGS format
     *              Animated thumbnail must be set for animated sticker sets and only for them
     *              Pass a zero InputFileId to delete the thumbnail
     */
    public static class SetStickerSetThumbnail extends Function {

        public int userId;
        public String name;
        public InputFile thumbnail;

        public SetStickerSetThumbnail() {}

        public SetStickerSetThumbnail(int userId, String name, InputFile thumbnail) {

            this.userId = userId;
            this.name = name;
            this.thumbnail = thumbnail;

        }

        @Override
        public int getConstructor() { return -1694737404; }

    }


    /**
     * Changes the position of a sticker in the set to which it belongs
     * For bots only
     * The sticker set must have been created by the bot
     *
     * @sticker - Sticker
     * @position - New position of the sticker in the set, zero-based
     */
    public static class SetStickerPositionInSet extends Function {

        public InputFile sticker;
        public int position;

        public SetStickerPositionInSet() {}

        public SetStickerPositionInSet(InputFile sticker, int position) {

            this.sticker = sticker;
            this.position = position;

        }

        @Override
        public int getConstructor() { return 2075281185; }

    }


    /**
     * Removes a sticker from the set to which it belongs
     * For bots only
     * The sticker set must have been created by the bot
     *
     * @sticker - Sticker
     */
    public static class RemoveStickerFromSet extends Function {

        public InputFile sticker;

        public RemoveStickerFromSet() {}

        public RemoveStickerFromSet(InputFile sticker) {

            this.sticker = sticker;

        }

        @Override
        public int getConstructor() { return 1642196644; }

    }


    /**
     * Returns information about a file with a map thumbnail in PNG format
     * Only map thumbnail files with size less than 1MB can be downloaded
     *
     * @location - Location of the map center
     * @zoom - Map zoom level
     * @width - Map width in pixels before applying scale
     * @height - Map height in pixels before applying scale
     * @scale - Map scale
     * @chatId - Identifier of a chat, in which the thumbnail will be shown
     *           Use 0 if unknown
     */
    public static class GetMapThumbnailFile extends Function {

        public Location location;
        public int zoom;
        public int width;
        public int height;
        public int scale;
        public long chatId;

        public GetMapThumbnailFile() {}

        public GetMapThumbnailFile(Location location, int zoom, int width, int height, int scale, long chatId) {

            this.location = location;
            this.zoom = zoom;
            this.width = width;
            this.height = height;
            this.scale = scale;
            this.chatId = chatId;

        }

        @Override
        public int getConstructor() { return -152660070; }

    }


    /**
     * Accepts Telegram terms of services
     *
     * @termsOfServiceId - Terms of service identifier
     */
    public static class AcceptTermsOfService extends Function {

        public String termsOfServiceId;

        public AcceptTermsOfService() {}

        public AcceptTermsOfService(String termsOfServiceId) {

            this.termsOfServiceId = termsOfServiceId;

        }

        @Override
        public int getConstructor() { return 2130576356; }

    }


    /**
     * Sends a custom request
     * For bots only
     *
     * @method - The method name
     * @parameters - JSON-serialized method parameters
     */
    public static class SendCustomRequest extends Function {

        public String method;
        public String parameters;

        public SendCustomRequest() {}

        public SendCustomRequest(String method, String parameters) {

            this.method = method;
            this.parameters = parameters;

        }

        @Override
        public int getConstructor() { return 285045153; }

    }


    /**
     * Answers a custom query
     * For bots only
     *
     * @customQueryId - Identifier of a custom query
     * @data - JSON-serialized answer to the query
     */
    public static class AnswerCustomQuery extends Function {

        public long customQueryId;
        public String data;

        public AnswerCustomQuery() {}

        public AnswerCustomQuery(long customQueryId, String data) {

            this.customQueryId = customQueryId;
            this.data = data;

        }

        @Override
        public int getConstructor() { return -1293603521; }

    }


    /**
     * Succeeds after a specified amount of time has passed
     * Can be called before initialization
     *
     * @seconds - Number of seconds before the function returns
     */
    public static class SetAlarm extends Function {

        public double seconds;

        public SetAlarm() {}

        public SetAlarm(double seconds) {

            this.seconds = seconds;

        }

        @Override
        public int getConstructor() { return -873497067; }

    }


    /**
     * Returns information about existing countries
     * Can be called before authorization
     */
    public static class GetCountries extends Function {

        @Override
        public int getConstructor() { return -51902050; }

    }


    /**
     * Uses current user IP address to find their country
     * Returns two-letter ISO 3166-1 alpha-2 country code
     * Can be called before authorization
     */
    public static class GetCountryCode extends Function {

        @Override
        public int getConstructor() { return 1540593906; }

    }


    /**
     * Returns information about a phone number by its prefix
     * Can be called before authorization
     *
     * @phoneNumberPrefix - The phone number prefix
     */
    public static class GetPhoneNumberInfo extends Function {

        public String phoneNumberPrefix;

        public GetPhoneNumberInfo() {}

        public GetPhoneNumberInfo(String phoneNumberPrefix) {

            this.phoneNumberPrefix = phoneNumberPrefix;

        }

        @Override
        public int getConstructor() { return -1608344583; }

    }


    /**
     * Returns the default text for invitation messages to be used as a placeholder when the current user invites friends to Telegram
     */
    public static class GetInviteText extends Function {

        @Override
        public int getConstructor() { return 794573512; }

    }


    /**
     * Returns information about a tg:// deep link
     * Use "tg://need_update_for_some_feature" or "tg:some_unsupported_feature" for testing
     * Returns a 404 error for unknown links
     * Can be called before authorization
     *
     * @link - The link
     */
    public static class GetDeepLinkInfo extends Function {

        public String link;

        public GetDeepLinkInfo() {}

        public GetDeepLinkInfo(String link) {

            this.link = link;

        }

        @Override
        public int getConstructor() { return 680673150; }

    }


    /**
     * Returns application config, provided by the server
     * Can be called before authorization
     */
    public static class GetApplicationConfig extends Function {

        @Override
        public int getConstructor() { return -1823144318; }

    }


    /**
     * Saves application log event on the server
     * Can be called before authorization
     *
     * @type - Event type
     * @chatId - Optional chat identifier, associated with the event
     * @data - The log event data
     */
    public static class SaveApplicationLogEvent extends Function {

        public String type;
        public long chatId;
        public JsonValue data;

        public SaveApplicationLogEvent() {}

        public SaveApplicationLogEvent(String type, long chatId, JsonValue data) {

            this.type = type;
            this.chatId = chatId;
            this.data = data;

        }

        @Override
        public int getConstructor() { return -811154930; }

    }


    /**
     * Adds a proxy server for network requests
     * Can be called before authorization
     *
     * @server - Proxy server IP address
     * @port - Proxy server port
     * @enable - True, if the proxy should be enabled
     * @type - Proxy type
     */
    public static class AddProxy extends Function {

        public String server;
        public int port;
        public boolean enable;
        public ProxyType type;

        public AddProxy() {}

        public AddProxy(String server, int port, boolean enable, ProxyType type) {

            this.server = server;
            this.port = port;
            this.enable = enable;
            this.type = type;

        }

        @Override
        public int getConstructor() { return 331529432; }

    }


    /**
     * Edits an existing proxy server for network requests
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     * @server - Proxy server IP address
     * @port - Proxy server port
     * @enable - True, if the proxy should be enabled
     * @type - Proxy type
     */
    public static class EditProxy extends Function {

        public int proxyId;
        public String server;
        public int port;
        public boolean enable;
        public ProxyType type;

        public EditProxy() {}

        public EditProxy(int proxyId, String server, int port, boolean enable, ProxyType type) {

            this.proxyId = proxyId;
            this.server = server;
            this.port = port;
            this.enable = enable;
            this.type = type;

        }

        @Override
        public int getConstructor() { return -1605883821; }

    }


    /**
     * Enables a proxy
     * Only one proxy can be enabled at a time
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     */
    public static class EnableProxy extends Function {

        public int proxyId;

        public EnableProxy() {}

        public EnableProxy(int proxyId) {

            this.proxyId = proxyId;

        }

        @Override
        public int getConstructor() { return 1494450838; }

    }


    /**
     * Disables the currently enabled proxy
     * Can be called before authorization
     */
    public static class DisableProxy extends Function {

        @Override
        public int getConstructor() { return -2100095102; }

    }


    /**
     * Removes a proxy server
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     */
    public static class RemoveProxy extends Function {

        public int proxyId;

        public RemoveProxy() {}

        public RemoveProxy(int proxyId) {

            this.proxyId = proxyId;

        }

        @Override
        public int getConstructor() { return 1369219847; }

    }


    /**
     * Returns list of proxies that are currently set up
     * Can be called before authorization
     */
    public static class GetProxies extends Function {

        @Override
        public int getConstructor() { return -95026381; }

    }


    /**
     * Returns an HTTPS link, which can be used to add a proxy
     * Available only for SOCKS5 and MTProto proxies
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     */
    public static class GetProxyLink extends Function {

        public int proxyId;

        public GetProxyLink() {}

        public GetProxyLink(int proxyId) {

            this.proxyId = proxyId;

        }

        @Override
        public int getConstructor() { return -1285597664; }

    }


    /**
     * Computes time needed to receive a response from a Telegram server through a proxy
     * Can be called before authorization
     *
     * @proxyId - Proxy identifier
     *            Use 0 to ping a Telegram server without a proxy
     */
    public static class PingProxy extends Function {

        public int proxyId;

        public PingProxy() {}

        public PingProxy(int proxyId) {

            this.proxyId = proxyId;

        }

        @Override
        public int getConstructor() { return -979681103; }

    }


    /**
     * Sets new log stream for internal logging of TDLib
     * Can be called synchronously
     *
     * @logStream - New log stream
     */
    public static class SetLogStream extends Function {

        public LogStream logStream;

        public SetLogStream() {}

        public SetLogStream(LogStream logStream) {

            this.logStream = logStream;

        }

        @Override
        public int getConstructor() { return -1364199535; }

    }


    /**
     * Returns information about currently used log stream for internal logging of TDLib
     * Can be called synchronously
     */
    public static class GetLogStream extends Function {

        @Override
        public int getConstructor() { return 1167608667; }

    }


    /**
     * Sets the verbosity level of the internal logging of TDLib
     * Can be called synchronously
     *
     * @newVerbosityLevel - New value of the verbosity level for logging
     *                      Value 0 corresponds to fatal errors, value 1 corresponds to errors, value 2 corresponds to warnings and debug warnings, value 3 corresponds to informational, value 4 corresponds to debug, value 5 corresponds to verbose debug, value greater than 5 and up to 1023 can be used to enable even more logging
     */
    public static class SetLogVerbosityLevel extends Function {

        public int newVerbosityLevel;

        public SetLogVerbosityLevel() {}

        public SetLogVerbosityLevel(int newVerbosityLevel) {

            this.newVerbosityLevel = newVerbosityLevel;

        }

        @Override
        public int getConstructor() { return -303429678; }

    }


    /**
     * Returns current verbosity level of the internal logging of TDLib
     * Can be called synchronously
     */
    public static class GetLogVerbosityLevel extends Function {

        @Override
        public int getConstructor() { return 594057956; }

    }


    /**
     * Returns list of available TDLib internal log tags, for example, ["actor", "binlog", "connections", "notifications", "proxy"]
     * Can be called synchronously
     */
    public static class GetLogTags extends Function {

        @Override
        public int getConstructor() { return -254449190; }

    }


    /**
     * Sets the verbosity level for a specified TDLib internal log tag
     * Can be called synchronously
     *
     * @tag - Logging tag to change verbosity level
     * @newVerbosityLevel - New verbosity level
     */
    public static class SetLogTagVerbosityLevel extends Function {

        public String tag;
        public int newVerbosityLevel;

        public SetLogTagVerbosityLevel() {}

        public SetLogTagVerbosityLevel(String tag, int newVerbosityLevel) {

            this.tag = tag;
            this.newVerbosityLevel = newVerbosityLevel;

        }

        @Override
        public int getConstructor() { return -2095589738; }

    }


    /**
     * Returns current verbosity level for a specified TDLib internal log tag
     * Can be called synchronously
     *
     * @tag - Logging tag to change verbosity level
     */
    public static class GetLogTagVerbosityLevel extends Function {

        public String tag;

        public GetLogTagVerbosityLevel() {}

        public GetLogTagVerbosityLevel(String tag) {

            this.tag = tag;

        }

        @Override
        public int getConstructor() { return 951004547; }

    }


    /**
     * Adds a message to TDLib internal log
     * Can be called synchronously
     *
     * @verbosityLevel - The minimum verbosity level needed for the message to be logged, 0-1023
     * @text - Text of a message to log
     */
    public static class AddLogMessage extends Function {

        public int verbosityLevel;
        public String text;

        public AddLogMessage() {}

        public AddLogMessage(int verbosityLevel, String text) {

            this.verbosityLevel = verbosityLevel;
            this.text = text;

        }

        @Override
        public int getConstructor() { return 1597427692; }

    }


    /**
     * Does nothing
     * For testing only
     * This is an offline method
     * Can be called before authorization
     */
    public static class TestCallEmpty extends Function {

        @Override
        public int getConstructor() { return -627291626; }

    }


    /**
     * Returns the received string
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - String to return
     */
    public static class TestCallString extends Function {

        public String x;

        public TestCallString() {}

        public TestCallString(String x) {

            this.x = x;

        }

        @Override
        public int getConstructor() { return -1732818385; }

    }


    /**
     * Returns the received bytes
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Bytes to return
     */
    public static class TestCallBytes extends Function {

        public byte[] x;

        public TestCallBytes() {}

        public TestCallBytes(byte[] x) {

            this.x = x;

        }

        @Override
        public int getConstructor() { return -736011607; }

    }


    /**
     * Returns the received vector of numbers
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Vector of numbers to return
     */
    public static class TestCallVectorInt extends Function {

        public int[] x;

        public TestCallVectorInt() {}

        public TestCallVectorInt(int[] x) {

            this.x = x;

        }

        @Override
        public int getConstructor() { return -2137277793; }

    }


    /**
     * Returns the received vector of objects containing a number
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Vector of objects to return
     */
    public static class TestCallVectorIntObject extends Function {

        public TestInt[] x;

        public TestCallVectorIntObject() {}

        public TestCallVectorIntObject(TestInt[] x) {

            this.x = x;

        }

        @Override
        public int getConstructor() { return 1825428218; }

    }


    /**
     * Returns the received vector of strings
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Vector of strings to return
     */
    public static class TestCallVectorString extends Function {

        public String[] x;

        public TestCallVectorString() {}

        public TestCallVectorString(String[] x) {

            this.x = x;

        }

        @Override
        public int getConstructor() { return -408600900; }

    }


    /**
     * Returns the received vector of objects containing a string
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Vector of objects to return
     */
    public static class TestCallVectorStringObject extends Function {

        public TestString[] x;

        public TestCallVectorStringObject() {}

        public TestCallVectorStringObject(TestString[] x) {

            this.x = x;

        }

        @Override
        public int getConstructor() { return 1527666429; }

    }


    /**
     * Returns the squared received number
     * For testing only
     * This is an offline method
     * Can be called before authorization
     *
     * @x - Number to square
     */
    public static class TestSquareInt extends Function {

        public int x;

        public TestSquareInt() {}

        public TestSquareInt(int x) {

            this.x = x;

        }

        @Override
        public int getConstructor() { return -60135024; }

    }


    /**
     * Sends a simple network request to the Telegram servers
     * For testing only
     * Can be called before authorization
     */
    public static class TestNetwork extends Function {

        @Override
        public int getConstructor() { return -1343998901; }

    }


    /**
     * Sends a simple network request to the Telegram servers via proxy
     * For testing only
     * Can be called before authorization
     *
     * @server - Proxy server IP address
     * @port - Proxy server port
     * @type - Proxy type
     * @dcId - Identifier of a datacenter, with which to test connection
     * @timeout - The maximum overall timeout for the request
     */
    public static class TestProxy extends Function {

        public String server;
        public int port;
        public ProxyType type;
        public int dcId;
        public double timeout;

        public TestProxy() {}

        public TestProxy(String server, int port, ProxyType type, int dcId, double timeout) {

            this.server = server;
            this.port = port;
            this.type = type;
            this.dcId = dcId;
            this.timeout = timeout;

        }

        @Override
        public int getConstructor() { return -1197366626; }

    }


    /**
     * Forces an updates.getDifference call to the Telegram servers
     * For testing only
     */
    public static class TestGetDifference extends Function {

        @Override
        public int getConstructor() { return 1747084069; }

    }


    /**
     * Does nothing and ensures that the Update object is used
     * For testing only
     * This is an offline method
     * Can be called before authorization
     */
    public static class TestUseUpdate extends Function {

        @Override
        public int getConstructor() { return 717094686; }

    }


    /**
     * Returns the specified error and ensures that the Error object is used
     * For testing only
     * Can be called synchronously
     *
     * @error - The error to be returned
     */
    public static class TestReturnError extends Function {

        public Error error;

        public TestReturnError() {}

        public TestReturnError(Error error) {

            this.error = error;

        }

        @Override
        public int getConstructor() { return 455179506; }

    }


}

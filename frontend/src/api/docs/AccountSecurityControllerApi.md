# AccountSecurityControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**changePassword**](#changepassword) | **POST** /api/account/security/password/change | |
|[**confirmEmailMfa**](#confirmemailmfa) | **POST** /api/account/security/mfa/email/confirm | |
|[**confirmTOTPSetup**](#confirmtotpsetup) | **POST** /api/account/security/mfa/totp | |
|[**deleteMfaConfig**](#deletemfaconfig) | **POST** /api/account/security/mfa/delete | |
|[**disableMfa**](#disablemfa) | **POST** /api/account/security/mfa/disable | |
|[**enableMfa**](#enablemfa) | **POST** /api/account/security/mfa/enable | |
|[**finishRegistration1**](#finishregistration1) | **POST** /api/account/security/mfa/passkey/registration/finish | |
|[**firstLogin**](#firstlogin) | **POST** /api/account/security/first-login | |
|[**generateMfaBackupCodes**](#generatemfabackupcodes) | **GET** /api/account/security/mfa/backup-codes | |
|[**getMfaConfig**](#getmfaconfig) | **GET** /api/account/security/mfa | |
|[**initEmailMfa**](#initemailmfa) | **POST** /api/account/security/mfa/email/init | |
|[**requestTOTP**](#requesttotp) | **GET** /api/account/security/mfa/totp | |
|[**startRegistration1**](#startregistration1) | **POST** /api/account/security/mfa/passkey/registration/start | |

# **changePassword**
> CustomApiResponseVoid changePassword(changePasswordRequest)


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration,
    ChangePasswordRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

let changePasswordRequest: ChangePasswordRequest; //

const { status, data } = await apiInstance.changePassword(
    changePasswordRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **changePasswordRequest** | **ChangePasswordRequest**|  | |


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **confirmEmailMfa**
> CustomApiResponseMfaConfigResponse confirmEmailMfa(mfaConfirmRequest)


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration,
    MfaConfirmRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

let mfaConfirmRequest: MfaConfirmRequest; //

const { status, data } = await apiInstance.confirmEmailMfa(
    mfaConfirmRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **mfaConfirmRequest** | **MfaConfirmRequest**|  | |


### Return type

**CustomApiResponseMfaConfigResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **confirmTOTPSetup**
> CustomApiResponseVoid confirmTOTPSetup(tOTPConfirmRequest)


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration,
    TOTPConfirmRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

let tOTPConfirmRequest: TOTPConfirmRequest; //

const { status, data } = await apiInstance.confirmTOTPSetup(
    tOTPConfirmRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **tOTPConfirmRequest** | **TOTPConfirmRequest**|  | |


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **deleteMfaConfig**
> CustomApiResponseVoid deleteMfaConfig(mfaDeleteRequest)


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration,
    MfaDeleteRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

let mfaDeleteRequest: MfaDeleteRequest; //

const { status, data } = await apiInstance.deleteMfaConfig(
    mfaDeleteRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **mfaDeleteRequest** | **MfaDeleteRequest**|  | |


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **disableMfa**
> CustomApiResponseVoid disableMfa(mfaDisableRequest)


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration,
    MfaDisableRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

let mfaDisableRequest: MfaDisableRequest; //

const { status, data } = await apiInstance.disableMfa(
    mfaDisableRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **mfaDisableRequest** | **MfaDisableRequest**|  | |


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **enableMfa**
> CustomApiResponseVoid enableMfa()


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

const { status, data } = await apiInstance.enableMfa();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **finishRegistration1**
> CustomApiResponseVoid finishRegistration1(finishPasskeyRegistrationRequest)


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration,
    FinishPasskeyRegistrationRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

let finishPasskeyRegistrationRequest: FinishPasskeyRegistrationRequest; //

const { status, data } = await apiInstance.finishRegistration1(
    finishPasskeyRegistrationRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **finishPasskeyRegistrationRequest** | **FinishPasskeyRegistrationRequest**|  | |


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **firstLogin**
> CustomApiResponseVoid firstLogin(firstLoginRequest)


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration,
    FirstLoginRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

let firstLoginRequest: FirstLoginRequest; //

const { status, data } = await apiInstance.firstLogin(
    firstLoginRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **firstLoginRequest** | **FirstLoginRequest**|  | |


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **generateMfaBackupCodes**
> CustomApiResponseListString generateMfaBackupCodes()


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

const { status, data } = await apiInstance.generateMfaBackupCodes();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListString**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getMfaConfig**
> CustomApiResponseListMfaConfigResponse getMfaConfig()


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

const { status, data } = await apiInstance.getMfaConfig();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListMfaConfigResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **initEmailMfa**
> CustomApiResponseMfaInitResponse initEmailMfa(mfaEmailInitRequest)


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration,
    MfaEmailInitRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

let mfaEmailInitRequest: MfaEmailInitRequest; //

const { status, data } = await apiInstance.initEmailMfa(
    mfaEmailInitRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **mfaEmailInitRequest** | **MfaEmailInitRequest**|  | |


### Return type

**CustomApiResponseMfaInitResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **requestTOTP**
> CustomApiResponseMfaSetupResponse requestTOTP()


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

const { status, data } = await apiInstance.requestTOTP();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseMfaSetupResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **startRegistration1**
> CustomApiResponsePublicKeyCredentialCreationOptions startRegistration1()


### Example

```typescript
import {
    AccountSecurityControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSecurityControllerApi(configuration);

const { status, data } = await apiInstance.startRegistration1();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponsePublicKeyCredentialCreationOptions**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)


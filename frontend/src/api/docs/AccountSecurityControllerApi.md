# AccountSecurityControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**changePassword**](AccountSecurityControllerApi.md#changepasswordoperation) | **POST** /api/account/security/password/change |  |
| [**confirmEmailMfa**](AccountSecurityControllerApi.md#confirmemailmfa) | **POST** /api/account/security/mfa/email/confirm |  |
| [**confirmTOTPSetup**](AccountSecurityControllerApi.md#confirmtotpsetup) | **POST** /api/account/security/mfa/totp |  |
| [**deleteMfaConfig**](AccountSecurityControllerApi.md#deletemfaconfig) | **POST** /api/account/security/mfa/delete |  |
| [**disableMfa**](AccountSecurityControllerApi.md#disablemfa) | **POST** /api/account/security/mfa/disable |  |
| [**enableMfa**](AccountSecurityControllerApi.md#enablemfa) | **POST** /api/account/security/mfa/enable |  |
| [**finishRegistration1**](AccountSecurityControllerApi.md#finishregistration1) | **POST** /api/account/security/mfa/passkey/registration/finish |  |
| [**firstLogin**](AccountSecurityControllerApi.md#firstloginoperation) | **POST** /api/account/security/first-login |  |
| [**generateMfaBackupCodes**](AccountSecurityControllerApi.md#generatemfabackupcodes) | **GET** /api/account/security/mfa/backup-codes |  |
| [**getMfaConfig**](AccountSecurityControllerApi.md#getmfaconfig) | **GET** /api/account/security/mfa |  |
| [**initEmailMfa**](AccountSecurityControllerApi.md#initemailmfa) | **POST** /api/account/security/mfa/email/init |  |
| [**requestTOTP**](AccountSecurityControllerApi.md#requesttotp) | **GET** /api/account/security/mfa/totp |  |
| [**startRegistration1**](AccountSecurityControllerApi.md#startregistration1) | **POST** /api/account/security/mfa/passkey/registration/start |  |



## changePassword

> CustomApiResponseVoid changePassword(changePasswordRequest)



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { ChangePasswordOperationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  const body = {
    // ChangePasswordRequest
    changePasswordRequest: ...,
  } satisfies ChangePasswordOperationRequest;

  try {
    const data = await api.changePassword(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **changePasswordRequest** | [ChangePasswordRequest](ChangePasswordRequest.md) |  | |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## confirmEmailMfa

> CustomApiResponseMfaConfigResponse confirmEmailMfa(mfaConfirmRequest)



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { ConfirmEmailMfaRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  const body = {
    // MfaConfirmRequest
    mfaConfirmRequest: ...,
  } satisfies ConfirmEmailMfaRequest;

  try {
    const data = await api.confirmEmailMfa(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **mfaConfirmRequest** | [MfaConfirmRequest](MfaConfirmRequest.md) |  | |

### Return type

[**CustomApiResponseMfaConfigResponse**](CustomApiResponseMfaConfigResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## confirmTOTPSetup

> CustomApiResponseVoid confirmTOTPSetup(tOTPConfirmRequest)



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { ConfirmTOTPSetupRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  const body = {
    // TOTPConfirmRequest
    tOTPConfirmRequest: ...,
  } satisfies ConfirmTOTPSetupRequest;

  try {
    const data = await api.confirmTOTPSetup(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **tOTPConfirmRequest** | [TOTPConfirmRequest](TOTPConfirmRequest.md) |  | |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deleteMfaConfig

> CustomApiResponseVoid deleteMfaConfig(mfaDeleteRequest)



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { DeleteMfaConfigRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  const body = {
    // MfaDeleteRequest
    mfaDeleteRequest: ...,
  } satisfies DeleteMfaConfigRequest;

  try {
    const data = await api.deleteMfaConfig(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **mfaDeleteRequest** | [MfaDeleteRequest](MfaDeleteRequest.md) |  | |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## disableMfa

> CustomApiResponseVoid disableMfa(mfaDisableRequest)



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { DisableMfaRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  const body = {
    // MfaDisableRequest
    mfaDisableRequest: ...,
  } satisfies DisableMfaRequest;

  try {
    const data = await api.disableMfa(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **mfaDisableRequest** | [MfaDisableRequest](MfaDisableRequest.md) |  | |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## enableMfa

> CustomApiResponseVoid enableMfa()



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { EnableMfaRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  try {
    const data = await api.enableMfa();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## finishRegistration1

> CustomApiResponseVoid finishRegistration1(finishPasskeyRegistrationRequest)



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { FinishRegistration1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  const body = {
    // FinishPasskeyRegistrationRequest
    finishPasskeyRegistrationRequest: ...,
  } satisfies FinishRegistration1Request;

  try {
    const data = await api.finishRegistration1(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **finishPasskeyRegistrationRequest** | [FinishPasskeyRegistrationRequest](FinishPasskeyRegistrationRequest.md) |  | |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## firstLogin

> CustomApiResponseVoid firstLogin(firstLoginRequest)



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { FirstLoginOperationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  const body = {
    // FirstLoginRequest
    firstLoginRequest: ...,
  } satisfies FirstLoginOperationRequest;

  try {
    const data = await api.firstLogin(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **firstLoginRequest** | [FirstLoginRequest](FirstLoginRequest.md) |  | |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## generateMfaBackupCodes

> CustomApiResponseListString generateMfaBackupCodes()



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { GenerateMfaBackupCodesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  try {
    const data = await api.generateMfaBackupCodes();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseListString**](CustomApiResponseListString.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getMfaConfig

> CustomApiResponseListMfaConfigResponse getMfaConfig()



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { GetMfaConfigRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  try {
    const data = await api.getMfaConfig();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseListMfaConfigResponse**](CustomApiResponseListMfaConfigResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## initEmailMfa

> CustomApiResponseMfaInitResponse initEmailMfa(mfaEmailInitRequest)



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { InitEmailMfaRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  const body = {
    // MfaEmailInitRequest
    mfaEmailInitRequest: ...,
  } satisfies InitEmailMfaRequest;

  try {
    const data = await api.initEmailMfa(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **mfaEmailInitRequest** | [MfaEmailInitRequest](MfaEmailInitRequest.md) |  | |

### Return type

[**CustomApiResponseMfaInitResponse**](CustomApiResponseMfaInitResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## requestTOTP

> CustomApiResponseMfaSetupResponse requestTOTP()



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { RequestTOTPRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  try {
    const data = await api.requestTOTP();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseMfaSetupResponse**](CustomApiResponseMfaSetupResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## startRegistration1

> CustomApiResponsePublicKeyCredentialCreationOptions startRegistration1()



### Example

```ts
import {
  Configuration,
  AccountSecurityControllerApi,
} from '';
import type { StartRegistration1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSecurityControllerApi(config);

  try {
    const data = await api.startRegistration1();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponsePublicKeyCredentialCreationOptions**](CustomApiResponsePublicKeyCredentialCreationOptions.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


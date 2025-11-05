# ICDCodeManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createIcdCode**](ICDCodeManagementApi.md#createicdcode) | **POST** /api/common/icd-codes |  |
| [**deleteIcdCode**](ICDCodeManagementApi.md#deleteicdcode) | **DELETE** /api/common/icd-codes/{id} |  |
| [**filter9**](ICDCodeManagementApi.md#filter9) | **POST** /api/common/icd-codes/filter |  |
| [**getIcdCodeById**](ICDCodeManagementApi.md#geticdcodebyid) | **GET** /api/common/icd-codes/{id} |  |
| [**updateIcdCode**](ICDCodeManagementApi.md#updateicdcode) | **PUT** /api/common/icd-codes/{id} |  |



## createIcdCode

> CustomApiResponseIcdCodeResponse createIcdCode(icdCodeRequest)



### Example

```ts
import {
  Configuration,
  ICDCodeManagementApi,
} from '';
import type { CreateIcdCodeRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ICDCodeManagementApi(config);

  const body = {
    // IcdCodeRequest
    icdCodeRequest: ...,
  } satisfies CreateIcdCodeRequest;

  try {
    const data = await api.createIcdCode(body);
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
| **icdCodeRequest** | [IcdCodeRequest](IcdCodeRequest.md) |  | |

### Return type

[**CustomApiResponseIcdCodeResponse**](CustomApiResponseIcdCodeResponse.md)

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


## deleteIcdCode

> CustomApiResponseVoid deleteIcdCode(id)



### Example

```ts
import {
  Configuration,
  ICDCodeManagementApi,
} from '';
import type { DeleteIcdCodeRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ICDCodeManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DeleteIcdCodeRequest;

  try {
    const data = await api.deleteIcdCode(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |

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


## filter9

> CustomApiResponsePageIcdCodeResponse filter9(searchFilter)



### Example

```ts
import {
  Configuration,
  ICDCodeManagementApi,
} from '';
import type { Filter9Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ICDCodeManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter9Request;

  try {
    const data = await api.filter9(body);
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
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageIcdCodeResponse**](CustomApiResponsePageIcdCodeResponse.md)

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


## getIcdCodeById

> CustomApiResponseIcdCodeResponse getIcdCodeById(id)



### Example

```ts
import {
  Configuration,
  ICDCodeManagementApi,
} from '';
import type { GetIcdCodeByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ICDCodeManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetIcdCodeByIdRequest;

  try {
    const data = await api.getIcdCodeById(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseIcdCodeResponse**](CustomApiResponseIcdCodeResponse.md)

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


## updateIcdCode

> CustomApiResponseIcdCodeResponse updateIcdCode(id, icdCodeRequest)



### Example

```ts
import {
  Configuration,
  ICDCodeManagementApi,
} from '';
import type { UpdateIcdCodeRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ICDCodeManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // IcdCodeRequest
    icdCodeRequest: ...,
  } satisfies UpdateIcdCodeRequest;

  try {
    const data = await api.updateIcdCode(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **icdCodeRequest** | [IcdCodeRequest](IcdCodeRequest.md) |  | |

### Return type

[**CustomApiResponseIcdCodeResponse**](CustomApiResponseIcdCodeResponse.md)

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


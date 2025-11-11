# UserManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**filter12**](UserManagementApi.md#filter12) | **POST** /api/admin/users/filter |  |
| [**lockUserAccount**](UserManagementApi.md#lockuseraccount) | **POST** /api/admin/users/{userId}/lock |  |
| [**unlockUserAccount**](UserManagementApi.md#unlockuseraccount) | **POST** /api/admin/users/{userId}/unlock |  |



## filter12

> CustomApiResponsePageUserResponse filter12(searchFilter)



### Example

```ts
import {
  Configuration,
  UserManagementApi,
} from '';
import type { Filter12Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new UserManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter12Request;

  try {
    const data = await api.filter12(body);
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

[**CustomApiResponsePageUserResponse**](CustomApiResponsePageUserResponse.md)

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


## lockUserAccount

> CustomApiResponseVoid lockUserAccount(userId)



### Example

```ts
import {
  Configuration,
  UserManagementApi,
} from '';
import type { LockUserAccountRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new UserManagementApi(config);

  const body = {
    // string
    userId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies LockUserAccountRequest;

  try {
    const data = await api.lockUserAccount(body);
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
| **userId** | `string` |  | [Defaults to `undefined`] |

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


## unlockUserAccount

> CustomApiResponseVoid unlockUserAccount(userId)



### Example

```ts
import {
  Configuration,
  UserManagementApi,
} from '';
import type { UnlockUserAccountRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new UserManagementApi(config);

  const body = {
    // string
    userId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies UnlockUserAccountRequest;

  try {
    const data = await api.unlockUserAccount(body);
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
| **userId** | `string` |  | [Defaults to `undefined`] |

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


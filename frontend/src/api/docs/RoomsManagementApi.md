# RoomsManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**_delete**](RoomsManagementApi.md#_delete) | **DELETE** /api/organization/rooms/{id} |  |
| [**create**](RoomsManagementApi.md#create) | **POST** /api/organization/rooms |  |
| [**filter1**](RoomsManagementApi.md#filter1) | **POST** /api/organization/rooms/filter |  |
| [**getRoomById**](RoomsManagementApi.md#getroombyid) | **GET** /api/organization/rooms/{id} |  |
| [**update**](RoomsManagementApi.md#update) | **PUT** /api/organization/rooms/{id} |  |



## _delete

> CustomApiResponseVoid _delete(id)



### Example

```ts
import {
  Configuration,
  RoomsManagementApi,
} from '';
import type { DeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new RoomsManagementApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies DeleteRequest;

  try {
    const data = await api._delete(body);
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


## create

> CustomApiResponseRoomResponse create(roomRequest)



### Example

```ts
import {
  Configuration,
  RoomsManagementApi,
} from '';
import type { CreateRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new RoomsManagementApi(config);

  const body = {
    // RoomRequest
    roomRequest: ...,
  } satisfies CreateRequest;

  try {
    const data = await api.create(body);
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
| **roomRequest** | [RoomRequest](RoomRequest.md) |  | |

### Return type

[**CustomApiResponseRoomResponse**](CustomApiResponseRoomResponse.md)

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


## filter1

> CustomApiResponsePageRoomResponse filter1(searchFilter)



### Example

```ts
import {
  Configuration,
  RoomsManagementApi,
} from '';
import type { Filter1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new RoomsManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter1Request;

  try {
    const data = await api.filter1(body);
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

[**CustomApiResponsePageRoomResponse**](CustomApiResponsePageRoomResponse.md)

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


## getRoomById

> CustomApiResponseRoomResponse getRoomById(id)



### Example

```ts
import {
  Configuration,
  RoomsManagementApi,
} from '';
import type { GetRoomByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new RoomsManagementApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies GetRoomByIdRequest;

  try {
    const data = await api.getRoomById(body);
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

[**CustomApiResponseRoomResponse**](CustomApiResponseRoomResponse.md)

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


## update

> CustomApiResponseRoomResponse update(id, roomRequest)



### Example

```ts
import {
  Configuration,
  RoomsManagementApi,
} from '';
import type { UpdateRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new RoomsManagementApi(config);

  const body = {
    // string
    id: id_example,
    // RoomRequest
    roomRequest: ...,
  } satisfies UpdateRequest;

  try {
    const data = await api.update(body);
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
| **roomRequest** | [RoomRequest](RoomRequest.md) |  | |

### Return type

[**CustomApiResponseRoomResponse**](CustomApiResponseRoomResponse.md)

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

